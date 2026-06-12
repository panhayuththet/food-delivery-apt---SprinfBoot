package com.example.micro.food.delivery.api.service.impl;

import com.example.micro.food.delivery.api.dto.OrderRequest;
import com.example.micro.food.delivery.api.dto.OrderResponse;
import com.example.micro.food.delivery.api.dto.OrderStatusRequest;
import com.example.micro.food.delivery.api.enumeration.DeliveryStatus;
import com.example.micro.food.delivery.api.enumeration.OrderStatus;
import com.example.micro.food.delivery.api.enumeration.PaymentMethod;
import com.example.micro.food.delivery.api.enumeration.PaymentStatus;
import com.example.micro.food.delivery.api.exception.BadRequestErrorException;
import com.example.micro.food.delivery.api.exception.InternalServerErrorException;
import com.example.micro.food.delivery.api.exception.UserNotFoundErrorException;
import com.example.micro.food.delivery.api.model.*;
import com.example.micro.food.delivery.api.repository.*;
import com.example.micro.food.delivery.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final DeliveryRepository deliveryRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public OrderResponse create(OrderRequest orderRequest) {

        Optional<User> user = userRepository.findById(orderRequest.getUserId());

        if (user.isEmpty()) {
            log.warn("User with id {} not found", orderRequest.getUserId());
            throw new UserNotFoundErrorException("User Not Found.");
        }

        Optional<Order> verifyOrderPending = orderRepository.findFirstByUserIdAndOrderStatusNot(user.get().getId(),
                OrderStatus.DELIVERED);

        if (verifyOrderPending.isPresent()) {
            log.warn("User with id {} has pending order", orderRequest.getUserId());
            throw new BadRequestErrorException("User has pending order.");
        }

        Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequest.getRestaurantId());
        if (restaurant.isEmpty()) {
            log.warn("Restaurant with id {} not found", orderRequest.getRestaurantId());
            throw new InternalServerErrorException("Restaurant Not Found.");
        }

        List<DeliveryPartner> deliveryPartners = deliveryPartnerRepository.findAllByAvailable(true);
        if (deliveryPartners.isEmpty()) {
            log.warn("No available delivery partners found");
            throw new InternalServerErrorException("No available delivery partners found.");
        }
        if (orderRequest.getOrderItemRequests().isEmpty()) {
            log.info("Order items are empty");
            throw new BadRequestErrorException("Order Items required non-null");
        }

        final var assignToDeliveryPartner = deliveryPartners.getFirst();
        final var orderId = user.get().getPhoneNumber()+UUID.randomUUID();

        Payment payment = new Payment();
        payment.setAmount(Double.valueOf(orderRequest.getTotalAmount()));
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentMethod(PaymentMethod.valueOf(orderRequest.getPaymentRequest().getPaymentMethod()));
        payment.setDescription(orderRequest.getPaymentRequest().getPaymentDescription());
        payment.setCreatedBy(user.get().getUsername());
        payment.setCreatedAt(new Date());

        log.info("Payment created: {}", payment);
        paymentRepository.saveAndFlush(payment);

        if (payment.getId() == null) {
            log.error("Payment not created");
            throw new InternalServerErrorException("Request fail please try again later.");
        }

        Order order = new Order();

        order.setOrderId((orderId));
        order.setOrderId((user.get().getPhoneNumber()+ UUID.randomUUID()));
        order.setOrderStatus(OrderStatus.valueOf(OrderStatus.PENDING.name()));
        order.setOrderDate(new Date());
        order.setTax(orderRequest.getTax());
        order.setTotalAmount(Double.parseDouble(orderRequest.getTotalAmount()));
        order.setUserId(user.get().getId());
        order.setDeliveryPartnerId(assignToDeliveryPartner.getId());
        order.setRestaurantId(restaurant.get().getId());
        order.setPaymentId(payment.getId());

        log.info("Order created: {}", order);
        orderRepository.saveAndFlush(order);

        if(order.getId() == null){
            log.error("Order not created");
            throw new InternalServerErrorException("Request fail please try again later.");
        }

        Delivery delivery = new Delivery();
        delivery.setDeliveryPartnerId(assignToDeliveryPartner.getId());
        delivery.setOrderId(order.getId());
        delivery.setPickupAddress(restaurant.get().getAddress());
        delivery.setDeliveryAddress(user.get().getAddress());
        delivery.setDeliveryPartnerId(assignToDeliveryPartner.getId());
        delivery.setDeliveryFee(orderRequest.getDeliveryFee());

        log.info("Delivery created: {}", delivery);
        deliveryRepository.saveAndFlush(delivery);
        return OrderResponse.builder()
                .orderId(orderId)
                .paymentMethod(payment.getPaymentMethod().name())
                .totalAmount(orderRequest.getDeliveryFee())
                .orderStatus(order.getOrderStatus().name())
                .build();

    }

    @Transactional
    @Override
    public OrderResponse update(OrderStatusRequest orderStatusRequest) {

        if (orderStatusRequest.getOrderId() == null ||
                orderStatusRequest.getOrderId().isBlank()) {
            throw new BadRequestErrorException("Order Id is required.");
        }

        Optional<Order> orderOpt =
                orderRepository.findFirstByOrderId(orderStatusRequest.getOrderId());

        if (orderOpt.isEmpty()) {
            log.warn("Order with id {} not found", orderStatusRequest.getOrderId());
            throw new BadRequestErrorException("Order Not Found.");
        }

        Order order = orderOpt.get();

        Delivery delivery = deliveryRepository.findFirstByOrderId(order.getId())
                .orElseThrow(() -> {
                    log.warn("Delivery with order id {} not found", order.getId());
                    return new InternalServerErrorException("Delivery Not Found.");
                });

        Payment payment = paymentRepository.findById(order.getPaymentId())
                .orElseThrow(() -> {
                    log.warn("Payment with id {} not found", order.getPaymentId());
                    return new InternalServerErrorException("Payment Not Found.");
                });

        String status = orderStatusRequest.getOrderStatus();

        if (OrderStatus.PENDING.name().equals(status)) {

            order.setOrderStatus(OrderStatus.PREPARING);

        } else if (OrderStatus.PREPARING.name().equals(status)) {

            order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);

            delivery.setPickUpTime(new Date());
            delivery.setDeliveryStatus(DeliveryStatus.PICKED_UP);

            deliveryRepository.save(delivery);

        } else if (OrderStatus.OUT_FOR_DELIVERY.name().equals(status)) {

            order.setOrderStatus(OrderStatus.DELIVERED);

            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);

            delivery.setDeliveryTime(new Date());
            delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);

            deliveryRepository.save(delivery);

        } else {
            throw new BadRequestErrorException(
                    "Invalid order status: " + status
            );
        }

        orderRepository.save(order);

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus().name())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(payment.getPaymentMethod().name())
                .build();
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public OrderResponse getById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAll() {
        return List.of();
    }
}

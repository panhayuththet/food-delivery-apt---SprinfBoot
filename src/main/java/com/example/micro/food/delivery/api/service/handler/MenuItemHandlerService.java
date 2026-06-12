package com.example.micro.food.delivery.api.service.handler;


import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.MenuItemPhotoRequest;
import com.example.micro.food.delivery.api.dto.MenuItemRequest;
import com.example.micro.food.delivery.api.dto.MenuItemResponse;
import com.example.micro.food.delivery.api.model.MenuItem;
import com.example.micro.food.delivery.api.model.MenuItemPhoto;
import com.example.micro.food.delivery.api.model.Restaurant;
import com.example.micro.food.delivery.api.repository.MenuItemPhotoRepository;
import com.example.micro.food.delivery.api.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemHandlerService {

    private final MenuItemPhotoRepository menuItemPhotoRepository;

    public boolean verifyMenuItemPhoto(MenuItemRequest menuItemRequest){
        // Verify menu item photo
        // Logic verify menu item photo will write here
        Set<Long> menuItemPhotoIds =  menuItemRequest
                .getMenuItemPhotoRequest()
                .stream().map(MenuItemPhotoRequest::getId)
                .collect(Collectors.toSet());

        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAllById(menuItemPhotoIds);
        if(menuItemPhotos.isEmpty()){
            log.info("Menu item photo not found");
            return false;
        }
        return true;
    }

    public MenuItem convertMenuItemRequestToMenuItem(MenuItemRequest menuItemRequest,
                                                     MenuItem menuItem, Restaurant restaurant) {

        menuItem.setName(menuItemRequest.getName());
        menuItem.setCode(menuItemRequest.getCode());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setAvailability(menuItemRequest.getAvailability());
        menuItem.setRestaurant(restaurant);
        if (menuItem.getId() != null) {
            menuItem.setCreatedAt(new Date());
            menuItem.setCreatedBy(Constant.SYSTEM);

        }

        return menuItem;
    }

    public MenuItemResponse convertMenuItemToMenuItemResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .code(menuItem.getCode())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .availability(menuItem.getAvailability())
                .restaurantId(menuItem.getRestaurant().getId())
                .build();
    }

}

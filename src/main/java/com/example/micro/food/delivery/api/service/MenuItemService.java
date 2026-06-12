package com.example.micro.food.delivery.api.service;

import com.example.micro.food.delivery.api.dto.MenuItemRequest;
import com.example.micro.food.delivery.api.dto.MenuItemResponse;
import java.util.List;
public interface MenuItemService {

    MenuItemResponse create(MenuItemRequest menuItemRequest);

    MenuItemResponse update(Long id, MenuItemRequest menuItemRequest);

    void delete(Long id);

    MenuItemResponse getById(Long id);

    List<MenuItemResponse> getAll();
}

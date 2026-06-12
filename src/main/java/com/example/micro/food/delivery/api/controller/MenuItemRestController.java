package com.example.micro.food.delivery.api.controller;

import com.example.micro.food.delivery.api.dto.MenuItemRequest;
import com.example.micro.food.delivery.api.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MenuItemRestController {

    private final MenuItemService menuItemService;

    @PostMapping(value = "/v1/menu-item", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(
            @RequestBody MenuItemRequest menuItemRequest) {
        log.info("Intercept request create new menu-item v1 with request: {}", menuItemRequest);
        return new ResponseEntity<>(menuItemService.create(menuItemRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/v1/menu-items/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody MenuItemRequest menuItemRequest) {

        log.info("Intercept request update MenuItem v1 with request: {}", menuItemRequest);

       return new ResponseEntity<>(menuItemService.update(id, menuItemRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/menu-items/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.info("Intercept request delete menuItem v1 with id: {}", id);

        menuItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v1/menu-items/{id}",
            produces = "application/json")
    public ResponseEntity<Object> findById(@PathVariable Long id) {

        log.info("Intercept request find menuitem by id: {}", id);

        return new ResponseEntity<>(menuItemService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/menuitems",
            produces = "application/json")
    public ResponseEntity<Object> getAll() {

        log.info("Intercept request find all menuitem");
        return new ResponseEntity<>(menuItemService.getAll(), HttpStatus.OK);
    }
}
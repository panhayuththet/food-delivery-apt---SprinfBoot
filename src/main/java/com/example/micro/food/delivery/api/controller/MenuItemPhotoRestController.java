package com.example.micro.food.delivery.api.controller;

import com.example.micro.food.delivery.api.dto.MenuItemPhotoRequest;
import com.example.micro.food.delivery.api.exception.ApiResponseUtil;
import com.example.micro.food.delivery.api.service.MenuItemPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MenuItemPhotoRestController {

    private final MenuItemPhotoService menuItemPhotoService;

    @PostMapping(value = "/v1/menu-items/photo/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<Object> upload(@RequestParam("files") MultipartFile[] files,
                                         MenuItemPhotoRequest menuItemPhotoRequest) {

      log.info("Upload restaurant v1 with id: {}", menuItemPhotoRequest);
      return new ResponseEntity<>(ApiResponseUtil.successResponse(menuItemPhotoService.upload(files, menuItemPhotoRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "/v1/menu-items/photo/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody MenuItemPhotoRequest menuItemPhotoRequest) {

        log.info("Update menu-item photo id={} request={}", id, menuItemPhotoRequest);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(menuItemPhotoService.update(id, menuItemPhotoRequest)), HttpStatus.OK);
    }

    @DeleteMapping("/v1/menu-items/photo/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.info("Delete menu-item photo id={}", id);

        menuItemPhotoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/menu-items/photo/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {

        log.info("Find menu-item photo by id={}", id);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(findById(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/menu-items/photo", produces = "application/json")
    public ResponseEntity<Object> getAll() {

        log.info("Get all menu-item photos");

        return new ResponseEntity<>(menuItemPhotoService.getAll(), HttpStatus.OK);
    }
}
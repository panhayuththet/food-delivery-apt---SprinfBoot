package com.example.micro.food.delivery.api.service.impl;

import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.MenuItemPhotoRequest;
import com.example.micro.food.delivery.api.dto.MenuItemPhotoResponse;
import com.example.micro.food.delivery.api.model.MenuItemPhoto;
import com.example.micro.food.delivery.api.repository.MenuItemPhotoRepository;
import com.example.micro.food.delivery.api.service.MenuItemPhotoService;
import com.example.micro.food.delivery.api.service.handler.MenuItemPhotoHandlerService;
import com.example.micro.food.delivery.api.utils.StringClazzUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemPhotoServiceImpl implements MenuItemPhotoService {

    private final MenuItemPhotoRepository menuItemPhotoRepository;
    private final MenuItemPhotoHandlerService menuItemPhotoHandlerService;

    private static final String FILE_UPLOAD_PATH = "/D:\\SU43\\Spring Boot\\micro-food-delivery-api/upload";

    @Override
    public List<MenuItemPhotoResponse>  upload(MultipartFile[] files, MenuItemPhotoRequest menuItemPhotoRequest) {

        menuItemPhotoHandlerService.validateFileUpload(files);
        menuItemPhotoHandlerService.validateValidFileUpload(files);

        List<MenuItemPhotoResponse> menuItemPhotoResponses = new ArrayList<>();

        try {
            for(MultipartFile file : files){
                var name = FilenameUtils.removeExtension(file.getOriginalFilename());
                var extensionName = StringClazzUtils.getFileExtension(file.getOriginalFilename());
                var fileName = name + "." +  extensionName;

                File filePathTemp = new File(FILE_UPLOAD_PATH, fileName);
                file.transferTo(filePathTemp);

                MenuItemPhoto menuItemPhoto = new MenuItemPhoto();
                menuItemPhotoRequest.setFileName(extensionName);
                menuItemPhotoRequest.setFileName(name);
                menuItemPhotoRequest.setFileSize((double) file.getSize());
                menuItemPhotoRequest.setFileFormat(file.getContentType());
                menuItemPhotoRequest.setSmallUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);
                menuItemPhotoRequest.setMediumUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);
                menuItemPhotoRequest.setLargeUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);


                menuItemPhoto = menuItemPhotoHandlerService
                        .convertMenuItemRequestPhotoToMenuItemPhoto(menuItemPhotoRequest,  menuItemPhoto);

                log.info("Save menu item photo");
                menuItemPhotoRepository.save(menuItemPhoto);

                menuItemPhotoResponses.add(menuItemPhotoHandlerService
                        .convertMenuItemPhotoToMenuItemPhotoResponse(menuItemPhoto));
            }
        }catch (Exception e) {
            log.error("Error upload file: {}", e.getMessage());
        }
            return  menuItemPhotoResponses;
        }



    @Override
    public MenuItemPhotoResponse update(Long id, MenuItemPhotoRequest menuItemPhotoRequest) {
        Optional<MenuItemPhoto> menuItemPhoto = menuItemPhotoRepository.findById(id);

        if(menuItemPhoto.isEmpty()){
            log.info("Menu item photo not found");
            return new MenuItemPhotoResponse();
        }
        MenuItemPhoto updateMenuItemPhoto = menuItemPhotoHandlerService
                .convertMenuItemRequestPhotoToMenuItemPhoto(menuItemPhotoRequest, menuItemPhoto.get());
        updateMenuItemPhoto.setUpdatedBy(Constant.SYSTEM);
        updateMenuItemPhoto.setUpdatedAt(new Date());

        log.info("Update menu item photo");
        menuItemPhotoRepository.saveAndFlush(updateMenuItemPhoto);

        return menuItemPhotoHandlerService.convertMenuItemPhotoToMenuItemPhotoResponse(updateMenuItemPhoto);
    }

    @Override
    public void delete(Long id) {
        menuItemPhotoRepository.deleteById(id);
    }

    @Override
    public MenuItemPhotoResponse getById(Long id) {
        Optional<MenuItemPhoto> menuItemPhoto = menuItemPhotoRepository.findById(id);
        return menuItemPhoto.map(menuItemPhotoHandlerService::convertMenuItemPhotoToMenuItemPhotoResponse)
                .orElse(null);
    }

    @Override
    public List<MenuItemPhotoResponse> getAll() {
        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAll();
        if(menuItemPhotos.isEmpty()){
            return List.of();
        }
        List<MenuItemPhotoResponse> menuItemPhotoResponses = new ArrayList<>();
        for(MenuItemPhoto itemPhoto : menuItemPhotos){
            menuItemPhotoResponses.add(menuItemPhotoHandlerService.convertMenuItemPhotoToMenuItemPhotoResponse(itemPhoto));
        }
        return menuItemPhotoResponses;
    }
}

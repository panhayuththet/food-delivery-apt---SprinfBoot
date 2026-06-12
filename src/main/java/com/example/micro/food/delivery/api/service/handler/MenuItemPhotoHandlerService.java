package com.example.micro.food.delivery.api.service.handler;

import com.example.micro.food.delivery.api.constant.Constant;
import com.example.micro.food.delivery.api.dto.MenuItemPhotoRequest;
import com.example.micro.food.delivery.api.dto.MenuItemPhotoResponse;
import com.example.micro.food.delivery.api.model.MenuItem;
import com.example.micro.food.delivery.api.model.MenuItemPhoto;
import com.example.micro.food.delivery.api.repository.MenuItemPhotoRepository;
import com.example.micro.food.delivery.api.utils.StringClazzUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service

public class MenuItemPhotoHandlerService {

    private final MenuItemPhotoRepository menuItemPhotoRepository;
    private final List<String> FILE_EXTENSION = Arrays.asList("jpg", "jpeg", "png");

    public MenuItemPhotoHandlerService(MenuItemPhotoRepository menuItemPhotoRepository) {
        this.menuItemPhotoRepository = menuItemPhotoRepository;
    }

    public void validateFileUpload(MultipartFile[] files) {
        if(files.length == 0) {
            log.warn("File is Empty");
            throw new IllegalArgumentException("File is Empty");
        }
    }

    public void validateValidFileUpload(MultipartFile[] files) {
        for(MultipartFile file : files) {

                var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                var extension = StringClazzUtils.getFileExtension(fileName);

                if(!FILE_EXTENSION.contains(extension)) {
                    log.warn("File extension not allow to upload, please verify {}", fileName);
                    throw new IllegalArgumentException("File extension not allow to upload, please verify");
                }
        }
    }

    public void updateFileByMenuItemAndFileId(MenuItem menuItem, List<MenuItemPhotoRequest> menuItemPhotoRequests) {
        final Set<Long> menuItemPhotoIds = menuItemPhotoRequests.stream()
                .map(MenuItemPhotoRequest::getId)
                .collect(Collectors.toSet());

        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAllByIdIn(menuItemPhotoIds);
        for(MenuItemPhoto menuItemPhoto : menuItemPhotos) {
            menuItemPhoto.setMenuItem(menuItem);
            menuItemPhotoRepository.saveAndFlush(menuItemPhoto);
        }
    }

    public MenuItemPhoto convertMenuItemRequestPhotoToMenuItemPhoto(MenuItemPhotoRequest menuItemPhotoRequest, MenuItemPhoto menuItemPhoto) {

        menuItemPhoto.setFileName(menuItemPhotoRequest.getFileName());
        menuItemPhoto.setFileType(menuItemPhotoRequest.getFileType());
        menuItemPhoto.setFileFormat(menuItemPhotoRequest.getFileFormat());
        menuItemPhoto.setFileSize(menuItemPhotoRequest.getFileSize());
        menuItemPhoto.setMediumUrl(menuItemPhotoRequest.getMediumUrl());
        menuItemPhoto.setLargeUrl(menuItemPhotoRequest.getLargeUrl());
        menuItemPhoto.setSmallUrl(menuItemPhotoRequest.getSmallUrl());
        menuItemPhoto.setUpdatedBy(menuItemPhotoRequest.getUploadedBy());


        menuItemPhoto.setStatus(Constant.ACTIVE);
        if (StringUtils.hasText(menuItemPhotoRequest.getStatus())) {
            menuItemPhoto.setStatus(menuItemPhotoRequest.getStatus());
        }

        if (menuItemPhoto.getId() != null) {
            menuItemPhoto.setCreatedAt(new Date());
            menuItemPhoto.setCreatedBy(Constant.SYSTEM);
        }
        return menuItemPhoto;

    }

    public MenuItemPhotoResponse convertMenuItemPhotoToMenuItemPhotoResponse(MenuItemPhoto menuItemPhoto) {
        return MenuItemPhotoResponse.builder()
                .id(menuItemPhoto.getId())
                .fileName(menuItemPhoto.getFileName())
                .fileType(menuItemPhoto.getFileType())
                .fileFormat(menuItemPhoto.getFileFormat())
                .fileSize(menuItemPhoto.getFileSize())
                .mediumUrl(menuItemPhoto.getMediumUrl())
                .largeUrl(menuItemPhoto.getLargeUrl())
                .smallUrl(menuItemPhoto.getSmallUrl())
                .status(menuItemPhoto.getStatus())
                .build();
    }

}

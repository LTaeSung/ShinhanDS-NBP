package com.delivery.domain.menu.controller;

import com.delivery.domain.menu.dto.MenuDto;
import com.delivery.domain.menu.service.MenuService;
import com.delivery.domain.store.entity.StoreEntity;
import com.delivery.domain.store.repository.StoreRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final StoreRepository storeRepository;
    private final MenuService menuService;

    @GetMapping("/menu/new")
    public String menuSaveForm(Model model, HttpSession session) {
        Long newOwnerId = (Long) session.getAttribute("ownerId");
        Optional<StoreEntity> targetStore = storeRepository.findByOwnerEntity_Id(newOwnerId);

        if (targetStore.isPresent()){
            model.addAttribute("targetStore", targetStore.get());
        }
        return "html/owner/menu_registration";
    }


    @PostMapping("/menu/new")
    public String menuSave(HttpSession session,
                           @RequestParam("name") List<String> names,
                           @RequestParam("price") List<Integer> prices,
                           @RequestParam("content") List<String> contents,
                           @RequestParam("category") List<String> categories,
                           @RequestPart("multipartFile") List<MultipartFile> files) throws IOException {
        Long ownerId = (Long) session.getAttribute("ownerId");
        Optional<StoreEntity> storeEntity = storeRepository.findByOwnerEntity_Id(ownerId);
        for (int i = 0; i < names.size(); i++) {
            MenuDto menuDto = new MenuDto();
            menuDto.setName(names.get(i));
            menuDto.setPrice(prices.get(i));
            menuDto.setContent(contents.get(i));
            menuDto.setCategory(categories.get(i));
            menuService.save(menuDto,storeEntity.get().getId(), files.get(i));
            // 처리 로직 작성
        }
//        menuService.save(menuDtoList, storeEntity.get().getId());

        return "redirect:/owner/";
    }

    private String encodeImageToBase64(String picturePath) throws IOException {
        Path imagePath = Paths.get("path/to/your/image/directory", picturePath);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
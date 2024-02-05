package com.delivery.domain.menufile.controller;

import com.delivery.domain.menufile.entity.MenuFileEntity;
import com.delivery.domain.menufile.repository.MenuFileRepository;
import com.delivery.domain.menufile.service.MenuFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuFileController {

    private final MenuFileService fileService;
    private final MenuFileRepository fileRepository;


//    @GetMapping("/menu-upload")
//    public String menuTestUploadForm(){
//        return "layouts/menuImage";
//    }
//    @PostMapping("/menu-upload")
//    public String menuUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
//        fileService.saveFile(file);
//
//        for (MultipartFile multipartFile : files) {
//            fileService.saveFile(multipartFile);
//        }
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/menu-view")
//    public String menuView(Model model) {
//
//        List<MenuFileEntity> files = fileRepository.findAll();
//        files.forEach( a-> log.info("test --> " + a.toString()));
//
//        model.addAttribute("menu",files);
//        return "layouts/menuView";
//    }


    //   이미지 출력
    //   이미지 출력

    @GetMapping("/images/menu/{fileId}")

    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException{

        // 여기서 id = menu id 이고 그에 해당하는 menufile_id 찾기
        MenuFileEntity byMenuEntityId = fileRepository.findByMenuEntity_Id(id);

        MenuFileEntity menuFile = fileRepository.findById(byMenuEntityId.getId()).orElse(null);
        return new UrlResource("file:" + "C:/project/sinhan_img/"+ menuFile.getSavedNm());
    }

    // 첨부 파일 다운로드
    @GetMapping("/menu/attach/{id}")
    public ResponseEntity<Resource> menuDownloadAttach(@PathVariable Long id) throws MalformedURLException {

        MenuFileEntity file = fileRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + file.getSavedPath());

        String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
}
package com.spring.api.controller;

import com.spring.api.model.ResponseObject;
import com.spring.api.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/fileupload")
public class FileUploadController {

    @Autowired
    private IStorageService storageService;

    // Nhận file image từ client

    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            // lưu file vào thư mục
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Upload file thành công", generatedFileName)
            );
            // Url to file image

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("ok", e.getMessage(), "")
            );
        }
    }

    // get iamges 'url'
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    // load all file
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadFiles(){
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseObject("failed", "List files failed", new String[] {}));
        }
    }
}

package com.spring.api.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements IStorageService{

    private final Path storageFolder = Paths.get("uploads");
    // Constructor
    public ImageStorageService(){
        try {
            Files.createDirectories(storageFolder);
        }catch (Exception e){
            throw new RuntimeException("Cannot initialize storage", e);
        }
    }

    private boolean isImageFile(MultipartFile file){
        //
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png", "jpg", "jpeg", "bmp", "webp"}).contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("hihi");
            if (file.isEmpty()){
                throw new RuntimeException("Failed to store empty file.");
            }
            // check file
            if (!isImageFile(file)){
                throw new RuntimeException("You can only upload image file");
            }
            // check file must br <= 5MB
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegabytes > 5.0f){
                throw new RuntimeException("File must be <= 5MB");
            }
            // Upload server must be rename
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "-");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(
                    Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Cannot store file outsite current directory. ");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }catch (Exception e){
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            // chỉ chứa ảnh
            return Files.walk(this.storageFolder, 1)
                    .filter(path -> !path.equals(this.storageFolder) && !path.toString().contains("._"))
                    .map(this.storageFolder::relativize);
        }catch (Exception e){
            throw new RuntimeException("Failed to load stored files", e);
        }
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try{
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }else{
                throw new RuntimeException("Không thể đọc file: " + fileName);
            }
        }catch (Exception e){
            throw new RuntimeException("Không thể đọc file: " + fileName, e);
        }
    }

    @Override
    public void deleteAllFiles() {

    }
}

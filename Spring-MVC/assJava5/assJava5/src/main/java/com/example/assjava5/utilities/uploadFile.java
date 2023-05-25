package com.example.assjava5.utilities;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class uploadFile {
    public File handleUploadFile(MultipartFile uploadFile, String name, boolean check) throws IOException {
        String file = "";
        if(check){
            file = "/home/hieu/assJava5/src/main/resources/static/file/product";
        }else {
            file = "/home/hieu/assJava5/src/main/resources/static/file/user";
        }

        File myFile = new File(file);
        if(!myFile.exists()){
            myFile.mkdirs();
        }

        if(check){
            File anhCheck = new File("/home/hieu/assJava5/src/main/resources/static/file/product/" + name);
            anhCheck.delete();
        }else {
            File anhCheck = new File("/home/hieu/assJava5/src/main/resources/static/file/user/" + name);
            anhCheck.delete();
        }
        File save = new File(myFile, name);
        uploadFile.transferTo(save);
        return save;
    }
}

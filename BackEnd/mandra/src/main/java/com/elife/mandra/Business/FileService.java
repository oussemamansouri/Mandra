package com.elife.mandra.Business;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {


    // Method to save the image and return the relative path
public String saveImage(MultipartFile image) throws IOException {
    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
    File directory = new File(uploadDir);
    
    // Create the directory if it does not exist
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Generate a unique filename
    String originalFilename = image.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFilename);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    File file = new File(directory, uniqueFilename);
    image.transferTo(file);
    return "/images/" + uniqueFilename; // Return the relative path with the unique filename
}



 // Method to save the files  and return the relative path
public String saveFile(MultipartFile mFile) throws IOException {
    String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/files";
    File directory = new File(uploadDir);
    
    // Create the directory if it does not exist
    if (!directory.exists()) {
        directory.mkdirs();
    }

    // Generate a unique filename
    String originalFilename = mFile.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFilename);
    String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

    File file = new File(directory, uniqueFilename);
    mFile.transferTo(file);
    return "/files/" + uniqueFilename; // Return the relative path with the unique filename
}

}

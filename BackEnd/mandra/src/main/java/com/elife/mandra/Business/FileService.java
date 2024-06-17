package com.elife.mandra.Business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileService {

    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif");

    // Method to save the file and return the relative path
    public String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();

        if (IMAGE_EXTENSIONS.contains(extension)) {
            return saveImage(file, extension);
        } else if (extension.equals("pdf")) {
            return savePDF(file, extension);
        } else {
            throw new IOException("Unsupported file type: " + extension);
        }
    }

    // Method to save the image and return the relative path
    private String saveImage(MultipartFile image, String extension) throws IOException {
        if (image.getSize() > MAX_IMAGE_SIZE) {
            throw new IOException("File size exceeds the maximum limit of 5MB.");
        }

        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
        File directory = new File(uploadDir);

        // Create the directory if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename
        String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

        File file = new File(directory, uniqueFilename);

        // Compress the image
        Thumbnails.of(image.getInputStream())
                .size(800, 800) // Adjust the size as needed
                .outputFormat(extension)
                .toFile(file);

        return "/images/" + uniqueFilename; // Return the relative path with the unique filename
    }

    // Method to save and compress the PDF and return the relative path
    private String savePDF(MultipartFile pdf, String extension) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/files";
        File directory = new File(uploadDir);

        // Create the directory if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename
        String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

        File file = new File(directory, uniqueFilename);

        // Compress the PDF
        compressPDF(pdf.getInputStream(), file);

        return "/files/" + uniqueFilename; // Return the relative path with the unique filename
    }

    // Method to compress the PDF file
    private void compressPDF(InputStream inputStream, File outputFile) throws IOException {
        PDDocument document = PDDocument.load(inputStream);
        for (PDPage page : document.getPages()) {
            Iterable<COSName> cosNames = page.getResources().getXObjectNames();
            for (COSName cosName : cosNames) {
                if (page.getResources().isImageXObject(cosName)) {
                    PDImageXObject imageXObject = (PDImageXObject) page.getResources().getXObject(cosName);
                    BufferedImage bufferedImage = imageXObject.getImage();

                    // Compress image
                    BufferedImage compressedImage = Thumbnails.of(bufferedImage)
                            .size(800, 800) // Adjust the size as needed
                            .outputQuality(0.75) // Adjust the quality as needed
                            .asBufferedImage();

                    PDImageXObject compressedXObject = JPEGFactory.createFromImage(document, compressedImage);
                    page.getResources().put(cosName, compressedXObject);
                }
            }
        }
        document.save(outputFile);
        document.close();
    }

}

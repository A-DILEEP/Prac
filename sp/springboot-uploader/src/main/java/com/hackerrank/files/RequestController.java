//package com.hackerrank.files;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//public class RequestController {
//    public static final String UPLOAD_DIR = "uploads/";
//
//    @PostMapping("/uploader")
//    public ResponseEntity uploader(@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file) {
//        return null;
//    }
//
//    @GetMapping("/downloader")
//    public ResponseEntity downloader(@RequestParam String fileName) {
//        return null;
//    }
//}

package com.hackerrank.files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@RestController
public class RequestController {
    public static final String UPLOAD_DIR = "uploads/";
    private static final long MAX_FILE_SIZE = 200 * 1024; //200kb

    public RequestController() throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    @PostMapping("/uploader")
    public ResponseEntity<?> uploader(@RequestParam("fileName") String fileName,
                                      @RequestParam("file") MultipartFile file) {
        try {
        	if (file.getSize() >= MAX_FILE_SIZE) {
        	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        	            .body("Internal Server Error: File too large");
        	}

            Path targetLocation = Paths.get(UPLOAD_DIR).resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/downloader")
    public ResponseEntity<Resource> downloader(@RequestParam String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


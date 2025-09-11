package com.hackerrank.files;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
@RestController
public class RequestController {
 
    public static final String UPLOAD_DIR = "uploads/";
 

    @PostMapping("/uploader")
    public ResponseEntity<Void> uploader(
            @RequestParam("fileName") String fileName,
            @RequestParam("file") MultipartFile file) {
        try {
            // Ensure uploads folder exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
 
            // Save/replace file
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, file.getBytes());
 
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
   
    @GetMapping("/downloader")
    public ResponseEntity<byte[]> downloader(@RequestParam("fileName") String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
 
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
 
            byte[] fileContent = Files.readAllBytes(filePath);
 
            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
 
 
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Void> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
//
//
//@ExceptionHandler(MaxUploadSizeExceededException.class)
//public ResponseEntity<Void> handleMaxSize(MaxUploadSizeExceededException ex) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//}
}
package com.example.spring_bot_ai.controller;

import com.example.spring_bot_ai.service.DataService;
import com.example.spring_bot_ai.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/datatables") // Обновленный базовый путь
public class UploadFileController {

    @Autowired
    private DataService dataService;

    @Transactional
    @PostMapping("/upload") // Путь к методу загрузки
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                dataService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + e.getMessage() + " - " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}



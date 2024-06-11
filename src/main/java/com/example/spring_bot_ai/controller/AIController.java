package com.example.spring_bot_ai.controller;

import com.example.spring_bot_ai.model.DataCompanyTurnover;
import com.example.spring_bot_ai.model.FilteredData;
import com.example.spring_bot_ai.repository.DataCompanyTurnoverRepository;
import com.example.spring_bot_ai.repository.FilteredDataRepository;
import com.example.spring_bot_ai.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AIController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DataCompanyTurnoverRepository dataCompanyTurnoverRepository;

    @Autowired
    private FilteredDataRepository filteredDataRepository;

    private static final String TARGET_URL = "http://localhost:5000/upload";

    @PostMapping("/predict-sales/{fileId}")
    @Transactional
    public ResponseEntity<List<FilteredData>> processData(@PathVariable Long fileId) {
        List<DataCompanyTurnover> data = dataCompanyTurnoverRepository.findByFileDetailId(fileId);
        if (data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Удаление старых данных filtered_data для этого файла
        filteredDataRepository.deleteByFileDetailId(fileId);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<DataCompanyTurnover>> request = new HttpEntity<>(data, headers);
        ResponseEntity<FilteredData[]> response = restTemplate.postForEntity(TARGET_URL, request, FilteredData[].class);

        if (response.getBody() != null) {
            for (FilteredData filteredData : response.getBody()) {
                filteredData.setFileDetail(data.get(0).getFileDetail());
            }
            List<FilteredData> savedData = filteredDataRepository.saveAll(List.of(response.getBody()));
            return ResponseEntity.ok(savedData);  // Возвращаем сохраненные данные в формате JSON
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/filtered-data/{fileId}/{id}")
    public ResponseEntity<Void> deleteFilteredData(@PathVariable Long fileId, @PathVariable Long id) {
        dataService.deleteFilteredDataByIdAndFileId(id, fileId);
        return ResponseEntity.noContent().build();
    }
}

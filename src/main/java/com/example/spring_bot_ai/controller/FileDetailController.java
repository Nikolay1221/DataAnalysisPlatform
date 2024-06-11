package com.example.spring_bot_ai.controller;

import com.example.spring_bot_ai.dto.FileDetailDTO;
import com.example.spring_bot_ai.dto.FileDetailWithDataDTO;
import com.example.spring_bot_ai.model.DataCompanyTurnover;
import com.example.spring_bot_ai.model.FileDetail;
import com.example.spring_bot_ai.model.FilteredData;
import com.example.spring_bot_ai.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileDetailController {

    private final DataService dataService;

    @Autowired
    public FileDetailController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<FileDetailDTO> getAllFiles() {
        return dataService.getAllFileDetailsDTO();
    }

    @GetMapping("/{fileId}")
    public List<FileDetailDTO> getFileById(@PathVariable Long fileId) {
        return Collections.singletonList(dataService.convertToDTO(dataService.getFileDetailsById(fileId)));
    }

    @GetMapping("/{fileId}/with-data")
    public FileDetailWithDataDTO getFileWithDataById(@PathVariable Long fileId) {
        return dataService.getFileDetailWithDataById(fileId);
    }

    @GetMapping("/last")
    public List<FileDetailDTO> getLastUploadedFile() {
        FileDetail lastFileDetail = dataService.getLastUploadedFile();
        return Collections.singletonList(dataService.convertToDTO(lastFileDetail));
    }

    @GetMapping("/{fileId}/turnover-data")
    public List<DataCompanyTurnover> getTurnoverDataByFileId(@PathVariable Long fileId) {
        return dataService.getDataByFileId(fileId);
    }

    @GetMapping("/{fileId}/generated-data")
    public List<FilteredData> getGeneratedDataByFileId(@PathVariable Long fileId) {
        return dataService.getGeneratedDataByFileId(fileId);
    }

    @GetMapping("/turnover-data/{dataId}")
    public DataCompanyTurnover getTurnoverDataById(@PathVariable Long dataId) {
        return dataService.getDataById(dataId);
    }

    @PostMapping("/{fileId}/turnover-data")
    public DataCompanyTurnover addTurnoverData(@PathVariable Long fileId, @RequestBody DataCompanyTurnover dataCompanyTurnover) {
        dataCompanyTurnover.setFileDetail(dataService.getFileDetailsById(fileId));
        return dataService.saveData(dataCompanyTurnover);
    }

    @DeleteMapping("/{fileId}/turnover-data/{dataId}")
    public void deleteTurnoverDataById(@PathVariable Long fileId, @PathVariable Long dataId) {
        DataCompanyTurnover data = dataService.getDataById(dataId);
        if (data.getFileDetail().getId().equals(fileId)) {
            dataService.deleteDataById(dataId);
        } else {
            throw new RuntimeException("Data not found for the given file ID");
        }
    }
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) {
        try {
            dataService.deleteFileById(fileId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

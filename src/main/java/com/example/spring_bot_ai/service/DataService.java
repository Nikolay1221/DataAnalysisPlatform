package com.example.spring_bot_ai.service;

import com.example.spring_bot_ai.dto.FileDetailDTO;
import com.example.spring_bot_ai.dto.FileDetailWithDataDTO;
import com.example.spring_bot_ai.model.DataCompanyTurnover;
import com.example.spring_bot_ai.model.FileDetail;
import com.example.spring_bot_ai.model.FilteredData;
import com.example.spring_bot_ai.repository.DataCompanyTurnoverRepository;
import com.example.spring_bot_ai.repository.FileDetailRepository;
import com.example.spring_bot_ai.repository.FilteredDataRepository;
import com.example.spring_bot_ai.util.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private DataCompanyTurnoverRepository dataCompanyTurnoverRepository;

    @Autowired
    private FileDetailRepository fileDetailRepository;

    @Autowired
    private FilteredDataRepository filteredDataRepository;

    @Autowired
    private CSVHelper csvHelper;

    public void save(MultipartFile file) {
        try {
            List<DataCompanyTurnover> dataCompanyTurnovers = csvHelper.csvToDataCompanyTurnovers(file.getInputStream(), file.getOriginalFilename());
            dataCompanyTurnoverRepository.saveAll(dataCompanyTurnovers);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store csv data: " + e.getMessage());
        }
    }

    public List<DataCompanyTurnover> getAllData() {
        return dataCompanyTurnoverRepository.findAll();
    }

    public void deleteDataById(Long id) {
        dataCompanyTurnoverRepository.deleteById(id);
    }

    public DataCompanyTurnover saveData(DataCompanyTurnover dataCompanyTurnover) {
        return dataCompanyTurnoverRepository.save(dataCompanyTurnover);
    }

    public FileDetail getFileDetailsById(Long id) {
        return fileDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<DataCompanyTurnover> getDataByFileId(Long fileId) {
        return dataCompanyTurnoverRepository.findByFileDetailId(fileId);
    }

    public DataCompanyTurnover getDataById(Long id) {
        return dataCompanyTurnoverRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
    }

    public List<FileDetailDTO> getAllFileDetailsDTO() {
        return fileDetailRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public FileDetailDTO convertToDTO(FileDetail fileDetail) {
        FileDetailDTO dto = new FileDetailDTO();
        dto.setId(fileDetail.getId());
        dto.setUploadDate(fileDetail.getUploadDate());
        dto.setTableName(fileDetail.getTableName());
        dto.setRowCount(fileDetail.getRowCount());
        return dto;
    }

    public FileDetailWithDataDTO getFileDetailWithDataById(Long fileId) {
        FileDetail fileDetail = getFileDetailsById(fileId);
        List<DataCompanyTurnover> turnoverData = getDataByFileId(fileId);
        List<FilteredData> generatedData = getGeneratedDataByFileId(fileId);

        FileDetailWithDataDTO dto = new FileDetailWithDataDTO();
        dto.setId(fileDetail.getId());
        dto.setUploadDate(fileDetail.getUploadDate());
        dto.setTableName(fileDetail.getTableName());
        dto.setRowCount(fileDetail.getRowCount());
        dto.setTurnoverData(turnoverData);
        dto.setGeneratedData(generatedData);

        return dto;
    }

    public FileDetail getLastUploadedFile() {
        return fileDetailRepository.findTopByOrderByIdDesc();
    }

    public List<FilteredData> getGeneratedDataByFileId(Long fileId) {
        return filteredDataRepository.findByFileDetailId(fileId);
    }

    public void deleteFileAndRelatedData(Long fileId) {
        List<DataCompanyTurnover> turnoverData = getDataByFileId(fileId);
        List<FilteredData> generatedData = getGeneratedDataByFileId(fileId);

        dataCompanyTurnoverRepository.deleteAll(turnoverData);
        filteredDataRepository.deleteAll(generatedData);
        fileDetailRepository.deleteById(fileId);
    }
}

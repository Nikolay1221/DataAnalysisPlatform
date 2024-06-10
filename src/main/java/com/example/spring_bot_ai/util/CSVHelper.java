package com.example.spring_bot_ai.util;

import com.example.spring_bot_ai.model.DataCompanyTurnover;
import com.example.spring_bot_ai.model.FileDetail;
import com.example.spring_bot_ai.repository.FileDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    private final FileDetailRepository fileDetailRepository;

    @Autowired
    public CSVHelper(FileDetailRepository fileDetailRepository) {
        this.fileDetailRepository = fileDetailRepository;
    }

    public static boolean hasCSVFormat(MultipartFile file) {
        return file.getContentType().equals("text/csv") || file.getContentType().equals("application/vnd.ms-excel");
    }

    public List<DataCompanyTurnover> csvToDataCompanyTurnovers(InputStream is, String fileName) {
        List<DataCompanyTurnover> dataCompanyTurnovers = new ArrayList<>();
        int rowCount = 0;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line;
            boolean headerSkipped = false;
            while ((line = fileReader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // пропуск заголовка
                }
                String[] tokens = line.split(",");
                if (tokens.length > 0) {
                    DataCompanyTurnover data = new DataCompanyTurnover();
                    data.setProductId(Integer.parseInt(tokens[0].trim()));
                    data.setYyyyMM(tokens[1].trim());
                    data.setProductCategory(tokens[2].trim());
                    data.setMin(Integer.parseInt(tokens[3].trim()));
                    data.setMax(Integer.parseInt(tokens[4].trim()));
                    data.setSales(Float.parseFloat(tokens[5].trim()));
                    data.setBalanceStart(Float.parseFloat(tokens[6].trim()));
                    data.setTransit(Float.parseFloat(tokens[7].trim()));
                    data.setBackorder(Float.parseFloat(tokens[8].trim()));
                    dataCompanyTurnovers.add(data);
                    rowCount++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }

        // Сохранение информации о файле в базу данных
        FileDetail fileDetail = new FileDetail();
        fileDetail.setUploadDate(LocalDate.now());
        fileDetail.setTableName(fileName);
        fileDetail.setRowCount(rowCount);
        fileDetail = fileDetailRepository.save(fileDetail);

        // Установка связи между данными и файлом
        for (DataCompanyTurnover data : dataCompanyTurnovers) {
            data.setFileDetail(fileDetail);
        }

        return dataCompanyTurnovers;
    }
}

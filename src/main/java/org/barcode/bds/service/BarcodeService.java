package org.barcode.bds.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.barcode.bds.model.Barcodes;
import org.barcode.bds.repository.BarcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class BarcodeService {

    @Autowired
    private BarcodeRepository barcodeRepository;


    public Optional<Barcodes> getByBarcode(String code){
        return barcodeRepository.findByCode(code);
    }

    @PostConstruct
    public void init() throws IOException {
        String[] HEADERS = {
                "code",
                "brand"
        };

        Reader in = new FileReader("/Users/mohamedabdelnabi/barcode-scanner/open4goods-full-gtin-dataset-MIN.csv");

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        int i =0;
        for (CSVRecord record : records) {
            String code = record.get("code");
            String brand = record.get("brand");
            Barcodes barcode = new Barcodes();
            barcode.setBrand(brand);
            barcode.setCode(code);
            barcodeRepository.save(barcode);
            if(++i%10000 == 0)
                log.info(String.valueOf(i));
        }
        barcodeRepository.flush();
    }
}

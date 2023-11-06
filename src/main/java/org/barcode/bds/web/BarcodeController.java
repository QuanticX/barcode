package org.barcode.bds.web;

import org.barcode.bds.model.Barcodes;
import org.barcode.bds.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/api/barcodes")
    @ResponseBody
    public Barcodes getBarcodeByCode(@RequestParam String code) {
        return barcodeService.getByBarcode(code).get();
    }
}

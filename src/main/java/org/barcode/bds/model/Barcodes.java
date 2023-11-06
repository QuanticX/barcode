package org.barcode.bds.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "barcodes",
        indexes = {@Index(name = "idx_code", columnList="code")})
public class Barcodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
//    private String model;
    private String brand;
//    private String last_updated;
//    private String gs1_country;
//    private String categories;


}

package org.barcode.bds.repository;

import org.barcode.bds.model.Barcodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcodes, Long> {

    Optional<Barcodes> findByCode(String code);
}

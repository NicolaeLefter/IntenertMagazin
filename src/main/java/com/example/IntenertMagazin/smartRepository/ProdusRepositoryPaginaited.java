package com.example.IntenertMagazin.smartRepository;

import com.example.IntenertMagazin.entity.Produs;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdusRepositoryPaginaited extends JpaRepository<Produs, Integer> {

    Page<Produs> findAll(Pageable pageable);
    Page<Produs> findByDenProdusContaining(String denProd, Pageable pageable);

}

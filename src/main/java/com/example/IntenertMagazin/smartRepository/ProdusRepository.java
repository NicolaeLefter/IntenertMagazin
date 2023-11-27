package com.example.IntenertMagazin.smartRepository;

import com.example.IntenertMagazin.entity.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdusRepository extends JpaRepository<Produs, Integer> {


}

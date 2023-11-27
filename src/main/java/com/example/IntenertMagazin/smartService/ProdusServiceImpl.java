package com.example.IntenertMagazin.smartService;

import com.example.IntenertMagazin.entity.Categorie;
import com.example.IntenertMagazin.entity.Produs;
import com.example.IntenertMagazin.exception.ProdusNotFoundException;
import com.example.IntenertMagazin.smartRepository.ProdusRepositoryPaginaited;
import com.example.IntenertMagazin.smartRepository.ProdusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProdusServiceImpl {
    @Autowired
    ProdusRepository produsRepository;
    @Autowired
    ProdusRepositoryPaginaited produsRepositoryPaginaited;

    @Transactional
    public ResponseEntity<Object> updateProduct(Produs produs, int idProd) throws ProdusNotFoundException{
        Produs prod = produsRepository.findById(idProd).orElseThrow(() ->
                new ProdusNotFoundException("Nu a fost gasit produsul cu id-ul: " + idProd));
        Map<Object, String> map = new HashMap<>();

        if (produs.getDenProdus().length() < 0) {
            map.put(produs.getDenProdus(), "Denumirea produsului nu trebuie sa fie null!");
            prod.setDenProdus(produs.getDenProdus());
            if ((produs.getCategory() != Categorie.valueOf(produs.getCategory().name()))) {
                map.put(produs.getCategory(), "Categoria produsului nu este inclusa in Enum Categroy!");
                prod.setCategory(produs.getCategory());
                if (produs.getPrice() <= 0) {
                    map.put(produs.getPrice(), "Pretul produsului trebuie sa fie mai mare de 0!");
                }
                prod.setPrice(produs.getPrice());
                prod.setStoc(produs.getStoc());
                prod.setRamMemory(produs.getRamMemory());
                if (produs.getTypeMemory().equals("HDD") || produs.getTypeMemory().equals("SSD")){
                    prod.setTypeMmeory(prod.getTypeMemory());
                }else {
                    map.put(produs.getTypeMemory(), "Tipul memorie poate sa fie HDD sau SSD");
                }

                if (!map.isEmpty()) {
                    throw  new RuntimeException((Throwable) map);
                }
                produsRepository.save(prod);
            }
        }
        return ResponseEntity.ok("Produsul a fost actualizat cu succes!");
    }
    public Page<Produs> getAll(Integer pageNumber, Integer size, String sortBy,
                               Boolean desc, Integer minPrice, Integer maxPrice){
        Pageable page;
        List<Produs> produsList = new ArrayList<>();
        if (desc){
            page = PageRequest.of(pageNumber,size, Sort.by(sortBy).descending());
        }else {
            page = PageRequest.of(pageNumber,size,Sort.by(sortBy).ascending());
        }
        if (minPrice != null || maxPrice != null){
            produsList = produsRepositoryPaginaited.findAll(page).stream()
                    .filter((a)-> (minPrice == null) || minPrice <=a.getPrice())
                    .filter((a)-> (maxPrice == null) || maxPrice >=a.getPrice())
                    .collect(Collectors.toList());
            return new PageImpl<>(produsList);
        }
        return produsRepositoryPaginaited.findAll(page);
    }

    public Page<Produs> getProdusByDenProd(String denProd, Integer pageNumber, Integer size, String sortBy,
                                           Boolean desc){
        Pageable page;
        if (desc){
            page = PageRequest.of(pageNumber,size, Sort.by(sortBy).descending());
        }else {
            page = PageRequest.of(pageNumber,size,Sort.by(sortBy).ascending());
        }
        return produsRepositoryPaginaited.findByDenProdusContaining(denProd,page);
    }




}

package com.example.IntenertMagazin.smartService;

import com.example.IntenertMagazin.dto.ProdusDTO;
import com.example.IntenertMagazin.entity.Categorie;
import com.example.IntenertMagazin.entity.Produs;
import com.example.IntenertMagazin.exception.ProdusNotFoundException;
import com.example.IntenertMagazin.exception.ProdusUpdateException;
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
    public Produs updateProduct(ProdusDTO produsDTO, int idProd) throws ProdusNotFoundException, ProdusUpdateException {
        Produs prod = produsRepository.findById(idProd).orElseThrow(() ->
                new ProdusNotFoundException("Nu a fost gasit produsul cu id-ul: " + idProd));

         // if(produsDTO.getDenProdus() == null){
             //  throw  new ProdusUpdateException("Denumirea produsului este obligatorie");
          // }
           prod.setDenProdus(produsDTO.getDenProdus());
          // if (produsDTO.getCategory() == null) {
               //throw new ProdusUpdateException("Categoria este obligatorie!");
         //  }
           prod.setCategory(produsDTO.getCategory());

               // if (produsDTO.getPrice() <= 0) {
                    //throw  new ProdusUpdateException("Pretul produsului trebuie sa fie mai mare sau egal cu 0!");
              //  }
                prod.setPrice(produsDTO.getPrice());
                prod.setStoc(produsDTO.getStoc());
                prod.setRamMemory(produsDTO.getRamMemory());
             //   if (produsDTO.getTypeMemory().equals("HDD") || produsDTO.getTypeMemory().equals("SSD")){
                  //  prod.setTypeMmeory(prod.getTypeMemory());
            //    }else {
              //      throw new ProdusUpdateException("Tipul memorie nu corespunde cerinteloe!");
            //    }

                produsRepository.save(prod);
                //produsDTO.setIdProd(prod.getId());
                return  prod;
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

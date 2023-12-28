package com.example.IntenertMagazin;

import com.example.IntenertMagazin.entity.Categorie;
import com.example.IntenertMagazin.entity.Produs;
import com.example.IntenertMagazin.exception.ProdusNotFoundException;
import com.example.IntenertMagazin.exception.ProdusUpdateException;
import com.example.IntenertMagazin.smartRepository.ProdusRepository;
import com.example.IntenertMagazin.smartService.ProdusServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.sampled.Port;
import java.util.List;

@SpringBootTest
public class ProdusRepositoryTeste {

    @Autowired
    ProdusRepository produsRepository;
    @Autowired
    ProdusServiceImpl produsService;



    @Test
    public void addProdusTeste(){
        //Before

        List<Produs> produsListBefore = produsRepository.findAll();
        Assertions.assertEquals(1, produsListBefore.size());
        Assertions.assertFalse(produsListBefore.contains(new Produs(2)));

        //Given
        Produs produs = new Produs(2);
        produsRepository.save(produs);

        //After

        List<Produs> produsListAfter = produsRepository.findAll();
        Assertions.assertEquals(2, produsListAfter.size());
        Assertions.assertFalse(produsListAfter.contains(new Produs(2)));


    }
 /*  @Test
    public void updateProdusSucces() throws ProdusNotFoundException, ProdusUpdateException {
        //Before
        Produs produsBefore = produsRepository.findById(1).get();
        Assertions.assertEquals(250.00, produsBefore.getPrice());
        Assertions.assertEquals(1, produsBefore.getIdStore());

        //Given
        Produs produs1 = new Produs("SAMSUNG S10", Categorie.SMARTPHONE,150.00, true,"128GB","SSD",1);
        produsService.updateProduct(produs1,1);

        //After

        Produs produsAfter = produsRepository.findById(1).get();
        Assertions.assertEquals(150.00, produsAfter.getPrice());
        Assertions.assertEquals(1, produsAfter.getIdStore());

    } */
    @Test
    public void getAllTest(){

        List<Produs> produsList = produsRepository.findAll();
        Assertions.assertEquals(1, produsList.size());

        Produs produs = produsList.get(0);
        Assertions.assertEquals("SAMSUNG S20", produs.getDenProdus());


    }

}

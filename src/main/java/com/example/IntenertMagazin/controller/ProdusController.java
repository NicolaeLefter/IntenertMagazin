package com.example.IntenertMagazin.controller;

import com.example.IntenertMagazin.dto.ProdusDTO;
import com.example.IntenertMagazin.entity.Produs;
import com.example.IntenertMagazin.exception.ProdusNotFoundException;
import com.example.IntenertMagazin.exception.ProdusUpdateException;
import com.example.IntenertMagazin.smartRepository.ProdusRepository;
import com.example.IntenertMagazin.smartService.ProdusServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smart")
public class ProdusController {
    @Autowired
    ProdusRepository produsRepository;
    @Autowired
    ProdusServiceImpl produsService;

    @PostMapping("/save/produs")
    public ResponseEntity<Object> saveProdus(@RequestBody Produs produs) {
        return ResponseEntity.status(HttpStatus.OK).body(produsRepository.save(produs));

    }

    @GetMapping("/save/all")
    public ResponseEntity<Object> saveAllProdus(@RequestBody List<Produs> produsList) {
        return ResponseEntity.status(HttpStatus.OK).body(produsRepository.saveAll(produsList));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produs> updateProdus(@Valid @RequestBody ProdusDTO produsDTO,
                                                  @PathVariable int id) throws ProdusNotFoundException, ProdusUpdateException {

       // return ResponseEntity.status(HttpStatus.OK).body(produsService.updateProduct(produs, id));

        return ResponseEntity.ok(produsService.updateProduct(produsDTO,id));
    }

    @GetMapping("/get/all")
    public List<Produs> getAll() {
        return produsRepository.findAll();
    }

    @DeleteMapping("/delete/prodById/{id}")
    public void deteleProdusById(@PathVariable int id) {
        produsRepository.deleteById(id);

    }

    @GetMapping("/get/allProdus")
    public ResponseEntity<Object> getAllProdus(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "4") Integer size,
                                               @RequestParam(defaultValue = "id") String sortBy,
                                               @RequestParam(defaultValue = "false") Boolean desc,
                                               @RequestParam Integer minPrice,
                                               @RequestParam Integer maxPrice
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(produsService.getAll
                (pageNumber, size, sortBy, desc, minPrice, maxPrice));
    }

    @GetMapping("/get/byDenProd")
    public ResponseEntity<Object> getProByDenProd(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(defaultValue = "4") Integer size,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "false") Boolean desc,
                                                  @RequestParam String denProd) {
        return ResponseEntity.status(HttpStatus.OK).body(produsService.getProdusByDenProd(denProd, pageNumber,
                size, sortBy, desc));
    }
}

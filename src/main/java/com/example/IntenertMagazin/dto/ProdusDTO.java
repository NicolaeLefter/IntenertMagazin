package com.example.IntenertMagazin.dto;

import com.example.IntenertMagazin.entity.Categorie;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

public class ProdusDTO {


    private Integer id;
    @NotEmpty //daca denumirea o sa fie egala cu null sau "" atunci se arunca erorare
    @Size(min = 3, message = "Denumirea produsului trebuie sa contina minim 3 caractere!")
    private String denProdus;

    @NotNull(message = "Categoria este obligatorie!")
    private Categorie category;
    @Max(value = 1000, message = "Suma maxima este 1000")
    @Min(value = 1, message = "Suma minima este 1!")
    private Double price;

    private Boolean stoc;

    private String ramMemory;
    @NotNull(message = "Not null")
    private TypeMemory typeMemory;

    private Integer idStore;

    public ProdusDTO(Integer id) {
        this.id = id;
    }

    public ProdusDTO() {

    }

    public ProdusDTO(String denProdus, Categorie category, Double price, Boolean stoc, String ramMemory, TypeMemory typeMemory, Integer idStore) {
        this.denProdus = denProdus;
        this.category = category;
        this.price = price;
        this.stoc = stoc;
        this.ramMemory = ramMemory;
        this.typeMemory = typeMemory;
        this.idStore = idStore;

    }

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }

    public Integer getId() {
        return id;
    }

    public void setIdProd(Integer id) {
        this.id = id;
    }

    public String getDenProdus() {
        return denProdus;
    }

    public void setDenProdus(String denProdus) {
        this.denProdus = denProdus;
    }

    public Categorie getCategory() {
        return category;
    }

    public void setCategory(Categorie category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStoc() {
        return stoc;
    }

    public void setStoc(Boolean stoc) {
        this.stoc = stoc;
    }

    public String getRamMemory() {
        return ramMemory;
    }

    public void setRamMemory(String ramMemory) {
        this.ramMemory = ramMemory;
    }

    public TypeMemory getTypeMemory() {
        return typeMemory;
    }

    public void setTypeMmeory(TypeMemory typeMemory) {
        this.typeMemory = typeMemory;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProd=" + id +
                ", denProdus='" + denProdus + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", stoc=" + stoc +
                ", ramMemory='" + ramMemory + '\'' +
                ", typeMmeory='" + typeMemory + '\'' +
                ", idStore=" + idStore +
                '}';
    }
}



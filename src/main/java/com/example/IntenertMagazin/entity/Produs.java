package com.example.IntenertMagazin.entity;

import jakarta.persistence.*;

@Entity
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Integer id;
    @Column(name = "den_prod")
    private String denProdus;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Categorie category;
    @Column(name = "price")
    private Double price;
    @Column(name = "stoc")
    private Boolean stoc;
    @Column(name = "ram_memory")
    private String ramMemory;
    @Column(name = "type_memory")
    private String typeMemory;
    @Column(name = "id_store")
    private Integer idStore;

    public Produs(Integer id){
        this.id = id;
    }

    public Produs(){

    }

    public Produs(String denProdus, Categorie category, Double price, Boolean stoc, String ramMemory, String typeMemory, Integer idStore) {
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

    public String getTypeMemory() {
        return typeMemory;
    }

    public void setTypeMmeory(String typeMemory) {
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

package com.example.IntenertMagazin.entity;

import jakarta.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "den_store")
    private String denStore;
    @Column(name = "id_store")
    private Integer idStore;
    @Column(name = "adress")
    private String adress;

    public String getDenStore() {
        return denStore;
    }

    public void setDenStore(String denStore) {
        this.denStore = denStore;
    }

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Store{" +
                "denStore='" + denStore + '\'' +
                ", idStore=" + idStore +
                ", adress='" + adress + '\'' +
                '}';
    }
}

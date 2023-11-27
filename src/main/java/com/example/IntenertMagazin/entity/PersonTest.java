package com.example.IntenertMagazin.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component(value = "personTest")

public class PersonTest implements PersonInterface  {

    public void test(){
        System.out.println("Hello World!");
    }

}

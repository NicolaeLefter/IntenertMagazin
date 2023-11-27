package com.example.IntenertMagazin.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {
    @Bean(name = "personTest")
    public PersonInterface getPersonInterfaceImpl(){
     return new PersonTest();
    }
    @Bean(name = "personTest2")
    public PersonInterface getPersonInterfaceImpl2(){
        return new PersonTest2();
    }

}

package com.example.IntenertMagazin.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {
    @Bean(name = "personTest1")
    public PersonInterface getPersonInterfaceImpl(){
     return new PersonTest1();
    }
    @Bean(name = "personTest2")
    public PersonInterface getPersonInterfaceImpl2(){
        return new PersonTest2();
    }

}

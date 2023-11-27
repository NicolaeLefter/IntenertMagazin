package com.example.IntenertMagazin.smartService;

import com.example.IntenertMagazin.entity.PersonInterface;
import com.example.IntenertMagazin.entity.PersonTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    @Qualifier(value = "personTest2")
    PersonInterface personTest;

    public void call() {
        personTest.test();
    }
}

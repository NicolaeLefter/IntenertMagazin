package com.example.IntenertMagazin.produsIntegrationTest;

import com.example.IntenertMagazin.IntenertMagazinApplication;
import com.example.IntenertMagazin.dto.ProdusDTO;
import com.example.IntenertMagazin.dto.TypeMemory;
import com.example.IntenertMagazin.entity.Categorie;
import com.example.IntenertMagazin.entity.Produs;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IntenertMagazinApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdusIntegrationTest {
    @LocalServerPort
    Integer port;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void produsControllerGetTest() throws JSONException {
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/smart/get/all",
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        String expected = "[{\"id\":1,\"denProdus\":\"SAMSUNG S21\",\"category\":\"SMARTPHONE\",\"price\":250.0,\"stoc\":true,\"ramMemory\":\"64GB\",\"typeMemory\":\"SSD\",\"idStore\":1}]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    public void produsControllerUpdateTest() throws JSONException {
        ProdusDTO produs = new ProdusDTO("Iphone 15 PRO MAX", Categorie.SMARTPHONE, 300.0, true, "64GB", TypeMemory.SSD, 1);
        HttpEntity<ProdusDTO> httpEntity = new HttpEntity<>(produs, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/smart/update/1",
                HttpMethod.PUT,
                httpEntity,
                String.class
        );
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"denProdus\": \"Iphone 15 PRO MAX\",\n" +
                "    \"category\": \"SMARTPHONE\",\n" +
                "    \"price\": 300.0,\n" +
                "    \"stoc\": true,\n" +
                "    \"ramMemory\": \"64GB\",\n" +
                "    \"typeMemory\": \"SSD\",\n" +
                "    \"idStore\": 1\n" +
                "}";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    public void produsControllerUpdateTestEror() throws JSONException {
        ProdusDTO produsDTO = new ProdusDTO("Ip", null, 0.0, true, "64GB", null, 1);
        HttpEntity<ProdusDTO> httpEntity = new HttpEntity<>(produsDTO, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/smart/update/1",
                HttpMethod.PUT,
                httpEntity,
                String.class
        );
        String excepted = "{\n" +
                "    \"price\": \"Suma minima este 1!\",\n" +
                "    \"typeMemory\": \"Not null\",\n" +
                "    \"category\": \"Categoria este obligatorie!\",\n" +
                "    \"denProdus\": \"Denumirea produsului trebuie sa contina minim 3 caractere!\"\n" +
                "}";
        JSONAssert.assertEquals(excepted, responseEntity.getBody(), false);
    }

    @Test
    public void produsControllerAddTest() throws JSONException {

        //Pregatim produsul pentru adaugare
        ProdusDTO produsDTO = new ProdusDTO("Lenovo XR", Categorie.LAPTOP, 500.0, true, "64GB", TypeMemory.HDD, 1);
        // Se realizează o cerere HTTP POST către controlerul responsabil cu adăugarea produsului
        HttpEntity<ProdusDTO> httpEntity = new HttpEntity<>(produsDTO, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/smart/save/produs",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        //Verificam daca am primit corect raspunsul de la serves
        String excepted = "{\n" +
                "    \"denProdus\": \"Lenovo XR\",\n" +
                "    \"category\": \"LAPTOP\",\n" +
                "    \"price\": 500.0,\n" +
                "    \"stoc\": true,\n" +
                "    \"ramMemory\": \"64GB\",\n" +
                "    \"typeMemory\": \"HDD\",\n" +
                "    \"idStore\": 1\n" +
                "}";
        JSONAssert.assertEquals(excepted, responseEntity.getBody(), false);
    }

    @Test
    public void produsControllerGetProdusByDenTest() {
        HttpEntity<ProdusDTO> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/smart/get/byDenProd?denProd=iphone&desc=false",
                HttpMethod.GET,
                httpEntity,
                String.class
        );
        String excepted = "{\n" +
                "            \"id\": 1,\n" +
                "            \"denProdus\": \"SAMSUNG S21\",\n" +
                "            \"category\": \"SMARTPHONE\",\n" +
                "            \"price\": 250.0,\n" +
                "            \"stoc\": true,\n" +
                "            \"ramMemory\": \"64GB\",\n" +
                "            \"typeMemory\": \"SSD\",\n" +
                "            \"idStore\": 1\n" +
                "        }";
    }

}

package dev.afcasco.fctfinderrct.controller;

import dev.afcasco.fctfinderrct.model.CompanyDTO;
import dev.afcasco.fctfinderrct.respository.CompanyRepositoryTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CompanyControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testListCompanies() {
        webTestClient.get().uri(CompanyController.COMPANY_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);

    }

    @Test
    void testGetCompanyByIdFound() {
        Integer companyId = 1;

        webTestClient.get().uri(CompanyController.COMPANY_PATH_ID, companyId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(CompanyDTO.class);
    }

    @Test
    void testCreateNewCompany() {
        webTestClient.post().uri(CompanyController.COMPANY_PATH)
                .body(Mono.just(CompanyRepositoryTest.getTestCompany()), CompanyDTO.class)
                .header("Content-type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v1/company/4")
                .expectBody(CompanyDTO.class);
    }


    @Test
    void testUpdateCompany() {
        final Integer companyId = 1;
        webTestClient.put().uri(CompanyController.COMPANY_PATH_ID,companyId)
                .body(Mono.just(CompanyRepositoryTest.getTestCompany()),CompanyDTO.class)
                .header("Content-type","application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteCompanyById() {
        final Integer companyId = 1;
        webTestClient.delete().uri(CompanyController.COMPANY_PATH_ID,companyId)
                .exchange()
                .expectStatus().isNoContent();



    }
}
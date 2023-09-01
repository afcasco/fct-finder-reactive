package dev.afcasco.fctfinderrct.bootstrap;

import dev.afcasco.fctfinderrct.respository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BootStrapDataTest {


    BootStrapData bootStrapData;
    @Autowired
    CompanyRepository companyRepository;


    @BeforeEach
    void setUp(){
        bootStrapData = new BootStrapData(companyRepository);
    }



    @Test
    void testReadCsvFile() throws Exception {
        bootStrapData.loadFromCsv();
        companyRepository.count().subscribe(System.out::println);

    }

}
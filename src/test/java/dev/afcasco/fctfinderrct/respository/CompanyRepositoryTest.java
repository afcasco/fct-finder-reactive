package dev.afcasco.fctfinderrct.respository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.afcasco.fctfinderrct.config.DatabaseConfig;
import dev.afcasco.fctfinderrct.domain.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void saveNewCompany() {
        companyRepository.save(getTestCompany()).subscribe(System.out::println);

    }

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestCompany()));

    }

    public static Company getTestCompany(){
        return Company.builder()
                .name("fake company")
                .address("lollipop street 123")
                .phone("123456789")
                .cif("b12345678")
                .zipCode(17200)
                .status("test")
                .city("Girona")
                .build();
    }
}
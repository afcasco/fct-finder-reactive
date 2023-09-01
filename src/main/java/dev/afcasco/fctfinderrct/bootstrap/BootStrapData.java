package dev.afcasco.fctfinderrct.bootstrap;

import dev.afcasco.fctfinderrct.domain.Company;
import dev.afcasco.fctfinderrct.respository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCompanyData();
        //loadFromCsv();
        Mono<Long> count = companyRepository.count().single();

        count.subscribe(entries -> log.info("--> Loaded " +entries+ " companies into the database <--"));
    }

    private void loadCompanyData() {
        companyRepository.count().subscribe(count -> {
            if (count == 0) {
                Company company1 = Company.builder()
                        .name("1. fake company")
                        .address("lollipop street 123")
                        .phone("12344566")
                        .cif("b55526646")
                        .zipCode(17200)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .status("test")
                        .build();

                Company company2 = Company.builder()
                        .name("2. fake company")
                        .address("lollipop street 123")
                        .phone("12344566")
                        .cif("b55526646")
                        .zipCode(17200)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .status("test")
                        .build();

                Company company3 = Company.builder()
                        .name("3. fake company")
                        .address("lollipop street 123")
                        .phone("12344566")
                        .cif("b55526646")
                        .zipCode(17200)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .status("test")
                        .build();

                companyRepository.saveAll(List.of(company1, company2, company3)).subscribe();
            }
        });


    }

    public void loadFromCsv() throws IOException {
        File file = ResourceUtils.getFile("classpath:empreses.csv");

        List<Company> companies;

        try (Stream<Company> companyStream = Files.lines(file.toPath()).map(this::mapCsvLineToPojo)) {
            companies = companyStream.toList();
            companyRepository.saveAll(companies).subscribe();
        }


    }

    private Company mapCsvLineToPojo(String line) {
        String[] properties = line.split("\\|");
        String nameAndCif = properties[0];
        String address = properties[2];
        Integer zipCode = Integer.valueOf(properties[3]);
        String city = properties[4];
        String phone = properties[5];

        return Company.builder()
                .name(nameAndCif.substring(1, nameAndCif.indexOf("(")).trim())
                .cif(nameAndCif.substring(nameAndCif.indexOf("(") + 1, nameAndCif.indexOf(")")))
                .address(address.replace("\"", ""))
                .zipCode(zipCode)
                .city(city.replace("\"", ""))
                .phone(phone)
                .build();
    }

}

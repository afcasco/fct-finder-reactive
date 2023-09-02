package dev.afcasco.fctfinderrct.controller;

import dev.afcasco.fctfinderrct.model.CompanyDTO;
import dev.afcasco.fctfinderrct.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class CompanyController {

    public static final String COMPANY_PATH = "/api/v1/company";
    public static final String COMPANY_PATH_ID = COMPANY_PATH + "/{companyId}";

    private final CompanyService companyService;

    @GetMapping(COMPANY_PATH)
    Flux<CompanyDTO> listCompanies() {
        return companyService.listCompanies();
    }

    @GetMapping(COMPANY_PATH_ID)
    Mono<CompanyDTO> getCompanyById(@PathVariable Long companyId) {
        return companyService.getCompanyById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(COMPANY_PATH)
    Mono<ResponseEntity<Void>> createCompany(@Validated @RequestBody CompanyDTO companyDTO) {
        return companyService.saveNewCompany(companyDTO)
                .map(savedDto -> ResponseEntity.created(UriComponentsBuilder.fromHttpUrl(
                                "http://localhost:8080" + COMPANY_PATH +
                                        "/" + savedDto.getId())
                        .build().toUri()
                ).build());
    }

    @PutMapping(COMPANY_PATH_ID)
    Mono<ResponseEntity<Void>> updateExistingCompany(@Validated @RequestBody CompanyDTO companyDTO,
                                                     @PathVariable Long companyId) {
        return companyService.updateCompany(companyId, companyDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(savedDto -> ResponseEntity.noContent().build());
    }

    @PatchMapping(COMPANY_PATH_ID)
    Mono<ResponseEntity<Void>> patchExistingCompany(@Validated @RequestBody CompanyDTO companyDTO,
                                              @PathVariable Long companyId) {
        return companyService.patchCompany(companyId,companyDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(savedDto -> ResponseEntity.ok().build());
    }

    @DeleteMapping(COMPANY_PATH_ID)
    Mono<ResponseEntity<Void>> deleteCompany(@PathVariable Long companyId){
        return companyService.getCompanyById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(companyDTO -> companyService.deleteCompany(companyId))
                .thenReturn(ResponseEntity.noContent().build());
    }
}

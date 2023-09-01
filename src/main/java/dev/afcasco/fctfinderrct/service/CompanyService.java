package dev.afcasco.fctfinderrct.service;

import dev.afcasco.fctfinderrct.model.CompanyDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {

    Flux<CompanyDTO> listCompanies();

    Mono<CompanyDTO> getCompanyById(Long companyId);

    Mono<CompanyDTO> saveNewCompany(CompanyDTO companyDTO);

    Mono<CompanyDTO> updateCompany(Long companyId, CompanyDTO companyDTO);

    Mono<CompanyDTO> patchCompany(Long companyId, CompanyDTO companyDTO);

    Mono<Void> deleteCompany(Long companyId);

}

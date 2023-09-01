package dev.afcasco.fctfinderrct.service;

import dev.afcasco.fctfinderrct.mapper.CompanyMapper;
import dev.afcasco.fctfinderrct.model.CompanyDTO;
import dev.afcasco.fctfinderrct.respository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Flux<CompanyDTO> listCompanies() {
        return companyRepository.findAll().map(companyMapper::companyToCompanyDto);
    }

    @Override
    public Mono<CompanyDTO> getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).map(companyMapper::companyToCompanyDto);
    }

    @Override
    public Mono<CompanyDTO> saveNewCompany(CompanyDTO companyDTO) {
        return companyRepository.save(companyMapper.companyDtoToCompany(companyDTO))
                .map(companyMapper::companyToCompanyDto);
    }

    @Override
    public Mono<CompanyDTO> updateCompany(Long companyId, CompanyDTO companyDTO) {
        return companyRepository.findById(companyId).map(existingCompany -> {
                    existingCompany.setName(companyDTO.getName());
                    existingCompany.setCity(companyDTO.getCity());
                    existingCompany.setCif(companyDTO.getCif());
                    existingCompany.setAddress(companyDTO.getAddress());
                    existingCompany.setPhone(companyDTO.getPhone());
                    existingCompany.setZipCode(companyDTO.getZipCode());
                    existingCompany.setStatus(companyDTO.getStatus());
                    return existingCompany;
                })
                .flatMap(companyRepository::save)
                .map(companyMapper::companyToCompanyDto);
    }

    @Override
    public Mono<CompanyDTO> patchCompany(Long companyId, CompanyDTO companyDTO) {
        return companyRepository.findById(companyId).map(existingCompany -> {
                    if (StringUtils.hasText(companyDTO.getCity())) {
                        existingCompany.setCity(companyDTO.getCity());
                    }

                    if (StringUtils.hasText(companyDTO.getCif())) {
                        existingCompany.setCif(companyDTO.getCif());
                    }

                    if (StringUtils.hasText(companyDTO.getAddress())) {
                        existingCompany.setAddress(companyDTO.getAddress());
                    }

                    if (StringUtils.hasText(companyDTO.getName())) {
                        existingCompany.setName(companyDTO.getName());
                    }

                    if (companyDTO.getZipCode() != null) {
                        existingCompany.setZipCode(companyDTO.getZipCode());
                    }

                    if (StringUtils.hasText(companyDTO.getStatus())) {
                        existingCompany.setStatus(companyDTO.getStatus());
                    }

                    if (StringUtils.hasText(companyDTO.getPhone())) {
                        existingCompany.setPhone(companyDTO.getPhone());
                    }

                    return existingCompany;
                })
                .flatMap(companyRepository::save)
                .map(companyMapper::companyToCompanyDto);
    }

    @Override
    public Mono<Void> deleteCompany(Long companyId) {
        return companyRepository.deleteById(companyId);
    }
}

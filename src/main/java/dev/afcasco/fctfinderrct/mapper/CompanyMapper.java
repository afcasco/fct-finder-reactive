package dev.afcasco.fctfinderrct.mapper;

import dev.afcasco.fctfinderrct.domain.Company;
import dev.afcasco.fctfinderrct.model.CompanyDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {

    Company companyDtoToCompany(CompanyDTO companyDTO);

    CompanyDTO companyToCompanyDto(Company company);
}

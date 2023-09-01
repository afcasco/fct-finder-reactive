package dev.afcasco.fctfinderrct.respository;

import dev.afcasco.fctfinderrct.domain.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompanyRepository extends ReactiveCrudRepository<Company,Long> {
}

package dev.afcasco.fctfinderrct.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    private Long id;
    private String cif;
    private Integer zipCode;
    private String city;
    private String address;
    private String name;
    private String phone;
    private String status;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}


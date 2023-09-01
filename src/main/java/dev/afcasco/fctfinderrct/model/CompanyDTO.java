package dev.afcasco.fctfinderrct.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {

    private Long id;

    @NotBlank
    @Size(min = 8, max = 9)
    private String cif;

    @NotNull
    private Integer zipCode;

    @NotBlank
    @Size(max = 255)
    private String city;

    @NotBlank
    @Size(min =4,max = 255)
    private String address;

    @NotBlank
    @Size(min = 4, max = 255)
    private String name;

    @NotBlank
    @Size(min = 9, max = 25)
    private String phone;

    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}

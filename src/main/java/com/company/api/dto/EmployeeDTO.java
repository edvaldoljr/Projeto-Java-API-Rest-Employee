package com.company.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {

    private String name;
    private Integer age;
    private String cpf;
    private String celullar;
    private String office;
    private String sector;
    private Double wage; //salario
}

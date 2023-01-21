package com.company.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "TB_EMPLOYEE")
public class EmployeeEntity implements Serializable {

    private static final long attachments = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age; //idade
    private String cpf;
    private String celullar;
    private String office; //cargo
    private String sector; //setor
    private Double wage; //salario



}

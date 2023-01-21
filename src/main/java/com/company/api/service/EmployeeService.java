package com.company.api.service;

import com.company.api.dto.EmployeeDTO;
import com.company.api.entity.EmployeeEntity;
import com.company.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    //POST
    public void insertEmployee(EmployeeDTO employee) {

        EmployeeEntity newEmployee = new EmployeeEntity();

        newEmployee.setName(employee.getName());
        newEmployee.setAge(employee.getAge());
        newEmployee.setCpf(employee.getCpf());
        newEmployee.setOffice(employee.getOffice());
        newEmployee.setCelullar(employee.getCelullar());
        newEmployee.setSector(employee.getSector());
        newEmployee.setWage(employee.getWage());

        employeeRepository.save(newEmployee);
    }

    //GET
    public List<EmployeeDTO> searchEmployee() {

        List<EmployeeDTO> listEmployee = new ArrayList<>();

        employeeRepository.findAll().forEach(item->{

            EmployeeDTO employee = EmployeeDTO
                    .builder()
                    .name(item.getName())
                    .age(item.getAge())
                    .cpf(item.getCpf())
                    .office(item.getOffice())
                    .celullar(item.getCelullar())
                    .sector(item.getSector())
                    .wage(item.getWage())
                    .build();

            listEmployee.add(employee);
        });

        return listEmployee;
    }

}

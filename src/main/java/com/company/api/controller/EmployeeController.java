package com.company.api.controller;

import com.company.api.dto.EmployeeDTO;
import com.company.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //GET
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeeCompany() {
        return new ResponseEntity<>(employeeService.searchEmployee(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNewEmployee(@RequestBody EmployeeDTO employee) {
        try {
            employeeService.insertEmployee(employee);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

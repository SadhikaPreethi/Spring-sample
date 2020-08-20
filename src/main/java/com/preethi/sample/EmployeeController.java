package com.preethi.sample;

import com.preethi.sample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee/all")
    List<Employee> getEmployees() {
        List<Employee> employeeList = employeeService.prepareData();
        return employeeList;
    }

    @RequestMapping(value = "/employee/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") Integer id) {
        List<Employee> employeeList = employeeService.prepareData();
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                ResponseEntity responseEntity = new ResponseEntity(employee, HttpStatus.OK);
                return responseEntity;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "Employee not found");
        ResponseEntity responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Employee added succesfully");
        ResponseEntity responseEntity = new ResponseEntity(map, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        boolean updated = employeeService.updateMyEmployee(employee);
        Map<String, String> map = new HashMap<>();
        ResponseEntity responseEntity;

        if (updated) {
            map.put("message", "Employee updated succesfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } else {
            map.put("message", "Employee not found for update");
            responseEntity = new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    // Methods moved to EmployeeService
}
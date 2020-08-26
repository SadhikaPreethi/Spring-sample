package com.preethi.sample;

import com.preethi.sample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDataService {

    @Autowired
    EmployeeDataRepository repository;

    List<Employee> getAllMyEmployees(){
        List<Employee> employeeList = repository.getAllEmployee();
        return employeeList;
    }

    int addEmployee(Employee employee){
        return repository.addEmployeeToDB(employee);
    }

    int updateEmployee(Employee employee){
        return repository.updateEmployeeInDB(employee);
    }

    int deleteEmployee(int id) {
        return repository.deleteEmployeeInDB(id);
    }

    int countRecordsByDept(String dept) {
        return repository.countByDept(dept);
    }

    String findNameById(int id) {
        return repository.findNameById(id);
    }
}

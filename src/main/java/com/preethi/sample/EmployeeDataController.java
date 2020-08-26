package com.preethi.sample;



import com.preethi.sample.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeDataController {

    @Autowired
    EmployeeDataService service;

    @org.springframework.web.bind.annotation.RequestMapping(value = "/employee/data/all")
    List<Employee> getAllEmployees(){
        List<Employee> employeeList = service.getAllMyEmployees();
        return employeeList;
    }
    @RequestMapping(value = "/employee/data/add", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee employee){
        int rowsAffected = service.addEmployee(employee);
        return "SUCCESSFULLY inserted " + rowsAffected + " record";
    }

    @RequestMapping(value = "/employee/data/update", method = RequestMethod.PUT)
    String updateEmployee(@RequestBody Employee employee){
        int rowsAffected = service.updateEmployee(employee);
        return "SUCCESSFULLY updated " + rowsAffected + " record";
    }

    @RequestMapping(value = "/employee/data/delete/{id}", method = RequestMethod.DELETE)
    String deleteEmployee(@PathVariable("id") int id) {
        int rowsAffected = service.deleteEmployee(id);
        return "SUCCESSFULLY deleted " + rowsAffected + " record";
    }

    @RequestMapping(value = "/employee/data/count/{dept}", method = RequestMethod.GET)
    String countByDept(@PathVariable("dept") String dept) {
        int count = service.countRecordsByDept(dept);
        return "You have " + count + " Employees for the dept " + dept;
    }

    @RequestMapping(value = "/employee/data/name/{id}", method = RequestMethod.GET)
    String findNameById(@PathVariable("id") int id) {
        String name = service.findNameById(id);
        return "The name of the Employee is " + name;
    }
}

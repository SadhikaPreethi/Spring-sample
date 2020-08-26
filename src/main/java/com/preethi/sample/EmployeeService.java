package com.preethi.sample;
import com.preethi.sample.model.Employee;
import com.preethi.sample.model.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {

    List<Employee> employeeList = new ArrayList<>();

    public List<Employee> prepareData() {

        if(employeeList.isEmpty()) {
            Employee e1 = new Employee();
            e1.setId(1);
            e1.setName("Vidhya");

            Role role = new Role();
            role.setDesignation("Chief Engineer");
            role.setDept("IT Dept");

            e1.setRole(role);

            Employee e2 = new Employee();
            e2.setId(2);
            e2.setName("Vandhana");

            Role role2 = new Role();
            role2.setDesignation("The CEO");
            role2.setDept("Management");
            e2.setRole(role2);

            employeeList.add(e1);
            employeeList.add(e2);
        }
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        List<Employee> employeeList = prepareData();
        // TODO: Make sure you are checking before adding
        // TODO: Condition - Check if Employee already exist by using Id
        employeeList.add(employee);
    }

    public boolean updateMyEmployee(Employee tobeUpdatedEmployee) {
        List<Employee> employeeList = prepareData();

        Iterator<Employee> iterator = employeeList.iterator();
        boolean removed = false;

        while(iterator.hasNext()) {
            Employee employee = iterator.next();
            if(employee.getId() == tobeUpdatedEmployee.getId()) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if(removed) {
            employeeList.add(tobeUpdatedEmployee);
        }
        return removed;
    }
}

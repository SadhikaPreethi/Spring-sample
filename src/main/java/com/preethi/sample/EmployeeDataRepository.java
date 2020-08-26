package com.preethi.sample;

import com.preethi.sample.model.Employee;
import com.preethi.sample.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class EmployeeDataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Employee> getAllEmployee() {
        String query = "SELECT * FROM employee";

        List<Employee> employeeList = jdbcTemplate.query(query, new RowMapper<Employee>() {

            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));

                Role role = new Role();
                role.setDesignation(resultSet.getString("designation"));
                role.setDept(resultSet.getString("dept"));

                employee.setRole(role);
                return employee;
            }
        });

        return employeeList;
    }

    int addEmployeeToDB(Employee employee) {
        String query = "INSERT INTO employee (id, name, designation, dept) " +
                "values (?, ?, ?, ?)";
        int result = jdbcTemplate.update(query, employee.getId(), employee.getName()
                , employee.getRole().getDesignation(), employee.getRole().getDept());
        return result;
    }

    int updateEmployeeInDB(Employee employee) {
        String query = "UPDATE employee SET name=? WHERE id=?";

        int result = jdbcTemplate.update(query, employee.getName(), employee.getId());
        return result;
    }

    int deleteEmployeeInDB(int id) {
        String query = "DELETE FROM employee WHERE id=?";

        int result = jdbcTemplate.update(query, id);
        return result;
    }

    int countByDept(String dept){
        String query = "SELECT count(id) FROM employee where dept=?";

        int count = jdbcTemplate.queryForObject(query, Integer.class, dept);
        return count;
    }

    String findNameById(Integer id) {
        String query = "SELECT name FROM employee where id=?";

        String name = jdbcTemplate.queryForObject(query, String.class, id);
        return name;
    }
}

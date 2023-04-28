package BO.implementation;

import DTO.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface employeeBO {

    //Create
    boolean createEmployee(EmployeeDTO employee) throws SQLException;

    public Optional<EmployeeDTO> findEmployeeById(int id) throws SQLException;
    public Optional<EmployeeDTO> findEmployeeByName(String name) throws SQLException;
    public List<EmployeeDTO> findAllEmployees() throws SQLException;
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException;
    public void deleteEmployee(int id) throws SQLException;
}

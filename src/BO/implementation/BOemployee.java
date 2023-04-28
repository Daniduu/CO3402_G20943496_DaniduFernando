package BO.implementation;

import DO.implementation.DOemployee;
import DTO.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BOemployee implements employeeBO{

    private final DOemployee employeeDAO;

    public BOemployee(DOemployee employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public boolean createEmployee(EmployeeDTO employee) throws SQLException {
        return employeeDAO.create(new EmployeeDTO(employee.getEmpID(), employee.getEmpName()));
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeById(int id) throws SQLException {
        return employeeDAO.findById(id);
    }

    @Override
    public Optional<EmployeeDTO> findEmployeeByName(String name) throws SQLException {
        return employeeDAO.findByName(name);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() throws SQLException {
        return employeeDAO.findAll();
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException {
        return employeeDAO.update(new EmployeeDTO(employee.getEmpID(), employee.getEmpName()));
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        employeeDAO.delete(id);
    }


}

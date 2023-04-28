package DO.implementation;

import DTO.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DOemployee {

    public boolean create(EmployeeDTO employee) throws SQLException;
    public Optional<EmployeeDTO> findById(int id) throws SQLException;
    public Optional<EmployeeDTO> findByName(String name) throws SQLException;
    public List<EmployeeDTO> findAll() throws SQLException;
    public boolean update(EmployeeDTO employee) throws SQLException;
    public void delete(int id) throws SQLException;
    public EmployeeDTO mapResultSetToEmployee(ResultSet resultSet) throws SQLException;
}

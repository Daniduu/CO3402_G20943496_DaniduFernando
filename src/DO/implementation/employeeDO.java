package DO.implementation;

import DO.DatabaseUtil;
import DTO.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class employeeDO  implements DOemployee{

    @Override
    public boolean create(EmployeeDTO employee) throws SQLException {
        String query = "INSERT INTO employee (idemployee, employeeName) VALUES (?, ?)";
        DatabaseUtil.executeUpdate(query, employee.getEmpID(), employee.getEmpName());
        return false;
    }

    @Override
    public Optional<EmployeeDTO> findById(int id) throws SQLException {
        String query = "SELECT * FROM employee WHERE idemployee = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(query, id);

        if (resultSet != null && resultSet.next()) {
            return Optional.of(mapResultSetToEmployee(resultSet));
        }

        return Optional.empty();
    }

    @Override
    public Optional<EmployeeDTO> findByName(String name) throws SQLException {
        String query = "SELECT * FROM employee WHERE employeeName = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(query, name);

        if (resultSet != null && resultSet.next()) {
            return Optional.of(mapResultSetToEmployee(resultSet));
        }

        return Optional.empty();
    }

    @Override
    public List<EmployeeDTO> findAll() throws SQLException {
        String query = "SELECT * FROM employee";
        ResultSet resultSet = DatabaseUtil.executeQuery(query);
        List<EmployeeDTO> employees = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }
        }

        return employees;
    }

    @Override
    public boolean update(EmployeeDTO employee) throws SQLException {
        String query = "UPDATE employee SET employeeName = ? WHERE idemployee = ?";
        DatabaseUtil.executeUpdate(query, employee.getEmpName(), employee.getEmpID());
        return false;
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM employee WHERE id = ?";
        DatabaseUtil.executeUpdate(query, id);
    }

    public EmployeeDTO mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        return new EmployeeDTO(
                resultSet.getInt("idemployee"),
                resultSet.getString("employeeName")
        );
    }

}

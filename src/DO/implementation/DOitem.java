package DO.implementation;

import DTO.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DOitem {

    public boolean create(ItemDTO item) throws SQLException;
    public Optional<ItemDTO> findById(int id) throws SQLException;
    public Optional<ItemDTO> findByName(String name) throws SQLException;
    public List<ItemDTO> findAll() throws SQLException;
    public void update(ItemDTO item) throws SQLException;
    public void delete(int id) throws SQLException;
    public ItemDTO mapResultSetToItem(ResultSet resultSet) throws SQLException;
}

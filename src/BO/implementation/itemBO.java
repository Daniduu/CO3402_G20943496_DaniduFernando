package BO.implementation;

import DTO.ItemDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface itemBO {

    //Create
    boolean createItem (ItemDTO item) throws SQLException;
    public Optional<ItemDTO> findItemById(int id) throws SQLException;
    public Optional<ItemDTO> findItemByName(String name) throws SQLException;
    public List<ItemDTO> findAllItems() throws SQLException;
    public void updateItem(ItemDTO item) throws SQLException;
    public void deleteItem(int id) throws SQLException;


}

package BO.implementation;

import DO.implementation.DOitem;
import DTO.EmployeeDTO;
import DTO.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BOitem implements itemBO {

    private final DOitem itemDao;

    public BOitem(DOitem itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public boolean createItem(ItemDTO item) throws SQLException {
        return itemDao.create(new ItemDTO(item.getID(), item.getName(), item.getQuantity(), item.getDateCreated(), item.getEmpName(), item.getItemPrice()));
    }

    @Override
    public Optional<ItemDTO> findItemById(int id) throws SQLException {
        return itemDao.findById(id);
    }

    @Override
    public Optional<ItemDTO> findItemByName(String name) throws SQLException {
        return itemDao.findByName(name);
    }

    @Override
    public List<ItemDTO> findAllItems() throws SQLException {
        return itemDao.findAll();

//        List<ItemDTO> items=new ArrayList<>();
//        List<ItemDTO> loadAll = itemDao.findAll();
//        for (ItemDTO item : loadAll) {
//            items.add(new ItemDTO(item.getID(), item.getName(), item.getQuantity(), item.getDateCreated(), item.getEmpName(), item.getItemPrice()));
//        }
//        return items;
    }

    @Override
    public void updateItem(ItemDTO item) throws SQLException {
        itemDao.update(new ItemDTO(item.getID(), item.getName(), item.getQuantity(), item.getDateCreated(), item.getEmpName(), item.getItemPrice()));
    }

    @Override
    public void deleteItem(int id) throws SQLException {
        itemDao.delete(id);
    }
}

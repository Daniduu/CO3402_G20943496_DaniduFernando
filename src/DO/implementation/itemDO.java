package DO.implementation;

import DO.DatabaseUtil;
import DTO.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class itemDO  implements DOitem{


    @Override
    public boolean create(ItemDTO item) throws SQLException {
        String query = "INSERT INTO item (itemID, itemName, itemQuantity, dateCreated, employeeName, itemPrice) VALUES (?, ?, ?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(query, item.getID(), item.getName(), item.getQuantity(), item.getDateCreated(), item.getEmpName(), item.getItemPrice());
        return false;
    }

    public Optional<ItemDTO> findById(int id) throws SQLException {
        String query = "SELECT * FROM item WHERE itemID = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(query, id);

        if (resultSet != null && resultSet.next()) {
            return Optional.of(mapResultSetToItem(resultSet));
        }

        return Optional.empty();
    }

    @Override
    public Optional<ItemDTO> findByName(String name) throws SQLException {
        String query = "SELECT * FROM item WHERE itemName = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(query, name);

        if (resultSet != null && resultSet.next()) {
            return Optional.of(mapResultSetToItem(resultSet));
        }

        return Optional.empty();
    }

    @Override
    public List<ItemDTO> findAll() throws SQLException {
        String query = "SELECT * FROM item";
        ResultSet resultSet = DatabaseUtil.executeQuery(query);
        List<ItemDTO> items = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                items.add(mapResultSetToItem(resultSet));
            }
        }

        return items;
    }

    @Override
    public void update(ItemDTO item) throws SQLException {
        String query = "UPDATE item SET itemName = ?, itemQuantity = ?, dateCreated = ?, employeeName = ?, itemPrice = ? WHERE itemID = ?";
        DatabaseUtil.executeUpdate(query, item.getName(), item.getQuantity(), item.getDateCreated(), item.getEmpName(), item.getItemPrice(), item.getID());
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM item WHERE itemID = ?";
        DatabaseUtil.executeUpdate(query, id);
    }

    public ItemDTO mapResultSetToItem(ResultSet resultSet) throws SQLException {
        return new ItemDTO(
                resultSet.getInt("itemID"),
                resultSet.getString("itemName"),
                resultSet.getInt("itemQuantity"),
                resultSet.getObject("dateCreated", LocalDateTime.class),
                resultSet.getString("employeeName"),
                resultSet.getFloat("itemPrice")
        );
    }
}

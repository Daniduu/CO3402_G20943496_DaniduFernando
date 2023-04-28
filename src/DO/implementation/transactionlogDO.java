package DO.implementation;

import DO.DatabaseUtil;

import DTO.TransactionLogDTO;
import Entity.transactionlog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class transactionlogDO implements DOtransactionlog{

    public boolean create(TransactionLogDTO transactionlog) throws SQLException{

        String query = "INSERT INTO transactionlog (transactionID, transactionType, itemID, employeeName, dateCreated) VALUES (?, ?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(query, transactionlog.getTransactionID(), transactionlog.getTypeOfTransaction(), transactionlog.getItemID(), transactionlog.getEmployeeName(), transactionlog.getDateAdded());
        return false;
    }

    @Override
    public Optional<TransactionLogDTO> findById(int id) throws SQLException {
        String query = "SELECT * FROM transactionlog WHERE transactionID = ?";
        ResultSet resultSet = DatabaseUtil.executeQuery(query, id);

        if (resultSet != null && resultSet.next()) {
            return Optional.of(mapResultSetToTransactionLog(resultSet));
        }

        return Optional.empty();
    }

    @Override
    public List<TransactionLogDTO> findAll() throws SQLException {
        String query = "SELECT * FROM transactionlog";
        ResultSet resultSet = DatabaseUtil.executeQuery(query);
        List<TransactionLogDTO> transactionLogs = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                transactionLogs.add(mapResultSetToTransactionLog(resultSet));
            }
        }

        return transactionLogs;
    }

    public TransactionLogDTO mapResultSetToTransactionLog(ResultSet resultSet) throws SQLException {
        return new TransactionLogDTO(
                resultSet.getInt("transactionID"),
                resultSet.getString("transactionType"),
                resultSet.getInt("itemID"),
                resultSet.getString("employeeName"),
                resultSet.getObject("dateCreated", LocalDateTime.class)
        );
    }

}

package DO.implementation;

import DTO.TransactionLogDTO;
import Entity.transactionlog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DOtransactionlog {

    public boolean create(TransactionLogDTO transactionLog) throws SQLException;
    public Optional<TransactionLogDTO> findById(int id) throws SQLException;
    public List<TransactionLogDTO> findAll() throws SQLException;
    public TransactionLogDTO mapResultSetToTransactionLog(ResultSet resultSet) throws SQLException;
}

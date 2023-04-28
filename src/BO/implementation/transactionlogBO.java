package BO.implementation;

import DTO.TransactionLogDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface transactionlogBO {

    //Create
    boolean createTransactionLog(TransactionLogDTO transactionLog) throws SQLException;

    public Optional<TransactionLogDTO> findTransactionById(int id) throws SQLException;
    public Optional<TransactionLogDTO> findTransactionByName(String name) throws SQLException;
    public List<TransactionLogDTO> findAllTransactions() throws SQLException;
    public void updateTransaction(TransactionLogDTO transactionLog) throws SQLException;
    public void deleteTransaction(int id) throws SQLException;

}

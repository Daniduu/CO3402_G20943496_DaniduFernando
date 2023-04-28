package BO.implementation;

import DO.implementation.DOtransactionlog;
import DTO.ItemDTO;
import DTO.TransactionLogDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BOtransactionlog implements transactionlogBO {

    private final DOtransactionlog transactionLogDao;

    public BOtransactionlog(DOtransactionlog transactionLogDao) {

        this.transactionLogDao = transactionLogDao;
    }


    @Override
    public boolean createTransactionLog(TransactionLogDTO transactionlog) throws SQLException {
        return transactionLogDao.create(new TransactionLogDTO(transactionlog.getTransactionID(), transactionlog.getTypeOfTransaction(), transactionlog.getItemID(), transactionlog.getEmployeeName(), transactionlog.getDateAdded()));
    }

    @Override
    public Optional<TransactionLogDTO> findTransactionById(int id) throws SQLException {
        return transactionLogDao.findById(id);
    }

    @Override
    public Optional<TransactionLogDTO> findTransactionByName(String name) throws SQLException {
        return null;
        //return transactionLogDao.findByName(name);
    }

    @Override
    public List<TransactionLogDTO> findAllTransactions() throws SQLException {
        return transactionLogDao.findAll();
    }

    @Override
    public void updateTransaction(TransactionLogDTO transactionlog) throws SQLException {
        //transactionLogDao.update(new TransactionLogDTO(transactionlog.getTransactionID(), transactionlog.getTypeOfTransaction(), transactionlog.getItemID(), transactionlog.getEmployeeName(), transactionlog.getDateAdded()));
    }

    @Override
    public void deleteTransaction(int id) throws SQLException {
//        transactionLogDao.delete(id);
    }
}

package DTO;

import java.time.LocalDateTime;

public class TransactionLogDTO
{
    private int TransactionID;
	public String TypeOfTransaction;
    public int ItemID;
    public String EmployeeName;
    public LocalDateTime DateAdded;

    public TransactionLogDTO(int transactionID, String typeOfTransaction, int itemID, String employeeName, LocalDateTime dateAdded) {
        TransactionID = transactionID;
        TypeOfTransaction = typeOfTransaction;
        ItemID = itemID;
        EmployeeName = employeeName;
        DateAdded = dateAdded;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public String getTypeOfTransaction() {
        return TypeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        TypeOfTransaction = typeOfTransaction;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public LocalDateTime getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        DateAdded = dateAdded;
    }
}

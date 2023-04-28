package Entity;

import java.time.LocalDateTime;

public class transactionlog
{
    private int transactionID;
    private String transactionType;
    private int itemID;
    private String employeeName;
    private LocalDateTime dateCreated;

    public transactionlog() {
    }

    public transactionlog(int transactionID, String transactionType, int itemID, String employeeName, LocalDateTime dateCreated) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.itemID = itemID;
        this.employeeName = employeeName;
        this.dateCreated = dateCreated;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}

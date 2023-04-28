package Entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class item
{
    private int itemID;
    private String itemName;
    private int itemQuantity;
    private LocalDateTime dateCreated;
    private String employeeName;
    private float itemPrice;

    public item(int itemID, String itemName, int itemQuantity, Timestamp dateCreated, String employeeName, float itemPrice) {
    }

    public item(int itemID, String itemName, int itemQuantity, LocalDateTime dateCreated, String employeeName, float itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.dateCreated = dateCreated;
        this.employeeName = employeeName;
        this.itemPrice = itemPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}

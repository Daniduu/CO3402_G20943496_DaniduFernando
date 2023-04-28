package DTO;

import java.time.LocalDateTime;

public class ItemDTO
{
	public int ID;
    public String Name;
    public int Quantity;
    public LocalDateTime DateCreated;
    public String EmpName;
    public float ItemPrice;

    public ItemDTO(int ID, String name, int quantity, LocalDateTime dateCreated, String empName, float itemPrice) {
        this.ID = ID;
        Name = name;
        Quantity = quantity;
        DateCreated = dateCreated;
        EmpName = empName;
        ItemPrice = itemPrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public LocalDateTime getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        DateCreated = dateCreated;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public float getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(float itemPrice) {
        ItemPrice = itemPrice;
    }
}

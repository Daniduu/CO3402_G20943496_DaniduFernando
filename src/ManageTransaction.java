

import BO.implementation.*;
import DO.implementation.employeeDO;
import DO.implementation.itemDO;
import DO.implementation.transactionlogDO;
import DTO.EmployeeDTO;
import DTO.ItemDTO;
import DTO.TransactionLogDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ManageTransaction
{
 
    private static final itemDO itemDAOImpl = new itemDO();
    private static final itemBO itemBO = new BOitem(itemDAOImpl);
    private static final employeeDO empDAOImpl = new employeeDO();
    private static final employeeBO employeeBO = new BOemployee(empDAOImpl);
    private static final transactionlogDO trnsDAOImpl = new transactionlogDO();
    private static final transactionlogBO transBO = new BOtransactionlog(trnsDAOImpl);
    private static int transactionID = 0;

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception
    {
        InitialiseData();

        DisMenu();
        int option = Choice();

        while (option != 8)
        {
            switch (option)
            {
                case 1:
                    AddItem();
                    break;
                case 2:
                    AddQuantity();
                    break;
                case 3:
                    RemoveItems();
                    break;
                case 4:
                    InventoryReport();
                    break;
                case 5:
                    FinancialReport();
                    break;
                case 6:
                    ShowTransactions();
                    break;
                case 7:
                    PersonalReport();
                    break;
                case 8:
                    Exit();
                    break;
            }
            DisMenu();
            option = Choice();
        }
    }

    private static void DisMenu()
    {
        System.out.println("\n1. Add new item");
        System.out.println("2. Add to stock");
        System.out.println("3. Take from stock");
        System.out.println("4. Inventory Report");
        System.out.println("5. Financial Report");
        System.out.println("6. Display Transaction Log");
        System.out.println("7. Report Personal Usage");
        System.out.println("8. Exit");
    }

    private static int Choice()
    {
        int option = ReadInteger("\nOption");
        while (option < 1 || option > 8)
        {
            System.out.println("\nChoice not recognised, Please enter again");
            option = ReadInteger("\nOption");
        }
        return option;
    }

    private static int ReadInteger(String prompt)
    {
        try
        {
            System.out.println(prompt + ": > ");
            return Integer.parseInt(reader.readLine().toString());
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    private static float ReadFloat(String prompt)
    {
        try
        {
            System.out.println(prompt + ": > ");
            return Float.parseFloat(reader.readLine().toString());
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    private static void InitialiseData() throws SQLException {

        AddEmployee(new EmployeeDTO(1,"Graham"));
        AddEmployee(new EmployeeDTO(2,"Phil"));
        AddEmployee(new EmployeeDTO(3, "Jan"));


        ItemDTO i1 = new ItemDTO(1, "Pencil", 10, LocalDateTime.now(),"Graham", 0.25f);
        itemBO.createItem(i1);
        CreateLogEntry(++transactionID,"Item Added", i1, LocalDateTime.now());

        ItemDTO i2 = new ItemDTO(2, "Eraser", 20, LocalDateTime.now(),"Phil", 0.15f);
        itemBO.createItem(i2);
        CreateLogEntry(++transactionID,"Item Added", i2, LocalDateTime.now());
    }

    private static void InventoryReport()
    {
        // Inventory Report
        System.out.println("\nAll items");
        System.out.println( "ID"+ "\t" +"Name" + "\t" + "Quantity");

        try {
            List<ItemDTO> itemList = itemBO.findAllItems();
            for (ItemDTO item : itemList) {
                DisplayItem(item);
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve items from the database.");
            e.printStackTrace();
        }
    }

    private static void DisplayItem(ItemDTO i)
    {
        System.out.println(
                i.getID()+ "\t" +
                        i.getName()+ "\t" +
                        i.getQuantity());
    }

    private static void DisplayAdd(ItemDTO i)
    {
        System.out.println(
                i.getID() + "\t" +
                        i.getName() + "\t" +
                        i.getItemPrice() + "\t" +
                        i.getQuantity() + "\t" +
                        i.getDateCreated());

        System.out.println();
    }

    private static void ShowTransactions() throws Exception {
        try {
            List<TransactionLogDTO> transactionLogs = transBO.findAllTransactions();

            System.out.println("\nTransaction Log:");
            System.out.println("Date" + "\t" + "Type" + "\t" + "ID" + "\t" + "Name" + "\t" + "Employee" + "\t" + "Price");
            for (TransactionLogDTO transactionLog : transactionLogs) {
                DisTransactions(transactionLog);
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve transaction logs from the database.");
            e.printStackTrace();
        }
    }

    private static void FinancialReport()
    {
        // Financial report
        TotalPrice();
    }

    private static void PersonalReport() throws IOException
    {
        // Personal usage report
        Report();
    }

    public static void AddItem() throws IOException {
        int itemId = ReadInteger("\nItem ID");
        System.out.println("Item Name: > ");
        String itemName = reader.readLine().toString();
        int itemQuantity = ReadInteger("Item Quantity");
        float itemPrice = ReadFloat("Item Price");
        System.out.println("Employee Name : > ");
        String itemEmpName = reader.readLine().toString();
        if (itemId < 0 || itemQuantity < 0 || itemPrice < 0 || itemName == "" || itemEmpName == "")
        {
            System.out.println("ERROR: ID, Quantity or Price below 0 or Item name/ Employee name is empty");
        }
        else
        {
            try {
                Optional<ItemDTO> existingItem = itemBO.findItemById(itemId);
                if (existingItem.isPresent()) {
                    System.out.println("Item is already in stock.");
                } else {
                    ItemDTO newItem = new ItemDTO(itemId, itemName, itemQuantity, LocalDateTime.now(), itemEmpName, itemPrice);
                    itemBO.createItem(newItem);

                    System.out.println("Stock Added: ");
                    System.out.println("ID" + "\t" + "Name" + "\t" + "Price" + "\t" + "Quantity" + "\t" + "Date Added");
                    DisplayAdd(newItem);
                }
            } catch (SQLException e) {
                System.out.println("Error: Unable to add item to the database.");
                e.printStackTrace();
            }
        }
    }

    public static void AddQuantity() throws Exception
    {
        int itemId = ReadInteger("\nItem ID");

        try {
            ItemDTO temp = FindItem(itemId);

            float itemPrice = ReadFloat("Item Price");
            System.out.println("Employee Name : > ");
            String itemEmpName = reader.readLine().toString();
            int itemQuantity = ReadInteger("How many items would you like to add?");

            if (itemQuantity < 0 || itemEmpName.equals("")) {
                System.out.println("ERROR: Quantity being added is below 0 or Employee name is empty");
            } else {
                ItemDTO updatedItem = new ItemDTO(itemId, temp.getName(), temp.getQuantity() + itemQuantity,
                        temp.getDateCreated(), itemEmpName, itemPrice);
                itemBO.updateItem(updatedItem);

                System.out.println(itemQuantity + " items have been added to Item ID: " + itemId + " on " + LocalDateTime.now());
                CreateLogEntry(++transactionID,"Stock Updated", updatedItem, LocalDateTime.now());
            }
        } catch (Exception e) {
            System.out.println("Error: Unable to update item in the database.");
            e.printStackTrace();
        }
    }

    public static void RemoveItems() throws Exception
    {
        System.out.println("Employee Name: > ");
        String empname = reader.readLine().toString();

        try {
            FindEmployee(empname);
        } catch (Exception e) {
            throw new Exception("ERROR: Employee not found");
        }

        int itemId = ReadInteger("\nItem ID");
        ItemDTO temp;
        try {
            temp = FindItem(itemId);
        } catch (Exception e) {
            throw new Exception("ERROR: Item not found");
        }

        int itemQuantity = ReadInteger("How many items would you like to remove?");
        if (itemQuantity > temp.getQuantity() || itemQuantity < 0) {
            System.out.println("ERROR: Quantity too many or below 0");
        } else {
            ItemDTO updatedItem = new ItemDTO(itemId, temp.getName(), temp.getQuantity() - itemQuantity,
                    temp.getDateCreated(), empname, temp.getItemPrice());
            itemBO.updateItem(updatedItem);

            System.out.println(empname + " has removed " + itemQuantity + " of Item ID: " + itemId + " on " + LocalDateTime.now());
            // Add transaction log entry
            CreateLogEntry(++transactionID,"Item Removed", updatedItem, LocalDateTime.now());
            // CreateUsageEntry(itemId, temp.getItemName(), itemQuantity, empName, LocalDateTime.now());
        }
    }

    public static ItemDTO FindItem(int itemId) throws Exception
    {
        try {
            Optional<ItemDTO> item = itemBO.findItemById(itemId);
            if (item.isPresent()) {
                return item.get();
            } else {
                throw new Exception("ERROR: Item not found");
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to find item in the database.");
            e.printStackTrace();
            throw new Exception("ERROR: Item not found");
        }
    }

    public static void TotalPrice()
    {
        float total = 0;

        try {
            List<TransactionLogDTO> transactionLogs = transBO.findAllTransactions();

            System.out.println("Financial Report:");

            for (TransactionLogDTO entry : transactionLogs) {
                if (entry.getTypeOfTransaction().equals("Item Added")
                        || entry.getTypeOfTransaction().equals("Stock Updated")) {
                    float cost = FindItem(entry.getItemID()).getItemPrice() * FindItem(entry.getItemID()).getQuantity();
                    System.out.println(FindItem(entry.getItemID()).getName() + "\t" + cost);
                    total += cost;
                }
            }

            System.out.println("Total price of all items:" + "\t" + total);
        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve transaction logs from the database.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static EmployeeDTO FindEmployee(String EmpName) throws Exception
    {
        Optional<EmployeeDTO> employee = employeeBO.findEmployeeByName(EmpName);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("ERROR: Employee not found");
        }
    }

    public static void AddEmployee(EmployeeDTO e) throws SQLException {
        employeeBO.createEmployee(e);
    }

    //    public static void CreateUsageEntry(int id, String name, int quantity, String empName, LocalDateTime dateCreated)
//    {
//        personalUsage.add(new Item(id, name, quantity, empName, dateCreated));
//    }
//
    public static void CreateLogEntry(int id, String type, ItemDTO i, LocalDateTime dateAdded) throws SQLException {
        transBO.createTransactionLog(new TransactionLogDTO(id, type, i.getID(), i.getEmpName(), dateAdded));
    }

    public static void DisTransactions(TransactionLogDTO tl) throws Exception {
        System.out.println(
                tl.getDateAdded() + "\t" +
                        tl.getTypeOfTransaction() + "\t" +
                        tl.getItemID() + "\t" +
                        tl.getEmployeeName() + "\t" +
                        (tl.getTypeOfTransaction().equals("Item Removed") ? "" : "" + FindItem(tl.getItemID()).getItemPrice()));
    }

    public static void Report() throws IOException
    {
        System.out.println("Enter employee name : > ");
        String empname = reader.readLine().toString();

        System.out.println("Date Taken"+ "\t" + "ID"+ "\t" + "Name"+ "\t" + "Quantity");

        try {
            List<TransactionLogDTO> transactionLogs = transBO.findAllTransactions();

            for (TransactionLogDTO logItem : transactionLogs) {
                if (logItem.getTypeOfTransaction().equals("Item Removed") && logItem.getEmployeeName().equals(empname)) {
                    DisPersonalUsage(logItem.getDateAdded(), logItem.getItemID(), FindItem(logItem.getItemID()).getName(), FindItem(logItem.getItemID()).getQuantity());
                    //need to add quantity column to the TransactionTable to get the Quantity
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve transaction logs from the database.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DisPersonalUsage(LocalDateTime date, int id, String name, int quantity)
    {
        System.out.println(date + "\t" + id + "\t" + name + "\t" + quantity);
    }

    public static void Exit() {
        System.out.println("Thank you for using the Inventory Management System. Goodbye!");
        System.exit(0);
    }

}

package Entity;

public class employee {

    private int idemployee;
    private String employeeName;

    public employee() {
    }

    public employee(int idemployee, String employeeName) {
        this.idemployee = idemployee;
        this.employeeName = employeeName;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}

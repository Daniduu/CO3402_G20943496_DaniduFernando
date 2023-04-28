package DTO;

import Entity.employee;

public class EmployeeDTO
{
	private int EmpID;
	public String EmpName;

	public EmployeeDTO(int empID, String empName) {
		EmpID = empID;
		EmpName = empName;
	}

	public int getEmpID() {
		return EmpID;
	}

	public void setEmpID(int empID) {
		EmpID = empID;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}
}

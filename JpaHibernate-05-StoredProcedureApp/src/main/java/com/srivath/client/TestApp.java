package com.srivath.client;

import java.util.Scanner;

import com.srivath.dao.EmployeeDao;
import com.srivath.dao.impl.EmployeeDaoImpl;
import com.srivath.entity.Employee;

public class TestApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employee id:");
		Integer empId = sc.nextInt();
		EmployeeDao dao = new EmployeeDaoImpl();
		dao.executeStoredProcedure(empId);
	}
}

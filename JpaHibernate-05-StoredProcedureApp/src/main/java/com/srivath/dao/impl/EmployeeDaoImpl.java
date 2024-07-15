package com.srivath.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

import com.srivath.dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
	
	public EmployeeDaoImpl() {
		System.out.println("EmployeeDaoImpl constructor."+factory);
	}

	@Override
	public void executeStoredProcedure(Integer empId) {
		EntityManager manager = factory.createEntityManager();
		// create a StoredProcedureQuery instance and set the IN parameters
		StoredProcedureQuery query = manager.createNamedStoredProcedureQuery("procedure1");
		query.setParameter("id", empId);
		// a storedPrcedureQuery must be executed within a Transaction.
		EntityTransaction txn = manager.getTransaction();
		txn.begin();
		query.execute();
		// OUT parameters must be accessed/retrieved from the Query within the Transaction
		String name = (String) query.getOutputParameterValue("name");
		// date coming from SQL will be of java.sql.Timestamp. It should be converted into LocalDate type in java.
		Timestamp join = (Timestamp) query.getOutputParameterValue("joinDate");
		LocalDate joinDate = join.toLocalDateTime().toLocalDate();
		Double experience = (Double) query.getOutputParameterValue("experience");
		txn.commit();
		System.out.println("name = " + name + "\njoinDate = " + joinDate + "\nexperience = " + experience);
		manager.close();
	}
}

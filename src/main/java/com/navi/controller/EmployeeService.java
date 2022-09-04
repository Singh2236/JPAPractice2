package com.navi.controller;

import com.navi.Model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeService {

    //Saving the data in the database
    public void update(int idForUpdate, String name, String designation, String salary) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Employee employee = entityManager.find(Employee.class, idForUpdate);
            employee.setName(name);
            employee.setDesignation(designation);
            employee.setSalary(salary);

            entityManager.persist(employee);
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.clear();
            entityManagerFactory.close();
        }
    }

    //Finding the data from the database
    public void find(int idToFind) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

                Employee employee = entityManager.find(Employee.class, idToFind);
                System.out.println(employee.getId() + " " + employee.getName() + " " + employee.getDesignation() + " " + employee.getSalary());

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.clear();
            entityManagerFactory.close();
        }
    }


    //Updating the data in the database #
    public void save(String name, String designation, String salary) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setDesignation(designation);
            employee.setSalary(salary);

            entityManager.persist(employee);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.clear();
            entityManagerFactory.close();
        }
    }

    //Deleting the data in the database
    public void delete(int idToBeDeleted) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, idToBeDeleted);
            entityManager.remove(employee);


            //entityManager.persist(employee);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.clear();
            entityManagerFactory.close();
        }
    }


}

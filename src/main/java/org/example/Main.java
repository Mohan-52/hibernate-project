package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Laptop l1=new Laptop();
        l1.setId(1);
        l1.setBrand("Asus");
        l1.setModel("VIVO Book");
        l1.setRam(16);

        Laptop l2=new Laptop();
        l2.setId(2);
        l2.setBrand("Apple");
        l2.setModel("Mac Book Pro");
        l2.setRam(32);


        Student s1=new Student();
        s1.setRollNo(102);
        s1.setAge(17);
        s1.setName("Akash Madwal");
        s1.setLaptops(Arrays.asList(l1,l2));


        System.out.println(s1);

//        Configuration cfg= new Configuration();
//        cfg.addAnnotatedClass(org.example.Student.class);
//        cfg.configure();
//
//        SessionFactory sf=cfg.buildSessionFactory();

        SessionFactory sf= new Configuration()
                .addAnnotatedClass(org.example.Student.class)
                .addAnnotatedClass(org.example.Laptop.class)
                .configure()
                .buildSessionFactory();


        Session session=sf.openSession();

        Student st2=session.find(Student.class, 102);

        Transaction transaction=session.beginTransaction();

        session.persist(l1);
        session.persist(l2);
        session.persist(s1);

       // session.merge(s1);

       // session.remove(st2);

        transaction.commit();

        Student std= session.find(Student.class,102);
        System.out.println(std);


        session.close();
        sf.close();




    }
}
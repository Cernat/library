package org.internship.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.Address;
import org.internship.hibernate.dto.UserDetails;

import java.util.Date;

/**
 * Class used for creating and manipulate data using Hibernate
 */
public class HibernateTest {
    public static void main(String[] args) {
        UserDetails user = new UserDetails();
        user.setUserId(1);
        user.setUserName("First User");

        Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        user.setAddress(address);

        UserDetails user2 = new UserDetails();
        user2.setUserId(2);
        user2.setUserName("SECOND USER");
        user2.setOfficeAddress(address);
        /**
         * .configure() - uses the hibernate.cfg.xml configuration file
         * .buildSessionFactory() - builds a session
         * .openSession() - open a session that allows us to make transactions
         */
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}

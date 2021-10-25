package org.internship.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.FourWheeler;
import org.internship.hibernate.dto.TwoWheeler;
import org.internship.hibernate.dto.UserDetails;
import org.internship.hibernate.dto.Vehicle;

/**
 * Class used for creating and manipulate data using Hibernate
 */
public class HibernateTest {
    public static void main(String[] args) {

        UserDetails user = new UserDetails();
        user.setUserName("Test User");

        /**
         * .configure() - uses the hibernate.cfg.xml configuration file
         * .buildSessionFactory() - builds a session
         * .openSession() - open a session that allows us to make transactions
         */
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        user.setUserName("Updated User");
        user.setUserName("Updated User Again");

        session.getTransaction().commit();
        session.close();

        user.setUserName("Updated User After Session Close");
    }
}

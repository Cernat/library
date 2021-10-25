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

        /**
         * .configure() - uses the hibernate.cfg.xml configuration file
         * .buildSessionFactory() - builds a session
         * .openSession() - open a session that allows us to make transactions
         */
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDetails userDetails = (UserDetails) session.get(UserDetails.class, 1);
        userDetails.setUserName("Updated User");

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        UserDetails user2 = (UserDetails) session2.get(UserDetails.class, 1);

        session2.getTransaction().commit();
        session2.close();

    }
}

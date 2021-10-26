package org.internship.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.UserDetails;

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

        UserDetails user = (UserDetails) session.get(UserDetails.class, 1);

        session.getTransaction().commit();
        session.close();

        user.setUserName("Updated Username after session close");

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(user);
        user.setUserName("Change After Update");
        session.getTransaction().commit();
        session.close();
    }
}

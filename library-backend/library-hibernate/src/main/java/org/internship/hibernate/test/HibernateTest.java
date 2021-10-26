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

        UserDetails user = (UserDetails) session.get(UserDetails.class, 5);
//        session.delete(user);
        user.setUserName("Updated User");
        session.update(user);

        session.getTransaction().commit();
        session.close();
//        System.out.println("User name pulled up is: " + user.getUserName());
    }
}

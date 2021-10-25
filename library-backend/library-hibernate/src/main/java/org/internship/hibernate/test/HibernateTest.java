package org.internship.hibernate.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.FourWheeler;
import org.internship.hibernate.dto.TwoWheeler;
import org.internship.hibernate.dto.UserDetails;
import org.internship.hibernate.dto.Vehicle;

import java.util.List;

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

        Query query = session.createQuery("select userName from UserDetails");
        query.setFirstResult(5);
        query.setMaxResults(4);
//        List<UserDetails> users = (List<UserDetails>) query.list();
        List<String> usersNames = (List<String>) query.list();

        session.getTransaction().commit();
        session.close();

//        for (UserDetails user : users)
//            System.out.println(user.getUserName());

        for (String user : usersNames)
            System.out.println(user);
    }
}

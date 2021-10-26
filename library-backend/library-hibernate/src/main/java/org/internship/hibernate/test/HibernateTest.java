package org.internship.hibernate.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.UserDetails;

import java.util.List;

import java.util.List;

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
//        String minUserId = " 5 or 1 = 1";
        String minUserId = "5";
        String userName = "User 9";

//        Query query = session.createQuery("from UserDetails where userId > " + minUserId);
        Query query = session.createQuery("from UserDetails where userId > :userId and  userName = :userName");
        query.setInteger("userId", Integer.parseInt(minUserId));
        query.setString("userName", userName);

        List<UserDetails> users = (List<UserDetails>) query.list();

        session.getTransaction().commit();
        session.close();

        for (UserDetails user : users)
            System.out.println(user.getUserName());
    }
}

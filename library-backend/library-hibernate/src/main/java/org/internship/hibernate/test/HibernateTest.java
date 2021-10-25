package org.internship.hibernate.test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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

        Criteria criteria = session.createCriteria(UserDetails.class);
        criteria.add(Restrictions.or(Restrictions.between("userId", 0, 3), Restrictions.between("userId", 7, 9)));

//        criteria.add(Restrictions.like("userName", "%User 6%"))
//                .add(Restrictions.between("userId", 5, 50));

        List<UserDetails> users = (List<UserDetails>) criteria.list();

        session.getTransaction().commit();
        session.close();

        for (UserDetails user : users)
            System.out.println(user.getUserName());
    }
}

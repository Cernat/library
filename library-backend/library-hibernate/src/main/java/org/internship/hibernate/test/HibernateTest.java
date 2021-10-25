package org.internship.hibernate.test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.internship.hibernate.dto.UserDetails;

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

        UserDetails exampleUser = new UserDetails();
//        exampleUser.setUserId(5);
//        exampleUser.setUserName("User 5");
        exampleUser.setUserName("User 1%");

//        Example example = Example.create(exampleUser).excludeProperty("userName");
        Example example = Example.create(exampleUser).enableLike();

        Criteria criteria = session.createCriteria(UserDetails.class)
                .add(example);
//                .setProjection(Projections.property("userId"));
//                .addOrder(Order.desc("userId"));

        List<UserDetails> users = (List<UserDetails>) criteria.list();

        session.getTransaction().commit();
        session.close();


        for (UserDetails user : users)
            System.out.println(user.getUserName());

    }
}

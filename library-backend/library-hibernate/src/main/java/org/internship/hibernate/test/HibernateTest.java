package org.internship.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.internship.hibernate.dto.Address;
import org.internship.hibernate.dto.UserDetails;
import org.internship.hibernate.dto.Vehicle;

import java.util.Date;
import java.util.Set;

/**
 * Class used for creating and manipulate data using Hibernate
 */
public class HibernateTest {
    public static void main(String[] args) {

        UserDetails user = new UserDetails();
        user.setUserName("First User");

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleName("Car2");

        user.getVehicle().add(vehicle);
        user.getVehicle().add(vehicle2);
        vehicle.setUser(user);
        vehicle2.setUser(user);

        /**
         * .configure() - uses the hibernate.cfg.xml configuration file
         * .buildSessionFactory() - builds a session
         * .openSession() - open a session that allows us to make transactions
         */
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(vehicle);
        session.save(vehicle2);
        session.getTransaction().commit();
        session.close();
    }
}

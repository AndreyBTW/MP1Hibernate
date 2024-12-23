package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_schema_test";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1q2w3e4r5t";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            throw new RuntimeException(e);
        }
        return connection;
    }

    public SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.URL, DB_URL);
        properties.setProperty(Environment.USER, DB_USERNAME);
        properties.setProperty(Environment.PASS, DB_PASSWORD);
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        return new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
    }
}

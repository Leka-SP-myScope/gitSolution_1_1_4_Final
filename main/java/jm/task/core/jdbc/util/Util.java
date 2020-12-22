package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/usertest?serverTimezone=UTC&useSSL=false";
    private static final String DB_UserName = "root";
    private static final String DB_Password = "ALEKA678022";
    private static SessionFactory sessionFactory;

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usertest" +
                        "?serverTimezone=UTC&useSSL=false");
                settings.setProperty("hibernate.connection.username", "root");
                settings.setProperty("hibernate.connection.password", "ALEKA678022");
                settings.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                settings.setProperty("hibernate.show_sql", "true");
                settings.setProperty("hibernate.use_sql_comments", "true");
                settings.setProperty("hibernate.format_sql", "true");
                settings.setProperty("hibernate.hbm2ddl.auto", "create-drop");

                configuration.addAnnotatedClass(User.class);

                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
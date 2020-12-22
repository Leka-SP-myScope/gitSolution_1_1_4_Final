package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery("CREATE TABLE IF NOT EXISTS USER  ("
                    + "Id INT (6) PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                    + "Name VARCHAR (80) NOT NULL, "
                    + "LastName VARCHAR (80) NOT NULL, "
                    + "Age FLOAT (4) NOT NULL)");
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery("DROP TABLE IF EXISTS USER");
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List<User> userList;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("SELECT * FROM USER");
            query.addEntity(User.class);
            userList = query.list();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery("TRUNCATE TABLE USER");
            sqlQuery.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
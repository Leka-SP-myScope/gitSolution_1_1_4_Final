package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            userService.createUsersTable();

            userService.saveUser("Vasiliy", "Ivanov", (byte) 25);
            userService.saveUser("Alexey", "Chudikov", (byte) 22);
            userService.saveUser("Sergey", "Pelikanov", (byte) 40);
            userService.saveUser("Petr", "Vagapov", (byte) 21);

            System.out.println(userService.getAllUsers());

            userService.cleanUsersTable();

            userService.dropUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

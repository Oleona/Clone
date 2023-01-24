package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Veins", "Richborn", (byte) 13);
        userService.saveUser("Gosha", "Sigorosa", (byte) 18);
        userService.saveUser("Lanselot", "Sigorosa", (byte) 6);
        userService.saveUser("Mario", "Somalissa", (byte) 3);
        var result = userService.getAllUsers();
        for (User res : result) {
            System.out.println(res.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

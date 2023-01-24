package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao myImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        myImpl.createUsersTable();
    }

    public void dropUsersTable() {
        myImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        myImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        myImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return myImpl.getAllUsers();
    }


    public void cleanUsersTable() {
        myImpl.cleanUsersTable();
    }
}

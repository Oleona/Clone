package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String sqlCreate = "CREATE TABLE IF NOT EXISTS users(" +
            "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(32) NOT NULL," +
            "lastName VARCHAR(64) NOT NULL," +
            "age TINYINT NOT NULL );";
    private static final String sqlDrop = "DROP TABLE IF EXISTS users";

    private static final String sqlInsert = "INSERT INTO users (name,lastName,age)" + "VALUES(?,?,?)";

    private static final String sqlRemoveById = "DELETE FROM users WHERE id=?";

    private static final String sqlGetAll = "SELECT id, name, lastName, age FROM users";

    private static final String sqlClear = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public Connection connection = Util.getConnection();

    public void createUsersTable() {

        try (Statement statement =connection.createStatement() ) {//Util.getConnection().createStatement()
            statement.executeUpdate(sqlCreate);
            System.out.println("Таблица users создана");
            //Util.getConnection().commit();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
               // Util.getConnection().rollback();
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlDrop);
            System.out.println("Таблица users удалена");
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем "+name+ " добавлен в БД");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlClear);
            System.out.println("Таблица users очищена");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

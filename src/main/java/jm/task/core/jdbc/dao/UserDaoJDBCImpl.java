package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    private final Connection connection = Util.getConnection();
    private static Long idCounter = 0L;

    public void createUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("""
                CREATE TABLE if not exists `users_table` (
                `id` MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
                `name` VARCHAR(45) NOT NULL,
                  `lastName` VARCHAR(45) NOT NULL,
                  `age` TINYINT(1) NOT NULL,
                  PRIMARY KEY (`id`))
            """)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("DROP TABLE if exists users_table")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection
                .prepareStatement("INSERT INTO users_table VALUES(?, ?, ?, ?)")) {
            ps.setLong(1, ++idCounter);
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setByte(4, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users_table where id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (PreparedStatement ps = connection
                .prepareStatement("SELECT id, name, lastName, age FROM users_table")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users_table")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

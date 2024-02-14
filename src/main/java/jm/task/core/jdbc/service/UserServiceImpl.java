package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao newDao = new UserDaoJDBCImpl();
    public void createUsersTable() {
        newDao.createUsersTable();
    }

    public void dropUsersTable() {
        newDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        newDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        newDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return newDao.getAllUsers();
    }

    public void cleanUsersTable() {
        newDao.cleanUsersTable();
    }
}

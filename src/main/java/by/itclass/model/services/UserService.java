package by.itclass.model.services;

import by.itclass.model.dao.UserDao;
import by.itclass.model.entities.User;

import java.util.Objects;

public class UserService implements Service{
    private UserDao dao;


    public  UserService() {
        dao = new UserDao();
    }

    public User getUser(String login, String password) {
        return dao.getUser(login, password);
    }

    public boolean addUser(User user, String password) {
        return dao.addUser(user, password);
    }
}

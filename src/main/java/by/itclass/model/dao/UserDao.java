package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;

public class UserDao {
    private static UserDao dao;



    public static UserDao getInstance() {
        return Objects.isNull(dao) ? new UserDao() : dao;
    }

    public User getUser(String login, String password) {
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_USER)) {
            ps.setString(1, login);
            ps.setString(2, password);
            var resultSet = ps.executeQuery();
            if(resultSet.next()) {
                var id = resultSet.getInt(ID_COL);
                var name = resultSet.getString(NAME_COL);
                var email = resultSet.getString(EMAIL_COL);
                return new User(id, login, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user, String password) {
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(INSERT_USER)) {
            if (isAccessible(user.getLogin(), cn)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getLogin());
                ps.setString(4, password);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAccessible(String login, Connection cn) throws SQLException {
        try (var ps = cn.prepareStatement(SELECT_USERID_BY_LOGIN)) {
            ps.setString(1, login);
            return !ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

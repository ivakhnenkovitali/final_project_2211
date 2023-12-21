package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.OrderItem;
import by.itclass.model.entities.User;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;
import static by.itclass.constants.JspConstants.*;

public class OrderDao {
    private static OrderDao dao;

    private OrderDao() {
        ConnectionManager.init();
    }

    /// Singleton  −•−−• •− −••• −−− •− −•• −−•• −• −−− −−−• −•− •− −•−−•− −••••− •− −•• −−•• −• −−•• ••• •− −− −•−− •••• •−− •−•− −•• −−− −− −•−− •••• −−−− •− −••• •−•• −−− −• •− •−−• •−• •− • −•− − •− •−− •− −• −• •−•− •••••• • −• •−−• •−• −•−− −−•• −• •− −−−• •− −• −•−− −•• •−•• •−•− − −•−− •••• •−− −•−− •−−• •− −•• −•− •− •−•−•− −•− •− •−•• •−− •− −− •−−• •− − •−• ••−•• −••• • −• − −−− •−•• −••− −•− •− −•• −−•• −• •− ••• −−− −••• −• −•− −•− •−•• •− ••• •− −• • −••• −−− •−•• −••− −−−− •••••• −−• ••−•• − −•−− •− −•• −−•• −• −•− •−•• •− ••• −− −−− •••− •− −••• −•−− −•−• −••− −− ••−•• −• ••−•• −•• •••− ••−•• •−• •− −− •−• ••−•• ••• ••− •−• ••• •− •− −••• −−− −−• •−•• •− −••• •− •−•• −••− −• −•−− −− •−−• −−− −−−− ••− −•− •− −− −−•• −• •− −−−• ••−•• −• −• •−•− ••••••
//


    public static OrderDao getInstance() {
        if (Objects.isNull(dao)) {
            dao = new OrderDao();
        }
        return dao;
    }

    public boolean saveOrder(HttpSession session, String address) {
        var user = (User) session.getAttribute(USER_ATTR);
        var now = LocalDateTime.now();
        var date = now.format(DateTimeFormatter.ofPattern("y-MM-dd"));
        var time = now.format(DateTimeFormatter.ofPattern("HH-mm"));
        var orderId = String.join("-", user.getName(), date, time);
        return saveOrderToDb(orderId, date, user.getId(), address, session);
    }

    private boolean saveOrderToDb(String orderId, String date, int userId, String address, HttpSession session) {
        try (var cn = ConnectionManager.getConnection()) {
            cn.setAutoCommit(false);
            firstAction(orderId, date, userId, address, cn);  ////транзакция
            var items = (List<OrderItem>) session.getAttribute(ORDER_ITEMS_ATTR);
            for (OrderItem item : items) {
                secondAction(orderId, item, cn);
            }


            cn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute(ORDER_ID_ATTR,orderId);
        return true;
    }

    private void firstAction(String orderId, String date, int userId,
                             String address, java.sql.Connection cn) throws SQLException {
        try (var psSaveOrder = cn.prepareStatement(INSERT_ORDER)) {
            psSaveOrder.setString(1, orderId);
            psSaveOrder.setString(2, date);
            psSaveOrder.setInt(3, userId);
            psSaveOrder.setString(4, address);
            psSaveOrder.executeUpdate();
        }
    }

    private void secondAction(String orderId, OrderItem item, Connection cn) throws SQLException {
        try (var psSaveItem = cn.prepareStatement(INSERT_ORDER_ITEM)) {
            psSaveItem.setString(1, orderId);
            psSaveItem.setInt(2, item.getFoodItem().getId());
            psSaveItem.setInt(3, item.getQuantity());
            psSaveItem.executeUpdate();
        }
    }


}





package by.itclass.model.dao;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.Order;
import by.itclass.model.entities.OrderItem;
import by.itclass.model.entities.User;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static by.itclass.constants.DbConstants.*;
import static by.itclass.constants.JspConstants.*;

public class OrderDao {


    /// Singleton был −•−−• •− −••• −−− •− −•• −−•• −• −−− −−−• −•− •− −•−−•− −••••− •− −•• −−•• −• −−•• ••• •− −− −•−− •••• •−− •−•− −•• −−− −− −•−− •••• −−−− •− −••• •−•• −−− −• •− •−−• •−• •− • −•− − •− •−− •− −• −• •−•− •••••• • −• •−−• •−• −•−− −−•• −• •− −−−• •− −• −•−− −•• •−•• •−•− − −•−− •••• •−− −•−− •−−• •− −•• −•− •− •−•−•− −•− •− •−•• •−− •− −− •−−• •− − •−• ••−•• −••• • −• − −−− •−•• −••− −•− •− −•• −−•• −• •− ••• −−− −••• −• −•− −•− •−•• •− ••• •− −• • −••• −−− •−•• −••− −−−− •••••• −−• ••−•• − −•−− •− −•• −−•• −• −•− •−•• •− ••• −− −−− •••− •− −••• −•−− −•−• −••− −− ••−•• −• ••−•• −•• •••− ••−•• •−• •− −− •−• ••−•• ••• ••− •−• ••• •− •− −••• −−− −−• •−•• •− −••• •− •−•• −••− −• −•−− −− •−−• −−− −−−− ••− −•− •− −− −−•• −• •− −−−• ••−•• −• −• •−•− ••••••
//


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
        session.setAttribute(ORDER_ID_ATTR, orderId);
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

    public List<Order> getOrders(int userId) {
        var orders = new ArrayList<Order>();
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_ORDERS_BY_USER)) {
            ps.setInt(1, userId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getString(ID_COL);
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                orders.add(new Order(id, date, address));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;

    }

    public String buildReceipt(String orderId) {
        var sb = new StringBuilder();
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_HEAD_FOR_ORDER)) {
            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var date = rs.getString(DATE_COL);
                var address = rs.getString(ADDRESS_COL);
                sb.append("<h2>Order Id : ").append(orderId).append("</h2>")
                        .append("<h2>Date of order : ").append(date).append("</h2>")
                        .append("<h2>Delivery address : ").append(address).append("</h2>")
                        .append("<h2> class='underline' You ordered : </h2>")
                        .append(getItemsForReceipt(orderId, cn))
                        .append(getTotalAmount(orderId, cn));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getItemsForReceipt(String orderId, Connection cn) {
        var sb = new StringBuilder();
        try (var ps = cn.prepareStatement(SELECT_ITEMS_FOR_ORDER)) {
            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                (sb.append("<h2>").append(rs.getInt(QUANTITY_COL)).append(" ")
                        .append(rs.getString(NAME_COL)).append(" $ ")
                        .append(rs.getDouble(PRICE_COL))).append(" $. Amount : ")
                        .append(rs.getDouble(AMOUNT_COL)).append(" $.</h2>");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getTotalAmount(String orderId, Connection cn) {
        var sb = new StringBuilder();
        try (var ps = cn.prepareStatement(SELECT_TOTAL_AMOUNT)) {

            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                sb.append("<h2 class='underline'> Total amount : ")
                        .append(Math.round(rs.getDouble(AMOUNT_COL)*100)/100d)    /// округление цены 5.9999999
                        .append(" $.</h2");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}





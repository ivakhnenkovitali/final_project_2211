package by.itclass.model.services;

import by.itclass.model.dao.OrderDao;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.Order;
import by.itclass.model.entities.OrderItem;
import by.itclass.model.entities.User;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;
import static by.itclass.constants.JspConstants.*;

public class OrderService implements Service{
    private OrderDao dao;

    OrderService() {
        dao = new OrderDao();

    }



    public boolean saveOrder(HttpSession session, String address) {
        return dao.saveOrder(session, address);


    }
    public List<Order> getOrders(int userId){
        return dao.getOrders(userId);
    }
public String buildReceipt(String orderId){
        return dao.buildReceipt(orderId);
}
}










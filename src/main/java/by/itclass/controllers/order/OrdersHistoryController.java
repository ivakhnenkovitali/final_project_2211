package by.itclass.controllers.order;


import by.itclass.controllers.abstraction.AbstractController;
import by.itclass.controllers.abstraction.OrderAbstractController;
import by.itclass.model.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.ApplicationConstants.*;
import static by.itclass.constants.JspConstants.*;


@WebServlet(value = ORDERS_HISTORY_CONTROLLER)
public class OrdersHistoryController extends OrderAbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var userId = ((User) session.getAttribute(USER_ATTR)).getId();

        var orders = orderService.getOrders(userId);
        req.setAttribute(ORDERS_ATTR, orders);
        forward(req, resp, ORDERS_JSP);
    }
}













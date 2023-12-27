package by.itclass.controllers.order;

import by.itclass.controllers.abstraction.AbstractController;
import by.itclass.controllers.abstraction.OrderAbstractController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.ApplicationConstants.*;
import static by.itclass.constants.JspConstants.*;

@WebServlet(value = ORDER_CONTROLLER)
public class OrderController extends OrderAbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var address = req.getParameter(ADDRESS_PARAM);
        var session = req.getSession();
        if (orderService.saveOrder(session, address)) {
            var orderId = (String) session.getAttribute(ORDER_ID_ATTR);
            session.removeAttribute(ORDER_ITEMS_ATTR);
            session.removeAttribute(ORDER_ID_ATTR);
            forward(req, resp, HOME_JSP, "Your order number is " + orderId
                    + ", you can print it on Orders page");

        } else {
            forward(req, resp, CART_JSP, "Smth. went wrong!!!");
        }
    }
}

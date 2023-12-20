package by.itclass.controllers;

import by.itclass.model.services.CartService;
import by.itclass.model.services.FoodService;
import by.itclass.model.services.OrderService;
import by.itclass.model.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.JspConstants.MESSAGE_ATTR;

public abstract class AbstractController extends HttpServlet {
    protected UserService userService;
    protected FoodService foodService;
    protected CartService cartService;
    protected OrderService orderService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
        foodService = FoodService.getInstance();
        cartService = CartService.getInstance();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //просто перейти на некий адрес
    public void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        req.getRequestDispatcher(url).forward(req, resp);
    }

    //перегруженный метод... перейти с сообщением
    public void forward(HttpServletRequest req, HttpServletResponse resp, String url, String message) throws ServletException, IOException {
        req.setAttribute(MESSAGE_ATTR, message);
        forward(req, resp, url);
    }

    protected void redirect(HttpServletResponse resp, String url) throws IOException {
        resp.sendRedirect(getServletContext().getContextPath() + url);
    }

}

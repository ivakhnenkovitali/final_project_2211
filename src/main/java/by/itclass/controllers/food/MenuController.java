package by.itclass.controllers.food;

import by.itclass.controllers.AbstractController;
import by.itclass.model.entities.FoodItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static by.itclass.constants.ApplicationConstants.MENU_CONTROLLER;
import static by.itclass.constants.JspConstants.*;

@WebServlet(value = MENU_CONTROLLER)
public class MenuController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var foodType = Integer.parseInt(req.getParameter(FOOD_TYPE_PARAM));
        var items = foodService.getFoodItemsByType(foodType);
        enrichRequest(req, foodType, items);
        forward(req, resp, HOME_JSP);
    }

    private  void enrichRequest(HttpServletRequest req, int foodType, List<FoodItem> items) {
        switch (foodType) {
            case 1 -> req.setAttribute(PIZZA_ATTR, items);
            case 2 -> req.setAttribute(DRINK_ATTR, items);
        }
    }
}

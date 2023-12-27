package by.itclass.model.services;

import by.itclass.model.dao.FoodDao;
import by.itclass.model.entities.FoodItem;

import java.util.List;
import java.util.Objects;

public class FoodService implements Service{
    private static FoodService service;
    private final FoodDao dao;

    FoodService() {
        dao = FoodDao.getInstance();
    }

    public static FoodService getInstance() {
        return Objects.isNull(service) ? new FoodService() : service;
    }

    public List<FoodItem> getFoodItemsByType(int foodType) {
        return dao.getFoodItemsByType(foodType);
    }
}

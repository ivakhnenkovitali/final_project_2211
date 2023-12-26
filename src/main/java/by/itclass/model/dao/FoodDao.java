package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.FoodItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.itclass.constants.DbConstants.*;

public class FoodDao {
    private static FoodDao dao;



    public static FoodDao getInstance() {
        return Objects.isNull(dao) ? new FoodDao() : dao;
    }

    public List<FoodItem> getFoodItemsByType(int foodType) {
        var items = new ArrayList<FoodItem>();
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_FOOD_ITEMS_BY_TYPE)){
            ps.setInt(1, foodType);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getInt(ID_COL);
                var name = rs.getString(NAME_COL);
                var price = rs.getDouble(PRICE_COL);
                items.add(new FoodItem(id, foodType, name, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}

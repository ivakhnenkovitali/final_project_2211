package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import com.sun.jdi.connect.spi.Connection;

import java.util.Objects;

public class OrderDao {
    private static OrderDao dao;
    private OrderDao(){
        ConnectionManager.init();
    }
 public static OrderDao getInstance(){
        if (Objects.isNull(dao)){
            dao = new OrderDao();
        }
        return dao;
 }

}

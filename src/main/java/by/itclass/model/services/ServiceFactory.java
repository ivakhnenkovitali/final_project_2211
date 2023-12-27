package by.itclass.model.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static Map<ServiceType, Service> services;

    public static void init() {
        services = new HashMap<>();
        services.put(ServiceType.USER_SERVICE, new UserService());
        services.put(ServiceType.CART_SERVICE, new CartService());
        services.put(ServiceType.FOOD_SERVICE, new FoodService());
        services.put(ServiceType.ORDER_SERVICE, new OrderService());
    }

    public static Service getInstance(ServiceType serviceType) {
        return services.get(serviceType);
    }
}

package by.itclass.model.services;

import java.util.Objects;

public class CartService {
    private static CartService service;

    public CartService() {
    }

    public static CartService getInstance(){
        if (Objects.isNull(service)){
            service = new CartService();

        }
        return service;
    }

}

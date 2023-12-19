package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Order {
    private String id;
    private String data;
    private String address;
}

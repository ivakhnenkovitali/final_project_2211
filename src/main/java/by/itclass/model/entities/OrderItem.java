package by.itclass.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class OrderItem {
    private String orderId;
    private final OrderItem item;
    private final int quantity;


}

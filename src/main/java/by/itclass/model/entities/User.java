package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
    private int id;
    private final String login;
    private final String name;
    private final String email;
}

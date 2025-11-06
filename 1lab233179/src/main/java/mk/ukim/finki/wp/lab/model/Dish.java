package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor
public class Dish {
    String dishId;
    String name;
    String cuisine;
    int preparationTime;
}

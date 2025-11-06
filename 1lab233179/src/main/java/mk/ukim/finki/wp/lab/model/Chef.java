package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor
public class Chef {
    Long id;
    String firstName;
    String lastName;
    String bio;
    List<Dish> dishes;
}

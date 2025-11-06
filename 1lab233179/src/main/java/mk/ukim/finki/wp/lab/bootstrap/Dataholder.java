package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dataholder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        chefs = new ArrayList<>();
        dishes = new ArrayList<>();

        chefs.add(new Chef(
                1L,
                "Gordon",
                "Ramsay",
                "World-renowned chef known for high-end cuisine and fiery personality.",
                new ArrayList<>()
        ));

        chefs.add(new Chef(
                2L,
                "Massimo",
                "Bottura",
                "Italian chef famous for innovative takes on traditional cuisine.",
                new ArrayList<>()
        ));

        chefs.add(new Chef(
                3L,
                "Rene",
                "Redzepi",
                "Chef behind Noma, known for modern Nordic food.",
                new ArrayList<>()
        ));

        chefs.add(new Chef(
                4L,
                "Heston",
                "Blumenthal",
                "British chef known for experimental cooking and molecular gastronomy.",
                new ArrayList<>()
        ));

        chefs.add(new Chef(
                5L,
                "Jamie",
                "Oliver",
                "Celebrity chef focused on fresh ingredients and simple cooking.",
                new ArrayList<>()
        ));
        dishes = new ArrayList<>();

        dishes.add(new Dish(
                "D1",
                "Beef Wellington",
                "British",
                120
        ));

        dishes.add(new Dish(
                "D2",
                "Tiramisu",
                "Italian",
                45
        ));

        dishes.add(new Dish(
                "D3",
                "Sushi Platter",
                "Japanese",
                60
        ));

        dishes.add(new Dish(
                "D4",
                "Paella",
                "Spanish",
                90
        ));

        dishes.add(new Dish(
                "D5",
                "Chicken Tikka Masala",
                "Indian",
                70
        ));
    }
}

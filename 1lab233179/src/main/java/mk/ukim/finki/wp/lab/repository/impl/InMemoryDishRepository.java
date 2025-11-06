package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.Dataholder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryDishRepository implements DishRepository {
    @Override
    public List<Dish> findAll() {
        return Dataholder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return Dataholder.dishes.stream().filter(d -> d.getDishId() == dishId).findFirst().orElse(null);
    }
}

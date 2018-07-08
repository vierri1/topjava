package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDataHardCode implements MealsData {

    private Map<Integer, Meal> meals = new ConcurrentHashMap();

    private AtomicInteger id = new AtomicInteger(0);

    {
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public Meal get(int id) {
        return meals.get(id);
    }


    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    public void delete(int id) {
        meals.remove(id);
    }

    public void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(id.incrementAndGet());
        }
        int id = meal.getId();
        meals.put(id, meal);
    }


}

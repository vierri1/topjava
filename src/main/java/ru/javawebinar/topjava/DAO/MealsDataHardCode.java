package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDataHardCode implements MealsData {

    private static MealsDataHardCode instance = new MealsDataHardCode();

    private static AtomicInteger id = new AtomicInteger(6);

    private static List<Meal> initList = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 2),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 3),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 4),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 5),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 6)
    );

    private static List<Meal> meals = new CopyOnWriteArrayList<>(initList);

    private MealsDataHardCode() { }

    public static MealsDataHardCode getInstnce() {
        return instance;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void updateMeal(Meal newMeal) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getId() == newMeal.getId()) {
                meals.set(i, newMeal);
                break;
            }
        }
    }

    public void deleteMeal(int id) {
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            if (meal.getId() == id) {
                meals.remove(i);
                break;
            }
        }
    }

    public void createMeal(LocalDateTime localDateTime, String description, int calories) {
        Meal newMeal = new Meal(localDateTime, description, calories, id.incrementAndGet());
        meals.add(newMeal);
    }


}

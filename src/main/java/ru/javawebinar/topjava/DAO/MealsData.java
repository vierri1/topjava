package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealsData {
    List<Meal> getMeals();
    void updateMeal(Meal newMeal);
    void deleteMeal(int id);
    void createMeal(LocalDateTime localDateTime, String description, int calories);
}

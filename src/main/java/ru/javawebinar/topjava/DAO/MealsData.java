package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;


public interface MealsData {

    List<Meal> getAll();

    void delete(int id);

    void save(Meal meal);

    Meal get(int id);

}

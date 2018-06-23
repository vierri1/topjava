package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(20,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        int calories;
        LocalDate date;
        for (UserMeal list: mealList) {
            date = list.getDateTime().toLocalDate();
            calories = list.getCalories();
            caloriesPerDayMap.merge(date, calories, (a, b) -> a + b);
        }

        List<UserMealWithExceed> mealWithExceedsList = new ArrayList<>();
        for (UserMeal list: mealList) {
            if (TimeUtil.isBetween(list.getDateTime().toLocalTime(), startTime, endTime)) {
                date = list.getDateTime().toLocalDate();
                calories = caloriesPerDayMap.get(date);
                mealWithExceedsList.add(new UserMealWithExceed(list, calories > caloriesPerDay));
            }
        }

        return mealWithExceedsList;
    }
}

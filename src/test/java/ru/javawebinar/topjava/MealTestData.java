package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class MealTestData {

    public static final Meal userMeal1 = new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal userMeal3 = new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal adminMeal1 = new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal adminMeal2 = new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal adminMeal3 = new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(expected));
    }
}

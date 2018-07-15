package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            SecurityUtil.setAuthUserId(1);
            System.out.println("USER_1");
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            Collection<Meal> list = mealRestController.getAll();
            list.forEach(System.out::println);

            SecurityUtil.setAuthUserId(2);
            System.out.println("USER_2");
            list = mealRestController.getAll();
            list.forEach(System.out::println);

            MealService mealService = appCtx.getBean(MealService.class);

            mealService.update( new Meal(2, 7, LocalDateTime.of(2015, Month.OCTOBER, 30, 10, 0), "DINNER", 500));
            System.out.println("USER_2 UPDATE");
            list = mealRestController.getAll();
            list.forEach(System.out::println);
        }
    }
}

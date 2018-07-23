package ru.javawebinar.topjava.service;

import org.assertj.core.condition.Not;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {


    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }


    @Autowired
    public MealService service;

    @Test
    public void get() {
        //id for userMeal2
        int id = AbstractBaseEntity.START_SEQ + 3;
        int userId = UserTestData.USER_ID;
        Meal meal = service.get(id, userId);
        assertMatch(meal, MealTestData.userMeal2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        Meal meal = service.get(START_SEQ + 2, UserTestData.ADMIN_ID);
    }

    @Test
    public void delete() {
        //id for adminMeal1
        int id = AbstractBaseEntity.START_SEQ + 5;
        int userId = UserTestData.ADMIN_ID;
        service.delete(id, userId);
        assertMatch(service.getAll(userId), adminMeal3, adminMeal2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(START_SEQ + 6, UserTestData.USER_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        int userId = UserTestData.USER_ID;
        List<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 13, 0),
                LocalDateTime.of(2015, Month.MAY, 30, 20, 0), userId);
        assertMatch(meals, userMeal3, userMeal2);
    }

    @Test
    public void getAll() {
        int userId = UserTestData.ADMIN_ID;
        List<Meal> meals = service.getAll(userId);
        assertMatch(meals, adminMeal3, adminMeal2, adminMeal1);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MealTestData.adminMeal3);
        int userId = UserTestData.ADMIN_ID;
        updated.setCalories(999);
        updated.setDescription("UPDATED MEAL");
        service.update(updated, userId);
        assertMatch(service.get(updated.getId(), userId), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        int userId = UserTestData.ADMIN_ID;
        service.update(service.get(START_SEQ + 3, UserTestData.USER_ID), userId);
    }

    @Test
    public void create() {
        int userId = UserTestData.USER_ID;
        Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "newMeal", 777);
        service.create(newMeal, userId);
        assertMatch(service.getAll(userId), userMeal3, userMeal2, newMeal, userMeal1);
    }
}
package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;

    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id, int userId) {
        log.info("get id={} userId={}", id, userId);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id, int userId) {
        log.info("delete id={} userId={}", id, userId);
        service.delete(id, userId);
    }

    public void update(Meal meal) {
        log.info("update meal={}", meal);
        service.update(meal);
    }

    public Collection<MealWithExceed> getWithExceed(User user) {
        log.info("getWithExceed id={}", user.getId());
        return MealsUtil.getWithExceeded(getAll(user.getId()), user.getCaloriesPerDay());
    }
}
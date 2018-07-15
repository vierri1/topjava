package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get id={} userId={}", id, userId);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        meal.setUserId(SecurityUtil.authUserId());
        return service.create(meal);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete id={} userId={}", id, userId);
        service.delete(id, userId);
    }

    public void update(Meal meal) {
        meal.setUserId(SecurityUtil.authUserId());
        log.info("update meal={}", meal);
        service.update(meal);
    }

    public Collection<MealWithExceed> getWithExceed() {
        int userId = SecurityUtil.authUserId();
        log.info("getWithExceed id={}", userId);
        return MealsUtil.getWithExceeded(getAll(), DEFAULT_CALORIES_PER_DAY);
    }

    public Collection<MealWithExceed> getFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        Collection<MealWithExceed> meals = getWithExceed();

        if (startDate == null) {
            startDate = LocalDate.MIN;
        }
        if (endDate == null) {
            endDate = LocalDate.MAX;
        }
        if (startTime == null) {
            startTime = LocalTime.MIN;
        }
        if (endTime == null) {
            endTime = LocalTime.MAX;
        }

        LocalDate sDate = startDate;
        LocalDate eDate = endDate;
        LocalTime sTime = startTime;
        LocalTime eTime = endTime;

        return meals.stream().filter(meal -> DateTimeUtil.isBetweenDate(meal.getDateTime().toLocalDate(), sDate, eDate))
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), sTime, eTime))
                .collect(Collectors.toList());
    }
}
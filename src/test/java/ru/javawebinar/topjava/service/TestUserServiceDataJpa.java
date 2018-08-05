package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles( profiles = Profiles.DATAJPA)
public class TestUserServiceDataJpa extends AbstractUserServiceTest {

    @Test
    public void getWithMeal() {
        User user = service.getWithMeal(ADMIN_ID);
        assertMatch(user.getMeals(), ADMIN_MEAL2, ADMIN_MEAL1);
    }
}
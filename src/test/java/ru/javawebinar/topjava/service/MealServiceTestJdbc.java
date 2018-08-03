package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles( profiles = {Profiles.JDBC, Profiles.POSTGRES_DB})
public class MealServiceTestJdbc extends AbstractMealServiceTest {
}

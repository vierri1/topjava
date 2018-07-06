package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsData;
import ru.javawebinar.topjava.DAO.MealsDataHardCode;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private MealsData mealsData = MealsDataHardCode.getInstnce();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Meal> mealList = mealsData.getMeals();

        String action = request.getParameter("action");

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = getMealById(mealList, id);
            request.setAttribute("meal", meal);
            log.debug("redirect to update");
            request.getRequestDispatcher("meal_update.jsp").forward(request, response);
        }

        else if (action != null && action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            mealsData.deleteMeal(id);
            log.debug("redirect to meals from delete");
            response.sendRedirect("meals");
        }

        else if (action != null && action.equals("add")) {
            log.debug("redirect to meals from add");
            request.getRequestDispatcher("meal_add.jsp").forward(request, response);
        }

        else {
            List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(20, 0), 2000);
            request.setAttribute("mealList", mealsWithExceed);
            log.debug("redirect to meals form else");
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("datetime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (action != null && action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = new Meal(localDateTime, description, calories, id);
            mealsData.updateMeal(meal);
        }

        else if (action != null && action.equals("add")) {
            mealsData.createMeal(localDateTime, description, calories);

        }

        response.sendRedirect("meals");
    }

    private Meal getMealById(List<Meal> mealList, int id) {
        Meal meal = null;
        for (int i = 0; i < mealList.size(); i++) {
            if (mealList.get(i).getId() == id) {
                meal = mealList.get(i);
            }
        }
        return meal;
    }
}

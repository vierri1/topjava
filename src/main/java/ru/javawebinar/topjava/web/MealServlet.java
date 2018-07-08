package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.MealsData;
import ru.javawebinar.topjava.DAO.MealsDataHardCode;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MealsData mealsData;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealsData = new MealsDataHardCode();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collection<Meal> mealList = mealsData.getAll();

        String action = request.getParameter("action");

        if (action == null) {
            List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(20, 0), 2000);
            request.setAttribute("mealList", mealsWithExceed);
            log.debug("redirect to meals");
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }

        else if (action.equals("update")) {
            Meal meal = mealsData.get(getId(request));
            request.setAttribute("meal", meal);
            log.debug("redirect to update");
            request.getRequestDispatcher("meal_edit.jsp").forward(request, response);
        }

        else if (action.equals("add")) {
            Meal meal = new Meal(LocalDateTime.now(), "", 500, null);
            request.setAttribute("meal", meal);
            log.debug("redirect to create");
            request.getRequestDispatcher("meal_edit.jsp").forward(request, response);
        }

        else if (action.equals("delete")) {
            mealsData.delete(getId(request));
            log.debug("redirect from delete");
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("datetime"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String paramId = request.getParameter("id");

        Integer id = null;
        if (!paramId.equals("")) {
            id = Integer.parseInt(paramId);
        }

        Meal meal = new Meal(localDateTime, description, calories, id);
        mealsData.save(meal);

        response.sendRedirect("meals");
    }

    private Integer getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(id);
    }

}

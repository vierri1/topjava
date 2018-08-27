package ru.javawebinar.topjava.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends MealRestController {

    public JspMealController(MealService service) {
        super(service);
    }


    @GetMapping
    public String meals(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/create")
    public String createView(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "Your Description", 1000));
        model.addAttribute("action", "create");
        return "mealForm";
    }

    @PostMapping("/create")
    public String createMeal(@ModelAttribute("meal") Meal meal) {
        if (meal.isNew()) {
            super.create(meal);
        }
        else {
            super.update(meal, meal.getId());
        }
        return "redirect:/meals";
    }

    @GetMapping("/edit/{id}")
    public String editView(@PathVariable Integer id, Model model) {
        model.addAttribute("meal", super.get(id));
        model.addAttribute("action", "edit");
        return "mealForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @PostMapping("/filter")
    public String doFilter(@RequestParam Map<String, String> requestMap, Model model) {
        LocalDate startDate = parseLocalDate(requestMap.get("startDate").trim());
        LocalDate endDate = parseLocalDate(requestMap.get("endDate").trim());
        LocalTime startTime = parseLocalTime(requestMap.get("startTime").trim());
        LocalTime endTime = parseLocalTime(requestMap.get("endTime").trim());

        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }


}

package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet",urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final ChefService chefService;
    private final DishService dishService;
    private final SpringTemplateEngine springTemplateEngine;

    public ChefDetailsServlet(ChefService chefService,DishService dishService,SpringTemplateEngine springTemplateEngine){
        this.chefService = chefService;
        this.dishService = dishService;
        this.springTemplateEngine = springTemplateEngine;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        String chefIdStr = req.getParameter("chefId");
        String dishId = req.getParameter("dishId");
        if(chefIdStr == null || dishId == null){
            resp.sendRedirect(req.getContextPath() + "listChefs");
            return;
        }

        Long chefId;
        try {
            chefId = Long.parseLong(chefIdStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "listChefs");
            return;
        }

        Chef chef = chefService.findById(chefId);
        Dish dish = dishService.findByDishId(dishId);

        if(chef != null && dish != null){
            chefService.addDishToChef(chefId,dishId);
        }
        WebContext context = new WebContext(
                JakartaServletWebApplication.buildApplication(getServletContext())
                        .buildExchange(req, resp)
        );
        context.setVariable("chef",chef);
        springTemplateEngine.process("chefDetails",context,resp.getWriter());
    }
}

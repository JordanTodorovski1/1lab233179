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
import java.util.List;

@WebServlet(name = "DishServlet",urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final DishService dishService;
    private final ChefService chefService;
    private final SpringTemplateEngine springTemplateEngine;

    public DishServlet(DishService dishService,ChefService chefService,SpringTemplateEngine springTemplateEngine){
        this.dishService = dishService;
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String chefId = req.getParameter("chefId");
        if(chefId == null){
            resp.sendRedirect(req.getContextPath() + "/listChefs");
            return;
        }
        Long chefIdLong;
        try{chefIdLong = Long.parseLong(chefId);}
        catch (NumberFormatException e){resp.sendRedirect(req.getContextPath() + "/listChefs");return;}

        Chef chef = chefService.findById(chefIdLong);
        if(chef == null){resp.sendRedirect(req.getContextPath() + "/listChefs"); return;}

        List<Dish> dishes = dishService.listDishes();

        WebContext context = new WebContext(JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp));
        context.setVariable("chef",chef);
        context.setVariable("allDishes",dishes);

        springTemplateEngine.process("dishesList",context,resp.getWriter());
    }
}

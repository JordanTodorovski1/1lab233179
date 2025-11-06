package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

import java.util.List;


@WebServlet(name = "ChefListServlet",urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    protected final ChefService chefService;

    public ChefListServlet(SpringTemplateEngine templateEngine,ChefService chefService){
        this.templateEngine = templateEngine;
        this.chefService = chefService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String chefFirstName = req.getParameter("firstName");
        String chefLastName = req.getParameter("lastName");
        String bio = req.getParameter("bio");

        List<Chef> chefs = chefService.listChefs();
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chefs",chefs);

        templateEngine.process("listChefs", context, resp.getWriter());
    }
}

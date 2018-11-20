package controller;

import cache.CardCacheService;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class AuthController {
    private final AuthService auth;

    @Autowired
    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @GetMapping
    public ModelAndView doAuth(HttpServletRequest req) {
        if (req.getSession() != null) {
            req.getSession().invalidate();
        }
        CardCacheService ccs = new CardCacheService();  //start timer
        return new ModelAndView("welcome");
    }

    @PostMapping
    public void doAuth(
            HttpServletRequest req,
            HttpServletResponse resp,
            @RequestParam String login,
            @RequestParam String pass
    ) throws IOException {
        User u = auth.isUserRegistered(login, pass);
        if (u != null) {
            req.getSession().setAttribute("user", u);
            System.out.println("Authorization successful");
            resp.sendRedirect("/hs");
        } else {
            resp.sendRedirect("/test");
        }
    }
}

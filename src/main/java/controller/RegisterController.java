package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService auth;

    @Autowired
    public RegisterController(AuthService auth) {
        this.auth = auth;
    }

    @GetMapping
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping
    protected void doRegister(
            @RequestParam String login,
            @RequestParam String pass1,
            @RequestParam String pass2,
            @RequestParam String cla$$,
            HttpServletResponse response) throws IOException {
        if (!auth.checkCredentials(login, pass1, pass2, cla$$)) {
            response.sendRedirect("/test");
        } else {
            auth.addNewUser(login, pass1, 0, 0, "", 0, cla$$, Instant.now(), 100);
            System.out.println("Registration successful");
            response.sendRedirect("/");
        }
    }

}

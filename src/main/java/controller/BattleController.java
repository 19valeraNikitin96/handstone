package controller;

import cache.WaitCache;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/battle")
public class BattleController {

    @GetMapping("/wait")
    public ModelAndView waiting(HttpServletRequest req,
                                HttpServletResponse resp,
                                @RequestParam(required = false) String exit) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/");
            return null;
        } else {
            User waitUser = WaitCache.getByLogin(u.getLogin());
            if (waitUser == null) {
                WaitCache.add(u);
            }
            if (exit != null) {
                WaitCache.remove(u.getLogin());
                resp.sendRedirect("/hs");
                return null;
            } else {
                //TODO: forming battle
                if (WaitCache.size() > 1) {

                }
                ModelAndView out = new ModelAndView("wait");
                return out;
            }
        }


    }
}
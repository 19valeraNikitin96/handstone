package controller;

import battle.Battle;
import cache.BattleCache;
import cache.WaitCache;
import dao.HUserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HUserDao hudao;

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
            if(BattleCache.contains(u)){
                ModelAndView out = new ModelAndView("battle");
                out.addObject("battle", BattleCache.findBattleOfUser(u));
                out.addObject("currentUser",u);
                return out;
            }
            if (exit != null) {
                WaitCache.remove(u.getLogin());
                resp.sendRedirect("/hs");
                return null;
            } else {
                //TODO: forming battle

                if (WaitCache.size() > 1) {

                    Battle b = new Battle(u, WaitCache.randUser(u));
                    ModelAndView out = new ModelAndView("battle");
                    out.addObject("battle", b);
                    WaitCache.remove(b.player1.getLogin());
                    WaitCache.remove(b.player2.getLogin());
                    BattleCache.battles.add(b);
                    return out;
                }
                ModelAndView out = new ModelAndView("wait");
                return out;
            }
        }
    }



}

/*
System.out.println("*************OK!!!*************");

                User opp = hudao.getByLogin("driveall");
                WaitCache.add(opp);    //testing battle
                System.out.println(WaitCache.size());
                System.out.println("*******************************");
                if (WaitCache.size() > 1) {
                    System.out.println(opp.toString());
                    System.out.println(u.toString());
                    Battle b = new Battle(u, opp);
                    ModelAndView out = new ModelAndView("battle");
                    out.addObject("player1_cards", b.player1_cards);
                    out.addObject("player2_cards", b.player2_cards);

                    return out;
                }
                ModelAndView out = new ModelAndView("wait");
                return out;
 */
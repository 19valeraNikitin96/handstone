package controller;

import battle.Battle;
import cache.BattleCache;
import cache.WaitCache;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/battle")
public class BattleController {

    private final CardService cardService;

    @Autowired
    public BattleController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/wait")
    public ModelAndView waiting(HttpServletRequest req,
                                HttpServletResponse resp,
                                @RequestParam(required = false) String exit) throws IOException {
        //check if user logined
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/");
            return null;
        }
        //check if we already in the battle
        Battle battle = BattleCache.isInBattle(u.getLogin());
        if (battle != null) {
            req.getSession().setAttribute("battleId", battle.getId());
            resp.sendRedirect("/battle");
            return null;
        }
        //getting user from wait map
        User waitUser = WaitCache.getByLogin(u.getLogin());
        //if user not in those map - adding him
        if (waitUser == null) {
            WaitCache.add(u);
        }
        //exiting from wait map
        if (exit != null) {
            WaitCache.remove(u.getLogin());
            resp.sendRedirect("/hs");
            return null;
        }
        //check if we have an opponent - creating a battle
        if (WaitCache.size() > 1) {
            WaitCache.remove(u.getLogin());
            List<User> users = WaitCache.removeAndPrepareToBattle(u);
            Battle b = new Battle(new Random().nextInt());
            b.setLogin1(users.get(0).getLogin());
            b.setLogin2(users.get(1).getLogin());
            b.setDeck1(cardService.getCardsFromJson(users.get(0).getDeck()));
            b.setDeck2(cardService.getCardsFromJson(users.get(1).getDeck()));
            BattleCache.battles.put(b.getId(), b);
            req.getSession().setAttribute("battleId", b.getId());
            resp.sendRedirect("/battle");
            return null;
        } else {
            return new ModelAndView("wait");
        }
    }

    @GetMapping
    public ModelAndView battleWiew(HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException {
        //check if user logined
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/");
            return null;
        }
        Integer battleId = (Integer) req.getSession().getAttribute("battleId");
        if (battleId == null) {
            resp.sendRedirect("/");
            return null;
        }
        Battle b = BattleCache.battles.get(battleId);
        //TODO: for 42

        if (u.getLogin().equals(b.getLogin2())) {

            b = new Battle(b);
        }
        ModelAndView out = new ModelAndView("battle");
        out.addObject("b", b);
        out.addObject("player", (User) req.getSession().getAttribute("user"));
        return out;
    }
}
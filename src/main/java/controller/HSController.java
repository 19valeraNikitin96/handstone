package controller;

import battle.Battle;
import cache.BattleCache;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hs")
public class HSController {

    private final CardService card;

    @Autowired
    public HSController(CardService card) {
        this.card = card;
    }

    @GetMapping
    public ModelAndView hs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User u = (User) req.getSession().getAttribute("user");

        Battle battle = BattleCache.isInBattle(u.getLogin());
        if (battle != null) {
            req.getSession().setAttribute("battleId", battle.getId());
            resp.sendRedirect("/battle");
            return null;
        }
        if (u != null) {
            ModelAndView out = new ModelAndView("hs");
            out.addObject("u", u);
            List<Card> cards = card.getCardsFromJson(u.getDeck());
            out.addObject("cards", cards);
            out.addObject("deckQuantity", u.quantityOfCards());
            return out;
        } else {
            resp.sendRedirect("/");
            return null;
        }
    }
}

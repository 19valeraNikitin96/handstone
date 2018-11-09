package controller;

import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/deck")
public class DeckController {

    private final UserService userv;
    private final CardService cserv;

    @Autowired
    public DeckController(UserService userv, CardService cserv) {
        this.userv = userv;
        this.cserv = cserv;
    }

    @GetMapping
    public ModelAndView deck(HttpServletRequest req,
                             HttpServletResponse resp,
                             @RequestParam(required = false) String ourid,
                             @RequestParam(required = false) String allid) throws IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/");
            return null;
        } else {
            List<Card> deck = cserv.getCardsFromJson(u.getDeck());
            List<Card> all = cserv.getCards();
            all.removeAll(deck);
            ModelAndView out = new ModelAndView("deck");
            out.addObject("deck", deck);
            out.addObject("u", u);
            out.addObject("cards", all);
            return out;
        }
    }
}

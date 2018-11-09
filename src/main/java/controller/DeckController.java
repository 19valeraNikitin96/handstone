package controller;

import entity.Card;
import entity.Cards;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthService;
import service.CardService;
import service.JsonTransformer;
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
    private final AuthService auth;
    private final JsonTransformer json;

    @Autowired
    public DeckController(UserService userv, CardService cserv, AuthService auth, JsonTransformer json) {
        this.userv = userv;
        this.cserv = cserv;
        this.auth = auth;
        this.json = json;
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
            if (allid != null) {
                int id = Integer.parseInt(allid);
                Cards cards = json.getUserCardsIds(u.getDeck());
                cards.cards.add(id);
                u.setDeck(json.toJson(cards));
                auth.updateUser(u);
            }
            if (ourid != null) {
                Cards cards = json.getUserCardsIds(u.getDeck());
                cards.cards.remove(new Integer(ourid));
                u.setDeck(json.toJson(cards));
                auth.updateUser(u);
            }
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

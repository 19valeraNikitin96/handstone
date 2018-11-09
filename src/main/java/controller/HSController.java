package controller;

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
        if (u != null) {
            ModelAndView out = new ModelAndView("hs");
            out.addObject("u", u);
            out.addObject("cards", card.getCardsFromJson(u.getDeck()));
            out.addObject("deckQuantity", u.quantityOfCards());
            return out;
        } else {
            resp.sendRedirect("/");
            return null;
        }
    }
}

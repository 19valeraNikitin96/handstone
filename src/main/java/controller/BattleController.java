package controller;

import battle.Battle;
import cache.BattleCache;
import cache.WaitCache;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ModelAndView battleView(HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException {
        //check if user logined
        User u = (User) req.getSession().getAttribute("user");
        if (u == null) {
            resp.sendRedirect("/");
            return null;
        }
        Integer battleId = (Integer) req.getSession().getAttribute("battleId");
        if (battleId == null) {
            resp.sendRedirect("/hs");
            return null;
        }
        Battle b = BattleCache.battles.get(battleId);
        //check if decks is full
        if (!b.isSettedUp()) {
            b.setUpBattle();
        }

        if (u.getLogin().equals(b.getLogin2())) {
            b = new Battle(b);
        }
        ModelAndView out = new ModelAndView("battle");
        out.addObject("b", b);
        out.addObject("u", u);
        return out;
    }

    @PostMapping
    public void processMove(HttpServletRequest req,
                            HttpServletResponse resp,
                            @RequestParam(required = false) String end,
                            @RequestParam(required = false) String play,
                            @RequestParam(required = false) String choice,
                            @RequestParam(required = false) String unchoice,
                            @RequestParam(required = false) String attack) throws IOException {
        Integer battleId = (Integer) req.getSession().getAttribute("battleId");
        Battle b = BattleCache.battles.get(battleId);
        User u = (User) req.getSession().getAttribute("user");
        //end turn functionality
        if (end != null) {
            //give 1 card to 2;(for player1)
            if (b.getLogin1().equals(u.getLogin())) {
                b.getInHand2().add(b.getDeck2().remove(new Random().nextInt(b.getDeck2().size())));
            }
            // increment move; give 1 card to 1; update players mana;(for player2)
            else {
                b.setTurn(b.getTurn() + 1);
                b.getInHand1().add(b.getDeck1().remove(new Random().nextInt(b.getDeck1().size())));
                b.setMana1(b.getTurn());
                b.setMana2(b.getTurn());
            }
            //change move
            b.setMove1(!b.isMove1());
        }
        //play card func
        if (play != null) {
            int id = Integer.parseInt(play);
            Card c = null;
            //(for player1)
            if (b.getLogin1().equals(u.getLogin())) {
                for (Card card : b.getInHand1()) {
                    if (card.getId() == id) {
                        c = card;
                        b.getInHand1().remove(card);
                        b.setMana1(b.getMana1() - card.getCost());
                        break;
                    }
                }
                b.getOnTable1().add(c);
            }
            //for player2
            else {
                for (Card card : b.getInHand2()) {
                    if (card.getId() == id) {
                        c = card;
                        b.getInHand2().remove(card);
                        b.setMana2(b.getMana2() - card.getCost());
                        break;
                    }
                }
                b.getOnTable2().add(c);
            }
        }

        //choise of card
        if (choice != null) {
            Integer id = Integer.valueOf(choice);
            b.setCardChoosen(id);
        }

        //unchoise of card
        if (unchoice != null) {
            b.setCardChoosen(null);
        }

        //attack enemy func
        if (attack != null) {
            Integer attackedId = Integer.valueOf(attack);
            //for player1
            if (b.getLogin1().equals(u.getLogin())) {
                Card wichAttacks = null;
                for (Card c : b.getOnTable1()) {
                    if (b.getCardChoosen().equals(c.getId())) {
                        wichAttacks = c;
                        break;
                    }
                }
                //attack in hero
                if (attackedId.equals(0)) {
                    b.setHp2(b.getHp2() - wichAttacks.getAttack());
                    if (b.getHp2() <= 0) {
                        //end battle
                    }
                }
                //attack card
                else {
                    Card wichAttacked = null;
                    for (Card c : b.getOnTable2()) {
                        if (attackedId.equals(c.getId())) {
                            wichAttacked = c;
                            break;
                        }
                    }
                    //calculate damage
                    wichAttacked.setDefence(wichAttacked.getDefence() - wichAttacks.getAttack());
                    wichAttacks.setDefence(wichAttacks.getDefence() - wichAttacked.getAttack());
                    //if some of them died
                    if (wichAttacks.getDefence() <= 0) {
                        b.getOnTable1().remove(wichAttacks);
                    }
                    if (wichAttacked.getDefence() <= 0) {
                        b.getOnTable2().remove(wichAttacked);
                    }
                    b.setCardChoosen(null);
                }
            }
            //for player2
            else {
                Card wichAttacks = null;
                for (Card c : b.getOnTable2()) {
                    if (b.getCardChoosen().equals(c.getId())) {
                        wichAttacks = c;
                        break;
                    }
                }
                //attack in hero
                if (attackedId.equals(0)) {
                    b.setHp1(b.getHp1() - wichAttacks.getAttack());
                    if (b.getHp1() <= 0) {
                        //end battle
                    }
                }
                //attack card
                else {
                    Card wichAttacked = null;
                    for (Card c : b.getOnTable1()) {
                        if (attackedId.equals(c.getId())) {
                            wichAttacked = c;
                            break;
                        }
                    }
                    //calculate damage
                    wichAttacked.setDefence(wichAttacked.getDefence() - wichAttacks.getAttack());
                    wichAttacks.setDefence(wichAttacks.getDefence() - wichAttacked.getAttack());
                    //if some of them died
                    if (wichAttacks.getDefence() <= 0) {
                        b.getOnTable2().remove(wichAttacks);
                    }
                    if (wichAttacked.getDefence() <= 0) {
                        b.getOnTable1().remove(wichAttacked);
                    }
                    b.setCardChoosen(null);
                }
            }
        }

        resp.sendRedirect("/battle");
    }
}
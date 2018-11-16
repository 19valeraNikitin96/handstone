package battle;

import dao.HCardDao;
import entity.Card;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.CardService;
import service.JsonTransformer;

import java.util.LinkedList;
import java.util.List;

public class Battle {


    private static int battles = 0;

    private CardService card = new CardService(new HCardDao(), new JsonTransformer());

    @Autowired
    public Battle(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.player1_cards = card.getCardsFromJson(player1.getDeck());
        this.player2_cards = card.getCardsFromJson(player2.getDeck());

        table = new LinkedList<>();
        player1Turn = true;

    }

    public User player1;
    public User player2;

    public List<Card> player1_cards; //список карт игрока 1
    public List<Card> player2_cards;//список карт игрока 2

    public List<Card> table; //список карт на столе
    public boolean player1Turn;//чей ход

    public List<Card> getPlayer1_cards(){
        return this.player1_cards;
    }
    public List<Card> getPlayer2_cards(){
        return this.player2_cards;
    }

    public User getPlayer1(){
        return this.player1;
    }
    public User getPlayer2(){
        return this.player2;
    }

}

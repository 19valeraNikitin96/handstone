package service;

import com.google.gson.Gson;
import dao.HCardDao;
import entity.Card;
import entity.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CardService {

    private final HCardDao cardDao;

    @Autowired
    public CardService(HCardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> getCardsFromJson(String json) {
        List<Card> out = new LinkedList<>();
        Cards cards = new Gson().fromJson(json, Cards.class);
        for (Integer id : cards.cards) {
            out.add(cardDao.getById(id));
        }
        return out;
    }

    public List<Card> getCards() {
        return cardDao.get();
    }
}

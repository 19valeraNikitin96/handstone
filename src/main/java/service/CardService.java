package service;

import dao.CDaoInt;
import entity.Card;
import entity.Cards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CardService {

    private final CDaoInt cardDao;
    private final JsonTransformer jsonTransformer;

    @Autowired
    public CardService(CDaoInt cardDao, JsonTransformer json) {
        this.cardDao = cardDao;
        jsonTransformer = json;
    }

    public List<Card> getCardsFromJson(String json) {
        List<Card> out = new LinkedList<>();
        Cards cards = jsonTransformer.getUserCardsIds(json);

        for (Integer id : cards.cards) {
            Card test = cardDao.getById(id);
            //System.out.println(test.toString());  Error
            out.add(test);
        }
        return out;
    }

    public List<Card> getCards() {
        return cardDao.get();
    }
}

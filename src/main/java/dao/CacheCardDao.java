package dao;

import cache.CardCache;
import entity.Card;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class CacheCardDao implements CDaoInt{

    @Override
    public Card getById(int id) {
        for (Card c : get()) {
            //System.out.println(c.getId()+" <-> "+id);
            if (c.getId() == id) {
                return new Card(c);
            }
        }
        return null;
    }

    @Override
    public List<Card> get() {
        return new LinkedList<>(CardCache.cardsInside);
    }
}

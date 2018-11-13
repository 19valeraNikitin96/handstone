package dao;

import entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheCardDao implements CDaoInt{

    @Override
    public Card getById(int id) {
        return null;
    }

    @Override
    public List<Card> get() {
        return null;
    }
}

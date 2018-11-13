package cache;

import entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardCache {

    public static List<Card> cardsInside;
}

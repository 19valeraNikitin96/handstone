package dao;

import entity.Card;

import java.util.List;

public interface CDaoInt {
    Card getById(int id);

    List<Card> get();

}

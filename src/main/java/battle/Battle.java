package battle;

import entity.Card;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Battle {
    private final Integer id;

    private List<Card> deck1 = new CopyOnWriteArrayList<>();
    private List<Card> deck2 = new CopyOnWriteArrayList<>();
    private List<Card> inHand1 = new CopyOnWriteArrayList<>();
    private List<Card> inHand2 = new CopyOnWriteArrayList<>();
    private List<Card> onTable1 = new CopyOnWriteArrayList<>();
    private List<Card> onTable2 = new CopyOnWriteArrayList<>();

    private String login1;
    private String login2;

    private boolean isMove1 = true;

    private int hp1 = 20;
    private int hp2 = 20;

    private int turn = 1;

    private int mana1;
    private int mana2;

    private boolean settedUp = false;

    public Battle(int id) {
        this.id = id;
    }

    public Battle(Integer id, List<Card> deck1, List<Card> deck2, String login1, String login2) {
        this.id = id;
        this.deck1 = deck1;
        this.deck2 = deck2;
        this.login1 = login1;
        this.login2 = login2;
    }

    public Integer getId() {
        return id;
    }

    public List<Card> getDeck1() {
        return deck1;
    }

    public void setDeck1(List<Card> deck1) {
        this.deck1 = deck1;
    }

    public List<Card> getDeck2() {
        return deck2;
    }

    public void setDeck2(List<Card> deck2) {
        this.deck2 = deck2;
    }

    public List<Card> getInHand1() {
        return inHand1;
    }

    public void setInHand1(List<Card> inHand1) {
        this.inHand1 = inHand1;
    }

    public List<Card> getInHand2() {
        return inHand2;
    }

    public void setInHand2(List<Card> inHand2) {
        this.inHand2 = inHand2;
    }

    public List<Card> getOnTable1() {
        return onTable1;
    }

    public void setOnTable1(List<Card> onTable1) {
        this.onTable1 = onTable1;
    }

    public List<Card> getOnTable2() {
        return onTable2;
    }

    public void setOnTable2(List<Card> onTable2) {
        this.onTable2 = onTable2;
    }

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login1) {
        this.login1 = login1;
    }

    public String getLogin2() {
        return login2;
    }

    public void setLogin2(String login2) {
        this.login2 = login2;
    }

    public boolean isMove1() {
        return isMove1;
    }

    public void setMove1(boolean move1) {
        isMove1 = move1;
    }

    public int getHp1() {
        return hp1;
    }

    public void setHp1(int hp1) {
        this.hp1 = hp1;
    }

    public int getHp2() {
        return hp2;
    }

    public void setHp2(int hp2) {
        this.hp2 = hp2;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getMana1() {
        return mana1;
    }

    public void setMana1(int mana1) {
        this.mana1 = mana1;
    }

    public int getMana2() {
        return mana2;
    }

    public void setMana2(int mana2) {
        this.mana2 = mana2;
    }

    public boolean isSettedUp() {
        return settedUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(id, battle.id) &&
                isMove1 == battle.isMove1 &&
                hp1 == battle.hp1 &&
                hp2 == battle.hp2 &&
                turn == battle.turn &&
                mana1 == battle.mana1 &&
                mana2 == battle.mana2 &&
                Objects.equals(deck1, battle.deck1) &&
                Objects.equals(deck2, battle.deck2) &&
                Objects.equals(inHand1, battle.inHand1) &&
                Objects.equals(inHand2, battle.inHand2) &&
                Objects.equals(onTable1, battle.onTable1) &&
                Objects.equals(onTable2, battle.onTable2) &&
                Objects.equals(login1, battle.login1) &&
                Objects.equals(login2, battle.login2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deck1, deck2, inHand1, inHand2, onTable1, onTable2, login1, login2, isMove1, hp1, hp2, turn, mana1, mana2);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", deck1=" + deck1 +
                ", deck2=" + deck2 +
                ", inHand1=" + inHand1 +
                ", inHand2=" + inHand2 +
                ", onTable1=" + onTable1 +
                ", onTable2=" + onTable2 +
                ", login1='" + login1 + '\'' +
                ", login2='" + login2 + '\'' +
                ", isMove1=" + isMove1 +
                ", hp1=" + hp1 +
                ", hp2=" + hp2 +
                ", turn=" + turn +
                ", mana1=" + mana1 +
                ", mana2=" + mana2 +
                '}';
    }

    //конструктор реверсивного копирования
    public Battle(Battle b) {

        this.id = b.id;

        this.deck1 = b.deck2;
        this.deck2 = b.deck1;

        this.inHand1 = b.inHand2;
        this.inHand2 = b.inHand1;

        this.onTable2 = b.onTable1;
        this.onTable1 = b.onTable2;

        this.login1 = b.login2;
        this.login2 = b.login1;

        this.hp1 = b.hp2;
        this.hp2 = b.hp1;

        this.isMove1 = !b.isMove1;

        this.mana1 = b.mana2;
        this.mana2 = b.mana1;

        this.settedUp = b.isSettedUp();

    }

    public void setUpBattle() {
        getInHand1().add(getDeck1().remove(new Random().nextInt(10)));
        getInHand1().add(getDeck1().remove(new Random().nextInt(9)));
        getInHand2().add(getDeck2().remove(new Random().nextInt(10)));
        getInHand2().add(getDeck2().remove(new Random().nextInt(9)));
        //
        getInHand1().add(getDeck1().remove(new Random().nextInt(8)));
    }
}

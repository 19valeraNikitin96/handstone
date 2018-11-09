package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hs_card")
public class Card implements Serializable {
    @Id
    private int id;
    private String name;
    private String about;
    private int cost;
    private String pic;
    private boolean isSpell;
    private int attack;
    private int defence;
    private String type;
    private String action;

    public Card() {}

    public Card(int id, String name, String about, int cost, String pic, boolean isSpell, int attack, int defence, String type, String action) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.cost = cost;
        this.pic = pic;
        this.isSpell = isSpell;
        this.attack = attack;
        this.defence = defence;
        this.type = type;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean getIsSpell() {
        return isSpell;
    }

    public void setIsSpell(boolean isSpell) {
        this.isSpell = isSpell;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                cost == card.cost &&
                isSpell == card.isSpell &&
                attack == card.attack &&
                defence == card.defence &&
                Objects.equals(name, card.name) &&
                Objects.equals(about, card.about) &&
                Objects.equals(pic, card.pic) &&
                Objects.equals(type, card.type) &&
                Objects.equals(action, card.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, about, cost, pic, isSpell, attack, defence, type, action);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Card{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", about='").append(about).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", pic='").append(pic).append('\'');
        sb.append(", isSpell=").append(isSpell);
        sb.append(", attack=").append(attack);
        sb.append(", defence=").append(defence);
        sb.append(", type='").append(type).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package entity;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "hs_user")
public class User implements Serializable {
    @Id
    private  String id;
    private String login;
    private String pass;
    private int lvl;
    private int points;
    private String deck;
    private int vip;
    @Column(name = "class")
    private String cla$$;
    private Timestamp date;
    private int money;

    /*************************
     *      constructors     *
     *                       *
     *************************/
    public User(){}

    public User(String id, String login, String pass, int lvl, int points, String deck, int vip, String cla$$, Instant date, int money) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.lvl = lvl;
        this.points = points;
        this.deck = deck;
        this.vip = vip;
        this.cla$$ = cla$$;
        this.date = Timestamp.from(date);
        this.money = money;
    }

    /********************
     *      getters     *
     *                  *
     ********************/

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getLvl() {
        return lvl;
    }

    public int getPoints() {
        return points;
    }

    public String getDeck() {
        return deck;
    }

    public int getVip() {
        return vip;
    }

    public String getCla$$() {
        return cla$$;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getMoney() {
        return money;
    }

    /********************
     *      setters     *
     *                  *
     ********************/
        public void setId (String id){
            this.id = id;
        }

        public void setLogin (String login){
            this.login = login;
        }

        public void setPass (String pass){
            this.pass = pass;
        }

        public void setLvl ( int lvl){
            this.lvl = lvl;
        }

        public void setPoints ( int points){
            this.points = points;
        }

        public void setDeck (String deck){
            this.deck = deck;
        }

        public void setVip ( int vip){
            this.vip = vip;
        }

        public void setCla$$ (String cla$$){
            this.cla$$ = cla$$;
        }

    public void setDate(Timestamp date) {
            this.date = date;
        }

        public void setMoney ( int money){
            this.money = money;
        }

    /********************
     *      hash code   *
     *      equals      *
     *      toString    *
     *                  *
     ********************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return lvl == user.lvl &&
                points == user.points &&
                vip == user.vip &&
                money == user.money &&
                Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(deck, user.deck) &&
                Objects.equals(cla$$, user.cla$$) &&
                Objects.equals(date, user.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, pass, lvl, points, deck, vip, cla$$, date, money);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", pass='").append(pass).append('\'');
        sb.append(", lvl=").append(lvl);
        sb.append(", points=").append(points);
        sb.append(", deck='").append(deck).append('\'');
        sb.append(", vip=").append(vip);
        sb.append(", cla$$='").append(cla$$).append('\'');
        sb.append(", date=").append(date);
        sb.append(", money=").append(money);
        sb.append('}');
        return sb.toString();
    }

    /**********************************
     *     getting user card from JSon*
     *                                *
     **********************************/

    public int quantityOfCards(){
        Cards ca = new Gson().fromJson(deck, Cards.class);
        return ca.cards.size();
    }
}

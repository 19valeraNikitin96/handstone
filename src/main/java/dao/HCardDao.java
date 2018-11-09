package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HCardDao implements CDaoInt {

    @Override
    public Card getById(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Card out = (Card) s.createQuery("FROM Card WHERE id='" + id + "'").uniqueResult();
        s.getTransaction().commit();
        s.close();
        return out;
    }

    @Override
    public List<Card> get() {
        List<Card> l = new ArrayList<>();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        ResultSet rs = (ResultSet) s.createQuery("FROM Card");
        s.close();
        try {
        while(rs.next()){

                l.add(new Card(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("about"),
                        rs.getInt("cost"),
                        rs.getString("pic"),
                        rs.getInt("isSpell"),
                        rs.getInt("attack"),
                        rs.getInt("defence"),
                        rs.getString("type"),
                        rs.getString("action")
                ));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
}

package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

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
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        List<Card> out = s.createQuery("FROM Card").list();
        s.close();
        return out;
    }
}

package dao;

import entity.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//@Component
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
        List<Card> tmp = s.createCriteria(Card.class).list();//s.createQuery("FROM Card").list();
        s.close();
        return new CopyOnWriteArrayList<>(tmp);
    }
}

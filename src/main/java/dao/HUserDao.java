package dao;

import entity.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class HUserDao implements UDaoInt {
    @Override
    public User getByLogin(String login) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        User out = (User) s.createQuery("FROM User WHERE login='" + login + "'").uniqueResult();
        s.close();
        return out;
    }

    @Override
    public void add(User u) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(User u) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }
}

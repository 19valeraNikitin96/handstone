package service;

import dao.HUserDao;
import dao.UDaoInt;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.SpringContextHolder;

import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private UDaoInt udao = new HUserDao();

    public boolean checkCredentials(String login, String pass1, String pass2, String cla$$) {
        if (login.length() == 0 || pass1.length() == 0 || cla$$.length() == 0) {
            return false;
        }
        if (!pass1.equals(pass2)) {
            return false;
        }
        return udao.getByLogin(login) == null;
    }

    public void addNewUser(String login, String pass, int lvl, int points, String deck, int vip, String cla$$, Date date, int money) {
        User u = (User) SpringContextHolder.getContext().getBean("user");
        u.setId(randomId());
        u.setLogin(login);
        u.setPass(pass);
        u.setLvl(lvl);
        u.setPoints(points);
        u.setDeck(deck);
        u.setVip(vip);
        u.setCla$$(cla$$);
        //u.setDate(date);
        u.setMoney(money);
        udao.add(u);
    }

    public User isUserRegistered(String login, String pass) {
        User out = udao.getByLogin(login);
        if (out == null) {
            return null;
        }
        if (!out.getPass().equals(pass)) {
            return null;
        }
        return out;
    }

    public void updateUser(User u) {
        udao.update(u);
    }

    /****************************
     *      randomId()          *
     *      generate unique Id  *
     *      for new User        *
     ****************************/
    private String randomId(){
        String buf = "";
        for(int i = 0; i < buf.length(); i=i+3){
            buf += Math.random()*('Z'-'A')+'A';
            buf += Math.random()*('z'-'a')+'a';
            buf += Math.random()*('9'-'0')+'0';
        }
        return buf;
    }


}

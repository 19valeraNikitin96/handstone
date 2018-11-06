package dao;

import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends Dao implements UDaoInt {

    public User getByLogin(String login) {
        User out = null;
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM hs_user WHERE login='" + login + "'");
            rs.next();
            out = new User(rs.getString("id"),
                    rs.getString("login"),
                    rs.getString("pass"),
                    rs.getInt("lvl"),
                    rs.getInt("points"),
                    rs.getString("deck"),
                    rs.getInt("vip"),
                    rs.getString("class"),
                    rs.getDate("date"),
                    rs.getInt("money")
            );
        } catch (SQLException ex) {
            System.out.println("====Result set is empty");
        }
        return out;
    }

    public void add(User u) {
        try (Connection c = getConnection();
             Statement st = c.createStatement()) {
            st.executeUpdate("INSERT INTO hs_user VALUES("
                    + u.getId()
                    + ", '"
                    + u.getLogin()
                    + "', '"
                    + u.getPass()
                    + "', '"
                    + u.getLvl()
                    + "', '"
                    + u.getPoints()
                    + "', '"
                    + u.getDeck()
                    + "')"
                    + u.getVip()
                    + "')"
                    + u.getCla$$()
                    + "')"
                    + u.getDate()
                    + "')"
                    + u.getMoney()
                    + "')"
            );
        } catch (SQLException ex) {
            System.out.println("====User not added");
        }
    }

    @Override
    public void update(User u) {
        System.out.println("Service not working!");
    }
}

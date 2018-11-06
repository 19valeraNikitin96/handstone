package dao;

import entity.User;

public interface UDaoInt {
    User getByLogin(String login);

    void add(User u);

    void update(User u);
}

package cache;

import entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WaitCache {
    private static Map<String, User> waitUsers = new ConcurrentHashMap<>();

    public static User getByLogin(String login) {
        return waitUsers.get(login);
    }

    public static void add(User u) {
        waitUsers.put(u.getLogin(), u);
    }

    public static void remove(String login) {
        waitUsers.remove(login);
    }

    public static int size() {
        return waitUsers.size();
    }

    public static List<User> removeAndPrepareToBattle(User u) {
        List<User> out = new LinkedList<>();
        out.add(u);
        waitUsers.remove(u.getLogin());
        Set<String> userLogins = waitUsers.keySet();
        for (String s : userLogins) {
            out.add(waitUsers.remove(s));
            break;
        }
        return out;
    }
}

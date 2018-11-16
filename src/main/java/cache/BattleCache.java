package cache;

import battle.Battle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleCache {
    public static Map<Integer, Battle> battles = new ConcurrentHashMap<>();

    public static Battle isInBattle(String login) {
        for (Integer id : battles.keySet()) {
            Battle b = battles.get(id);
            if (b.getLogin2().equals(login) || b.getLogin1().equals(login)) {
                return b;
            }
        }
        return null;
    }
}

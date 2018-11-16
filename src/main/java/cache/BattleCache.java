package cache;

import battle.Battle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleCache {
    public static Map<Integer, Battle> battles = new ConcurrentHashMap<>();
}

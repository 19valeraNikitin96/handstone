package cache;

import battle.Battle;
import entity.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BattleCache {
    public static List<Battle> battles = Collections.synchronizedList(new LinkedList<>());

    //метод contains проверяет наличие User u во всех объектах Battle
    public static boolean contains(User u){

        for(Battle b : battles){

            if(u.equals(b.player1)){
                return true;
            }
            if(u.equals(b.player2)){
                return true;
            }
        }
        return false;
    }

    //находит объект Battle, в котором на ходится User u
    public static Battle findBattleOfUser(User u ){
        for(Battle b : battles){
            if(u.equals(b.player1)){
                return b;
            }
            if(u.equals(b.player2)){
                return b;
            }
        }
        return null;
    }
}

package Game.event;

import java.util.ArrayList;
import java.util.List;

import Game.enemy.Enemy;
import Game.player.Player;

public class CombatEventBus {
    private final List<CombatObserver> observers = new ArrayList<>();

    public void register(CombatObserver observer) {
        observers.add(observer);
    }

    public void unregister(CombatObserver observer) {
        observers.remove(observer);
    }

    public void notifyAttack(String attackerName, String targetName, int damage, int targetHp, int targetMaxHp) {
        for (CombatObserver observer : observers) {
            observer.onAttack(attackerName, targetName, damage, targetHp, targetMaxHp);
        }
    }

    public void notifyDefeat(Player player, Enemy enemy) {
        for (CombatObserver observer : observers) {
            observer.onDefeat(player, enemy);
        }
    }
}

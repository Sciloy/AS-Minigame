package Game.event;

import Game.enemy.Enemy;
import Game.player.Player;

public interface CombatObserver {
    void onAttack(String attackerName, String targetName, int damage, int targetHp, int targetMaxHp);
    void onDefeat(Player player, Enemy enemy);
}

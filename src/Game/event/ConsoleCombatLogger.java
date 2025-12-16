package Game.event;

import Game.enemy.Enemy;
import Game.player.Player;

public class ConsoleCombatLogger implements CombatObserver {
    @Override
    public void onAttack(String attackerName, String targetName, int damage, int targetHp, int targetMaxHp) {
        System.out.println(attackerName + " hits " + targetName + " for " + damage);
        System.out.println(targetName + " HP: " + targetHp + " / " + targetMaxHp);
    }

    @Override
    public void onDefeat(Player player, Enemy enemy) {
        if (player != null && player.isDead()) {
            System.out.println("Player has fallen !");
        }
        if (enemy != null && enemy.isDead()) {
            System.out.println(enemy.getName() + " has been defeated!");
        }
    }

    @Override
    public void onEnrage(String enemyName) {
        System.out.println(enemyName + " becomes enraged!");
    }
}

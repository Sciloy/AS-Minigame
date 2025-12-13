package Game.strategy;

import java.util.Random;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;
import Game.player.Player;

public class DefensiveAttack implements AttackStrategy {
    private static final Random random = new Random();

    @Override
    public void attack(Enemy self, Player target, CombatEventBus bus) {
        int damage = Math.max(0, self.getBaseDamage() - 5);
        target.takeDamage(damage);
        bus.notifyAttack(self.getName(), "Player", damage, target.getHp(), target.getMaxHp());
        if (target.isDead()) {
            bus.notifyDefeat(target, self);
            return;
        }

        // 20% chance to daze player and make them skip next turn
        if (random.nextDouble() < 0.20) {
            target.setSkipNextTurn(true);
            System.out.println("Player is dazed and will skip their next turn!");
        }
    }
}

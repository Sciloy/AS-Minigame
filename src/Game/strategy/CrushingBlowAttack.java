package Game.strategy;

import java.util.concurrent.ThreadLocalRandom;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;
import Game.player.Player;

public class CrushingBlowAttack implements AttackStrategy {
    @Override
    public void attack(Enemy self, Player target, CombatEventBus bus) {
        if (ThreadLocalRandom.current().nextDouble() > 0.7) {
            System.out.println(self.getName() + "'s crushing blow missed!");
            return;
        }
        int damage = self.getBaseDamage() + 10;
        target.takeDamage(damage);
        bus.notifyAttack(self.getName(), "Player", damage, target.getHp(), target.getMaxHp());
        if (target.isDead()) {
            bus.notifyDefeat(target, self);
        }
    }
}

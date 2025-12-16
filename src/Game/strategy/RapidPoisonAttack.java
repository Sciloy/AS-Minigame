package Game.strategy;

import java.util.concurrent.ThreadLocalRandom;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;
import Game.player.Player;

public class RapidPoisonAttack implements AttackStrategy {
    @Override
    public void attack(Enemy self, Player target, CombatEventBus bus) {
        int shotDamage = Math.max(1, self.getBaseDamage() - 6);
        int hits = 0;
        for (int i = 0; i < 2; i++) {
            if (ThreadLocalRandom.current().nextDouble() > 0.65) {
                System.out.println(self.getName() + "'s quick shot " + (i + 1) + " missed!");
                continue;
            }
            target.takeDamage(shotDamage);
            bus.notifyAttack(self.getName() + " quick shot " + (i + 1), "Player", shotDamage, target.getHp(), target.getMaxHp());
            hits++;
            if (target.isDead()) {
                bus.notifyDefeat(target, self);
                return;
            }
        }
        if (hits > 0) {
            target.addPoison(2);
            System.out.println("Player is further poisoned!");
        }
    }
}

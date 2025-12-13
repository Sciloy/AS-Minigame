package Game.strategy;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;
import Game.player.Player;

public class PoisonAttack implements AttackStrategy {
    @Override
    public void attack(Enemy self, Player target, CombatEventBus bus) {
        int damage = self.getBaseDamage();
        target.takeDamage(damage);
        bus.notifyAttack(self.getName(), "Player", damage, target.getHp(), target.getMaxHp());
        if (target.isDead()) {
            bus.notifyDefeat(target, self);
            return;
        }
        target.addPoison(3);
        System.out.println("Player is poisoned! +3 ongoing damage each turn.");
    }
}

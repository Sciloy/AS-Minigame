package Game.strategy;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;
import Game.player.Player;

public interface AttackStrategy {
    void attack(Enemy self, Player target, CombatEventBus bus);
}

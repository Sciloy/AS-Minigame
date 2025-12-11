package Game.app;

import Game.logic.GameLogic;
import Game.factory.EnemyCreator;
import Game.enemy.Enemy;
import Game.player.Player;
import Game.event.CombatEventBus;
import Game.event.ConsoleCombatLogger;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(100);
        CombatEventBus bus = new CombatEventBus();
        bus.register(new ConsoleCombatLogger());

        EnemyCreator creator = GameLogic.selectCreator();
        Enemy enemy = creator.createEnemy();

        System.out.println("A wild " + enemy.getName() + " appears!");

        while (!player.isDead() && !enemy.isDead()) {
            player.attack(enemy, bus);
            if (enemy.isDead()) break;

            enemy.attack(player, bus);
        }

        System.out.println("Combat ended. Player HP: " + player.getHp() + "/" + player.getMaxHp());
    }
}

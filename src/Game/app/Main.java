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

        int round = 1;
        while (!player.isDead() && !enemy.isDead()) {
            System.out.println();
            System.out.println("=== Round " + round + " ===");
            // Poison tick at the start of player's turn
            if (player.applyPoisonTick(bus)) {
                break;
            }

            if (player.shouldSkipTurn()) {
                System.out.println("Player skips this turn due to daze!");
                player.consumeSkip();
            } else {
                System.out.println("-- Player turn --");
                player.attack(enemy, bus);
            }
            if (enemy.isDead()) break;

            System.out.println("-- Enemy turn --");
            enemy.attack(player, bus);
            if (player.isDead()) {
                break;
            }
            round++;

            if (!player.isDead() && !enemy.isDead()) {
                pauseBetweenRounds();
            }
        }

        System.out.println("Combat ended. Player HP: " + player.getHp() + "/" + player.getMaxHp());
    }

    private static void pauseBetweenRounds() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

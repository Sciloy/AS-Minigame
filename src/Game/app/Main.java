package Game.app;

import Game.logic.GameLogic;
import Game.factory.EnemyCreator;
import Game.enemy.Enemy;
import Game.player.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(100);
        EnemyCreator creator = GameLogic.selectCreator();
        Enemy enemy = creator.createEnemy();
        enemy.attack(player);
    }
}
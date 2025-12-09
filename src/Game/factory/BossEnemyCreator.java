package Game.factory;

import Game.enemy.Enemy;
import Game.enemy.BossEnemy;

public class BossEnemyCreator implements EnemyCreator {
    public Enemy createEnemy() { return new BossEnemy(); }
}
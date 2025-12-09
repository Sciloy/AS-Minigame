package Game.factory;

import Game.enemy.Enemy;
import Game.enemy.RangedEnemy;

public class RangedEnemyCreator implements EnemyCreator {
    public Enemy createEnemy() { return new RangedEnemy(); }
}
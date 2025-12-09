package Game.factory;

import Game.enemy.Enemy;
import Game.enemy.MeleeEnemy;

public class MeleeEnemyCreator implements EnemyCreator {
    public Enemy createEnemy() { return new MeleeEnemy(); }
}

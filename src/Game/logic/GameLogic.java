package Game.logic;

import java.util.Random;
import Game.factory.*;

public class GameLogic {
    private static Random random = new Random();

    public static EnemyCreator selectCreator() {
        double r = random.nextDouble();
        if (r < 0.4) return new MeleeEnemyCreator();
        if (r < 0.8) return new RangedEnemyCreator();
        return new BossEnemyCreator();
    }
}


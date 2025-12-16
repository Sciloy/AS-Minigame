package Game.enemy;

public class BossEnemy extends Enemy {
    public BossEnemy() {
        this.name = "Boss";
        this.baseDamage = 16;
        this.maxHp = 90;
        this.hp = maxHp;
        setStrategy(new Game.strategy.DefensiveAttack());
        setEnragedStrategy(new Game.strategy.CrushingBlowAttack());
    }
}

package Game.enemy;

public class MeleeEnemy extends Enemy {
    public MeleeEnemy() {
        this.name = "Fighter";
        this.baseDamage = 8;
        this.maxHp = 30;
        this.hp = maxHp;
        setStrategy(new Game.strategy.AggressiveAttack());
    }
}

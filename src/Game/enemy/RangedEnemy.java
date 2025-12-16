package Game.enemy;

public class RangedEnemy extends Enemy {
    public RangedEnemy() {
        this.name = "Ranger";
        this.baseDamage = 18;
        this.maxHp = 45;
        this.hp = maxHp;
        setStrategy(new Game.strategy.PoisonAttack());
        setEnragedStrategy(new Game.strategy.RapidPoisonAttack());
    }
}

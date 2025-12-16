package Game.enemy;

public class MeleeEnemy extends Enemy {
    public MeleeEnemy() {
        this.name = "Fighter";
        this.baseDamage = 10;
        this.maxHp = 70;
        this.hp = maxHp;
        setStrategy(new Game.strategy.PoisonAttack()); // baseline weaker
        setEnragedStrategy(new Game.strategy.AggressiveAttack()); // enrage for harder hits
    }
}

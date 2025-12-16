package Game.enemy;

import Game.player.Player;
import Game.event.CombatEventBus;
import Game.strategy.AttackStrategy;

public abstract class Enemy {
    protected String name;
    protected int baseDamage;
    protected int hp;
    protected int maxHp;
    protected AttackStrategy strategy;
    protected AttackStrategy enragedStrategy;
    protected boolean enraged;

    public void attack(Player p, CombatEventBus bus) {
        if (!enraged && enragedStrategy != null && hp <= maxHp * 0.3) {
            enraged = true;
            strategy = enragedStrategy;
            bus.notifyEnrage(name);
        }
        if (strategy == null) {
            p.takeDamage(baseDamage);
            bus.notifyAttack(name, "Player", baseDamage, p.getHp(), p.getMaxHp());
            if (p.isDead()) {
                bus.notifyDefeat(p, this);
            }
            return;
        }
        strategy.attack(this, p, bus);
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isDead() { return hp <= 0; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getBaseDamage() { return baseDamage; }
    public void setStrategy(AttackStrategy strategy) { this.strategy = strategy; }
    public void setEnragedStrategy(AttackStrategy strategy) { this.enragedStrategy = strategy; }
}

package Game.player;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;

public class Player {
    private int hp;
    private int maxHp;
    private int baseDamage;
    private int poisonDamage;
    private boolean skipNextTurn;

    public Player(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseDamage = 12;
        this.poisonDamage = 0;
        this.skipNextTurn = false;
    }

    public void attack(Enemy enemy, CombatEventBus bus) {
        enemy.takeDamage(this.baseDamage);
        bus.notifyAttack("Player", enemy.getName(), baseDamage, enemy.getHp(), enemy.getMaxHp());
        if (enemy.isDead()) {
            bus.notifyDefeat(this, enemy);
        }
    }

    public boolean applyPoisonTick(CombatEventBus bus) {
        if (poisonDamage <= 0) return false;
        hp -= poisonDamage;
        if (hp < 0) {
            hp = 0;
        }
        bus.notifyAttack("Poison", "Player", poisonDamage, hp, maxHp);
        if (isDead()) {
            bus.notifyDefeat(this, null);
            return true;
        }
        return false;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void addPoison(int amount) { poisonDamage += amount; }
    public void setSkipNextTurn(boolean skip) { this.skipNextTurn = skip; }
    public boolean shouldSkipTurn() { return skipNextTurn; }
    public void consumeSkip() { this.skipNextTurn = false; }

    public boolean isDead() { return hp <= 0; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}

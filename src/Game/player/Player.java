package Game.player;

import Game.enemy.Enemy;
import Game.event.CombatEventBus;

public class Player {
    private int hp;
    private int maxHp;
    private int baseDamage;

    public Player(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseDamage = 12;
    }

    public void attack(Enemy enemy, CombatEventBus bus) {
        enemy.takeDamage(this.baseDamage);
        bus.notifyAttack("Player", enemy.getName(), baseDamage, enemy.getHp(), enemy.getMaxHp());
        if (enemy.isDead()) {
            bus.notifyDefeat(this, enemy);
        }
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isDead() { return hp <= 0; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}

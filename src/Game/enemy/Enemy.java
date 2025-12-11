package Game.enemy;

import Game.player.Player;
import Game.event.CombatEventBus;

public abstract class Enemy {
    protected String name;
    protected int baseDamage;
    protected int hp;
    protected int maxHp;

    public void attack(Player p, CombatEventBus bus) {
        p.takeDamage(baseDamage);
        bus.notifyAttack(name, "Player", baseDamage, p.getHp(), p.getMaxHp());
        if (p.isDead()) {
            bus.notifyDefeat(p, this);
        }
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
}

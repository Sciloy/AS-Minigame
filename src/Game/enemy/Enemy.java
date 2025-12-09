package Game.enemy;

import Game.player.Player;

public abstract class Enemy {
    protected String name;
    protected int baseDamage;

    public void attack(Player p) {
        System.out.println(name + " hits for " + baseDamage);
        p.takeDamage(baseDamage);
    }
}
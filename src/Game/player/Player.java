package Game.player;

public class Player {
    private int hp;
    private int maxHp;

    public Player(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
    }
    
    public void takeDamage(int dmg) {
        hp -= dmg;
        //if (hp < 0) hp = 0;
        System.out.println("Player HP: " + hp + " / " + maxHp);
    }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}
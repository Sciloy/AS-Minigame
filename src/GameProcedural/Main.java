package GameProcedural;

import java.util.Random;

public class Main {

    static int playerHp = 100;
    static int playerMaxHp = 100;
    enum EnemyType {MELEE, RANGED, BOSS}


    static Random random = new Random();


    static EnemyType selectEnemy() {
        double r = random.nextDouble();
        if (r < 0.6) return EnemyType.MELEE;   
        if (r < 0.9) return EnemyType.RANGED;
        return EnemyType.BOSS;       
    }

    static int baseDamage(EnemyType t) {
        switch (t) {
            case MELEE:  return 8; 
            case RANGED: return 15; 
            case BOSS:   return 21; 
            default:     return 0;
        }
    }
    static String enemyName(EnemyType t) {
        switch (t) {
            case MELEE:  return "Fighter";
            case RANGED: return "Ranger";
            case BOSS:   return "Boss";
            default:     return "UnknownEnemy";
        }
    }
    static void attack(EnemyType t) {
        int dmg = baseDamage(t);
        System.out.println(enemyName(t) + " hits for " + dmg);
        playerHp = playerHp - dmg;
        System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);
    }

    public static void main(String[] args) {
        EnemyType e = selectEnemy();
        attack(e); 
    }
}





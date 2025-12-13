package GameProcedural;

import java.util.Random;

public class Main {

    static int playerHp = 100;
    static int playerMaxHp = 100;
    static int playerPoison = 0;
    static boolean playerSkip = false;
    static int enemyHp = 0;
    static int enemyMaxHp = 0;
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
    static int enemyHpValue(EnemyType t) {
        switch (t) {
            case MELEE:  return 30;
            case RANGED: return 24;
            case BOSS:   return 60;
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
    static void enemyAttacksPlayer(EnemyType t) {
        int dmg = baseDamage(t);
        switch (t) {
            case MELEE: // Aggressive: +5 dmg
                dmg += 5;
                break;
            case RANGED: // Poison: base dmg, adds poison
                break;
            case BOSS: // Defensive: -5 dmg, 20% chance to daze player
                dmg = Math.max(0, dmg - 5);
                if (random.nextDouble() < 0.20) {
                    playerSkip = true;
                    System.out.println("Player is dazed and will skip their next turn!");
                }
                break;
            
            default:
                break;
        }
        System.out.println(enemyName(t) + " hits Player for " + dmg);
        playerHp = playerHp - dmg;
        if (playerHp < 0) playerHp = 0;
        System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);

        if (t == EnemyType.RANGED) {
            playerPoison += 3;
            System.out.println("Player is poisoned! +3 ongoing damage each turn.");
        }
    }

    static void playerAttacksEnemy(EnemyType t) {
        int dmg = 12;
        // Manual prints repeated for each enemy branch below
        switch (t) {
            case MELEE:
                System.out.println("Player hits Fighter for " + dmg);
                enemyHp -= dmg;
                System.out.println("Fighter HP: " + enemyHp + " / " + enemyMaxHp);
                break;
            case RANGED:
                System.out.println("Player hits Ranger for " + dmg);
                enemyHp -= dmg;
                System.out.println("Ranger HP: " + enemyHp + " / " + enemyMaxHp);
                break;
            case BOSS:
                System.out.println("Player hits Boss for " + dmg);
                enemyHp -= dmg;
                System.out.println("Boss HP: " + enemyHp + " / " + enemyMaxHp);
                break;
            default:
                break;
        }
        if (enemyHp < 0) enemyHp = 0;
    }

    public static void main(String[] args) {
        EnemyType enemyType = selectEnemy();
        enemyHp = enemyHpValue(enemyType);
        enemyMaxHp = enemyHp;

        System.out.println("A wild " + enemyName(enemyType) + " appears!");

        while (playerHp > 0 && enemyHp > 0) {
            // Poison tick on player at start of turn
            if (playerPoison > 0) {
                playerHp -= playerPoison;
                if (playerHp < 0) playerHp = 0;
                System.out.println("Poison hits Player for " + playerPoison);
                System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);
                if (playerHp <= 0) {
                    System.out.println("Player defeated!");
                    break;
                }
            }

            if (playerSkip) {
                System.out.println("Player skips this turn due to daze!");
                playerSkip = false;
            } else {
                playerAttacksEnemy(enemyType);
            }
            if (enemyHp <= 0) {
                System.out.println(enemyName(enemyType) + " defeated!");
                break;
            }

            enemyAttacksPlayer(enemyType);
            if (playerHp <= 0) {
                System.out.println("Player defeated!");
                break;
            }
        }
    }
}


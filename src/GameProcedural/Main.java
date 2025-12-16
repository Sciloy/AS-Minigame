package GameProcedural;

import java.util.Random;

public class Main {

    static int playerHp = 100;
    static int playerMaxHp = 100;
    static int playerPoison = 0;
    static boolean playerSkip = false;
    static boolean enemyEnraged = false;
    static int enemyHp = 0;
    static int enemyMaxHp = 0;
    enum EnemyType {MELEE, RANGED, BOSS}

    static Random random = new Random();

    static EnemyType selectEnemy() {
        double r = random.nextDouble();
        if (r < 0.4) return EnemyType.MELEE;   
        if (r < 0.8) return EnemyType.RANGED;
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
        if (!enemyEnraged && enemyHp <= enemyMaxHp * 0.3) {
            enemyEnraged = true;
            System.out.println(enemyName(t) + " becomes enraged!");
        }
        int dmg = baseDamage(t);
        switch (t) {
            case MELEE:
                if (enemyEnraged) {
                    dmg += 5; // aggressive mode
                }
                break;
            case RANGED:
                if (enemyEnraged) {
                    rapidPoison();
                    return;
                }
                break;
            case BOSS:
                dmg = Math.max(0, dmg - 5); // defensive base
                if (random.nextDouble() < 0.20) {
                    playerSkip = true;
                    System.out.println("Player is dazed and will skip their next turn!");
                }
                if (enemyEnraged) {
                    crushingBlow();
                    return;
                }
                break;
            default:
                break;
        }
        System.out.println(enemyName(t) + " hits Player for " + dmg);
        playerHp = playerHp - dmg;
        if (playerHp < 0) playerHp = 0;
        System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);

        if (t == EnemyType.RANGED && !enemyEnraged) {
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

        int round = 1;
        while (playerHp > 0 && enemyHp > 0) {
            System.out.println();
            System.out.println("=== Round " + round + " ===");
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
                System.out.println("-- Player turn --");
                playerAttacksEnemy(enemyType);
            }
            if (enemyHp <= 0) {
                System.out.println(enemyName(enemyType) + " defeated!");
                break;
            }

            System.out.println("-- Enemy turn --");
            enemyAttacksPlayer(enemyType);
            if (playerHp <= 0) {
                System.out.println("Player defeated!");
                break;
            }
            round++;

            if (playerHp > 0 && enemyHp > 0) {
                pauseBetweenRounds();
            }
        }
    }

    static void rapidPoison() {
        int shotDmg = Math.max(1, baseDamage(EnemyType.RANGED) - 6);
        int hits = 0;
        for (int i = 0; i < 2; i++) {
            if (random.nextDouble() > 0.65) {
                System.out.println("Ranger's quick shot " + (i + 1) + " missed!");
                continue;
            }
            playerHp -= shotDmg;
            if (playerHp < 0) playerHp = 0;
            System.out.println("Ranger quick shot " + (i + 1) + " hits Player for " + shotDmg);
            System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);
            hits++;
            if (playerHp <= 0) break;
        }
        if (hits > 0) {
            playerPoison += 2;
            System.out.println("Player is further poisoned!");
        }
    }

    static void crushingBlow() {
        if (random.nextDouble() > 0.7) {
            System.out.println("Boss's crushing blow missed!");
            return;
        }
        int dmg = baseDamage(EnemyType.BOSS) + 10;
        playerHp -= dmg;
        if (playerHp < 0) playerHp = 0;
        System.out.println("Boss lands a crushing blow for " + dmg);
        System.out.println("Player HP: " + playerHp + " / " + playerMaxHp);
    }

    static void pauseBetweenRounds() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}

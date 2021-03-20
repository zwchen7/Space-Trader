import java.util.Random;
//import java.util.HashMap;
/**
 *  Class representing basic functionality of the Bandit
 *
 *  @author Zhi Wei Chen
 *  @version 1.0
 */

public class Bandit {
    private Random rand = new Random();
    private int payment;

    public Bandit() {
        payment = 100 + rand.nextInt(200);
    }

    @Override
    public String toString() {
        return "You have encountered a group of bandits, they demand you pay them "
                + payment + " credits";
    }

    /*
    1 = player has enough credits
    2 = player does not have enough credits, the inventory has being cleared instead
    3 = player does not have enough credits or any items, ship's health - 50
    4 = player's ship does not have any more health (game over?)
     */

    public int pay(Player player, Ship ship) {
        if (player.getCredits() > payment) {
            player.setCredits(player.getCredits() - payment);
            System.out.println("You paid the bandits to not attack you.");
            return 1;
        } else if (!ship.getInventory().isEmpty()) {
            ship.getInventory().clear();
            System.out.println("The bandits took all of your items...");
            System.out.println("You shouldn't have tried to pay without enough credits.");
            return 2;
        } else {
            ship.setHealth(ship.getHealth() - 50);
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");
                return 4;
            } else {
                System.out.println("Your ship was damaged!");
                System.out.println("Your health is now " + ship.getHealth());
                System.out.println("Your ship now has " + ship.getFuel() + " fuel.");
                return 3;
            }
        }
    }

    /*
    1 = player successfully fled
    2 = the player failed to flee and lost all credit and ship's health - 50
    3 = player's ship does not have any more health (game over?)
     */

    public int flee(Player player, Ship ship) {
        double rate = player.getPilot();
        if (rand.nextInt(20) < rate) {
            System.out.println("You escaped!");
            return 1;
        } else {
            player.setCredits(0);
            ship.setHealth(ship.getHealth() - 5);
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");
                return 3;
            } else {
                System.out.println("Your ship was damaged!");
                System.out.println("Your health is now " + ship.getHealth());
                System.out.println("Your ship now has " + ship.getFuel() + " fuel.");
                return 2;
            }
        }
    }

    /*
    1 = player successfully fought off the bandits and got some credits
    2 = the player failed to flee and lost all credit and ship's health - 50
    3 = player's ship does not have any more health (game over?)
     */

    public int fight(Player player, Ship ship) {
        double rate = player.getFighter();
        if (rand.nextInt(30) < rate) {
            player.setCredits(player.getCredits() + rand.nextInt(100) + 100);
            System.out.println("You fought off the bandits "
                    + "and were able to steal some credits and fuel along the way!");
            ship.setFuel(ship.getFuel() + 3);
            return 1;
        } else {
            player.setCredits(0);
            ship.setHealth(ship.getHealth() - 5);
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");
                return 3;
            } else {
                System.out.println("Your ship was damaged!");
                System.out.println("Your health is now " + ship.getHealth());
                System.out.println("Your ship now has " + ship.getFuel() + " fuel.");
                return 2;
            }
        }
    }

    public int getPayment() {
        return payment;
    }

    private void setPayment(int payment) {
        this.payment = payment;
    }


    /* test
        public static void main(String[] args) {
        Player player = new Player("jason", 1);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Ship ship = new Ship("ship", 10, map, 100, 100);
        Bandit bandit = new Bandit();
        player.setSkillPoints(20);
        System.out.println(bandit.fight(player, ship));
        System.out.println(player.getCredits());
    }
     */
}

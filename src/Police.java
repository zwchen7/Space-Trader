import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *  Class representing basic functionality of the Police
 *
 *  @author Zhi Wei Chen
 *  @version 1.0
 */


public class Police {
    private Random rand = new Random();
    private String item1;
    private String item2;

    public Police(Ship ship) {
        List keysAsArray = new ArrayList(ship.getInventory().keySet());
        if (ship.getInventory().size() > 0) {
            item1 = (String) keysAsArray.get((int) (Math.random() * (ship.getInventory().size())));
            if (ship.getInventory().size() > 3) {
                item2 = (String) keysAsArray.get((int) (Math.random()
                        * (ship.getInventory().size())));
                if (item2.equals(item1)) {
                    item2 = null;
                }
            }
        }

    }

    @Override
    public String toString() {
        if (item2 == null) {
            return "You have encountered a group of space police, they demand you to give them "
                    + item1
                    + ", as they believe it is stolen";
        } else {
            return "You have encountered a group of space police, they demand you to give them "
                    + item1 + " and "
                    + item2 + ", as they believe it is stolen";
        }
    }

    public void forfeit(Ship ship) {
        ship.getInventory().remove(item1);
        System.out.println("You forfeited the requested item");
        if (!(item2 == null)) {
            ship.getInventory().remove(item2);
            System.out.println("You forfeited the requested item");
        }
    }

    /*
    1 = player successfully fled
    2 = the player failed to flee and lost their stolen items, paid the fine, and ship's health - 50
    3 = player's ship does not have any more health (game over?)
     */

    public int flee(Player player, Ship ship) {
        double rate = player.getPilot();
        if (rand.nextInt(20) < rate) {
            System.out.println("You escaped!");
            System.out.println("Your ship now has " + ship.getFuel() + " fuel.");
            return 1;
        } else {
            forfeit(ship);
            int credits = rand.nextInt(200);
            if (player.getCredits() < credits) {
                player.setCredits(0);
            } else {
                player.setCredits(player.getCredits() - credits);
            }
            ship.setHealth(ship.getHealth() - 50);
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");
                return 3;
            } else {
                System.out.println("You couldn't escape! "
                        + "Your ship was damaged and you lost your items and a fine!");
                System.out.println("Your health is now " + ship.getHealth());
                System.out.println("Your ship now has " + ship.getFuel() + " fuel.");
                return 2;
            }
        }
    }

    /*
    1 = player successfully fought off the police and got to keep his items
    2 = the player failed to flee and lost their stolen items, paid the fine, and ship's health - 50
    3 = player's ship does not have any more health (game over?)
     */

    public int fight(Player player, Ship ship) {
        double rate = player.getFighter();
        if (rand.nextInt(30) < rate) {
            System.out.println("You were able to fight off the police! You also stole some fuel!");
            ship.setFuel(ship.getFuel() + 3);
            return 1;
        } else {
            player.setCredits(0);
            ship.setHealth(ship.getHealth() - 5);
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");
                return 3;
            } else {
                System.out.println("You lost the fight! Your "
                        + "ship was damaged and you lost your items and a fine!");
                System.out.println("Your health is now " + ship.getHealth());
                return 2;
            }
        }
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    /* test
    public static void main(String[] args) {
        Player player = new Player("jason", 1);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Ship ship = new Ship("ship", 10, map, 100, 100);
        ship.getInventory().put("Water", 100);
        ship.getInventory().put("Food", 100);
        ship.getInventory().put("Coke", 100);
        ship.getInventory().put("Banana", 100);
        Police police = new Police(ship);
        System.out.println(police);
    }
     */
}

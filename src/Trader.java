//import java.util.HashMap;
import java.util.Random;

/**
 *  Class representing Trader NPC
 *
 *  @author Anna Ho
 *  @version 1.0
 */

public class Trader extends Market {
    private Random rand = new Random();
    // Create rand int to determine which good trader has
    private int randIntItem = rand.nextInt(items.size());
    private int randIntQuantity;
    private int randIntHealth;
    private int startPoints;
    // Rand item
    private Item traderGood = items.get(randIntItem);
    private int traderGoodQuantity = traderGood.getQuantity();


    public Trader(Player player, Region region) {
        super(player, region);
    }

    @Override
    public String toString() {
        return "You have encountered a trader, they are selling an item!";
    }

    /**
     *
     * @param quantity to buy
     * @param player the player
     * @param ship the ship
     * @param region region in universe
     * @return transaction
     *     0 = no problem
     *     1 = goods not available in market
     *     2 = not enough goods in market
     *     3 = cargo will be too full after purchase
     *     4 = not enough credits
     */
    public int buyGoods(int quantity, Player player, Ship ship, Region region) {
        if (quantity > traderGoodQuantity) { // If player wants to buy more than available
            System.out.println("You are trying to buy more than available!");
            return 2;
        } else if ((ship.size() + quantity) > ship.getCargo()) { // If ship cannot hold items
            System.out.println("Your ship does not have enough space!");
            return 3;
        } else if (player.getCredits() < traderGood.getBuyPrice() * quantity) {
            System.out.println("You do not have enough credits!");
            // Player does not have enough creds
            return 4;
        } else { // Buy
            player.setCredits(player.getCredits() - traderGood.getBuyPrice() * quantity);
            if (ship.getInventory().containsKey(traderGood.getName())) {
                quantity = quantity + ship.getInventory().get(traderGood.getName());
            }
            ship.getInventory().put(traderGood.getName(), quantity);
            traderGood.setQuantity(traderGood.getQuantity() - quantity);
            System.out.println("Thank you for your purchase!");
            return 0;
        }
    }

    /**
     * Attempts to rob trader
     * @param player the player
     * @param ship player's ship
     * @return won or not
     *      0 = better luck next time...
     *     -1 = you won, but not enough space to hold items won...
     *     anything else = quantity of item won
     */
    public int robTrader(Player player, Ship ship) {
        startPoints = (int) (player.getStartPoints() * 1.5);
        /*
        Starting pts: 6, 8, or 10
        Starting pts * 1.5 = 9, 12, or 15
        Subtract fighter pts from start points to get range of rands.
        Range is smaller if fighter pts is larger, thus increasing chances.
         */
        int randIntRob = rand.nextInt((startPoints) - player.getFighter());
        // Gets rand amount of goods available from 1 - 1/4 of avail quantity
        randIntQuantity = rand.nextInt((Math.abs(traderGoodQuantity / 4)) + 1);
        if (randIntRob < 4) { // Wins & robs
            // If ship has enough space, give player items
            if (!((ship.size() + randIntQuantity) > ship.getCargo())) {
                // If ship already has item, update quantity
                if (ship.getInventory().containsKey(traderGood.getName())) {
                    randIntQuantity = randIntQuantity
                            + ship.getInventory().get(traderGood.getName());
                }
                ship.getInventory().put(traderGood.getName(), randIntQuantity);
                traderGood.setQuantity(traderGood.getQuantity() - randIntQuantity);
                System.out.println("You managed to steal "
                        + randIntQuantity + " and some fuel, and escape!");
                ship.setFuel(ship.getFuel() + 3);
                return randIntQuantity;
            } else {
                System.out.println("You won and stole some fuel, "
                        + "but didn't have enough space left for all your items...");
                ship.setFuel(ship.getFuel() + 3);

                return -1;
            }
        } else { // Fails and health decreases
            //rand # from 1 - half of ship's health to decrease
            //randIntHealth = rand.nextInt((int) (ship.getHealth() * 0.5)) + 1;
            ship.setHealth(ship.getHealth() - 3);
            System.out.println("You failed to rob the trader! Your ship was damaged!");
            System.out.println("Your health is now " + ship.getHealth());
            if (ship.getHealth() <= 0) {
                System.out.println("Your ship was destroyed! Game Over!");

            }
            return 0;
        }
    }


    /**
     * Negotiates with the trader
     * @param player the player
     * @param ship player's ship
     * @return successful or not
     *     0 = success!
     *     1 = better luck next time...
     */
    public int negotiateTrader(Player player, Ship ship) {
        startPoints = (int) (player.getStartPoints() * 1.5);
        int randIntNegotiate = rand.nextInt((startPoints) - player.getMerchant());
        if (randIntNegotiate < 5) { // successful, lower price
            for (int i = 0; i < player.getMerchant() + 1; i++) {
                traderGood.setBuyPrice((int) (traderGood.getBuyPrice() * 0.7));
            }
            System.out.println("You negotiated successfully!");
            return 0;
        } else { // upset trader, increase price by 2 - 5 times
            traderGood.setBuyPrice(traderGood.getBuyPrice() * (rand.nextInt(5 - 2 + 1) + 2));
            System.out.println("The trader doesn't like your tone...");
            return 1;
        }
    }

    /**
     * Gets good trader is selling
     * @return item available for sale
     */
    public Item getTraderGood() {
        return traderGood;
    }

    /**
     * Gets number of goods available for certain item
     * @return number in stock for the good
     */
    public int getTraderGoodQuantity() {
        return traderGoodQuantity;
    }

    /**
     * Gets number of goods player wins
     * @return number of goods won
     */
    public int getRandIntQuantity() {
        return randIntQuantity;
    }

    /**
     * Gets number of health lost
     * @return health lost
     */
    public int getRandIntHealth() {
        return randIntHealth;
    }

    //    //Test
    //    public static void main(String[] args) {
    //        Player player = new Player("Anna", 1);
    //        player.incrementFighter();
    //        player.incrementFighter();
    //        player.incrementFighter();
    //        player.incrementFighter();
    //        Region region = new Region("region1");
    //        HashMap<String, Integer> map = new HashMap<String, Integer>();
    //        Ship ship = new Ship("ship", 10, map, 100, 100);
    //        Trader trader = new Trader(player, region);
    //
    //        System.out.println("Ship B4:" + ship.getInventory());
    //        System.out.println("Buy:" + trader.buyGoods(1, player, ship, region));
    //        System.out.println("Ship After:" + ship.getInventory());
    //
    //        System.out.println("Rob: " + trader.robTrader(player, ship));
    //        System.out.println("Health After: " + ship.getHealth());
    //        System.out.println("Ship Inventory After: " + ship.getInventory());
    //
    //        player.incrementMerchant();
    //        player.incrementMerchant();
    //        System.out.println("Item Price B4:" + trader.getTraderGood().getBuyPrice());
    //        System.out.println("Negotiate: " + trader.negotiateTrader(player, ship));
    //        System.out.println("Item Price After:" + trader.getTraderGood().getBuyPrice());
    //    }

}

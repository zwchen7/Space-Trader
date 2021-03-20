import java.util.Random;
import java.util.ArrayList;

/**
 *  Class representing basic functionality of the market
 *
 *  @author Zhi Wei Chen, Anna Ho
 *  @version 1.0
 */



public class Market {
    private Random rand = new Random();
    protected ArrayList<Item> items = new ArrayList();

    public Market(Player player, Region region) {
        //first random variable that decreases with merchant skill
        int discount = 10 * region.getTechLevel() - player.getMerchant();
        if (discount <= 0) {
            discount = 1;
        }
        //second random variable
        int randVar = 10 * (region.getTechLevel() + 1);
        int price = 10 * (region.getTechLevel() + 1) + rand.nextInt(discount)
                + rand.nextInt(randVar);
        //goods added to list based on tech level of planet
        Item water = new Item("Water", (int) (price * 0.7), price,
                rand.nextInt((region.getTechLevel() + 1) * 10));
        items.add(water);
        if (region.getTechLevel() > 0) {
            price = 20 * (region.getTechLevel()) + rand.nextInt(discount) + rand.nextInt(randVar);
            Item fur = new Item("Fur", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(fur);
        }
        if (region.getTechLevel() > 1) {
            price = 40 * (region.getTechLevel() - 1) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item food = new Item("Food", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(food);
        }
        if (region.getTechLevel() > 2) {
            price = 80 * (region.getTechLevel() - 2) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item ore = new Item("Ore", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(ore);
        }
        if (region.getTechLevel() > 3) {
            price = 160 * (region.getTechLevel() - 3) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item game = new Item("Game", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(game);
        }
        if (region.getTechLevel() > 4) {
            price = 320 * (region.getTechLevel() - 4) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item firearm = new Item("Fire", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(firearm);
        }
        if (region.getTechLevel() > 5) {
            price = 640 * (region.getTechLevel() - 5) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item medicine = new Item("Medicine", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(medicine);
        }
        if (region.getTechLevel() > 6) {
            price = 1280 * (region.getTechLevel() - 6) + rand.nextInt(discount)
                    + rand.nextInt(randVar);
            Item machine = new Item("Machine", (int) (price * 0.7), price,
                    rand.nextInt((region.getTechLevel() + 1) * 10));
            items.add(machine);
            // special equip (only have a chance of appearing on planets with tech level 7)
            int chance = rand.nextInt(4);
            if (chance == 0) {
                int chanceE = rand.nextInt(4);
                if (chanceE == 0) {
                    Item weapon = new Item("Merchant+1", 0, 700, 1);
                    items.add(weapon);
                } else if (chanceE == 1) {
                    Item scroll = new Item("Fighter+1", 0, 700, 1);
                    items.add(scroll);
                } else if (chanceE == 2) {
                    Item armor = new Item("Engineer+1", 0, 700, 1);
                    items.add(armor);
                } else {
                    Item boots = new Item("Pilot+1", 0, 700, 1);
                    items.add(boots);
                }
            }
        }
    }


    /*
    goods value
    0 = water
    1 = fur
    2 = food
    3 = ore
    4 = game
    5 = firearm
    6 = medicine
    7 = machine
    8 = special equip
     */

    /*
    return int
    -1 = special item
    0 = no problem
    1 = goods not available in market
    2 = not enough goods in market
    3 = cargo will be too full after purchase
    4 = not enough credits
     */

    public int buyGoods(int goods, int quantity, Player player, Ship ship, Region region) {
        if (goods > region.getTechLevel() && goods <= 7) {
            return 1;
        } else if (quantity > items.get(goods).getQuantity()) {
            return 2;
        } else if ((ship.size() + quantity) > ship.getCargo()) {
            return 3;
        } else if (player.getCredits() < items.get(goods).getBuyPrice() * quantity) {
            return 4;
        } else {
            player.setCredits(player.getCredits() - items.get(goods).getBuyPrice() * quantity);
            if (ship.getInventory().containsKey(items.get(goods).getName())) {
                quantity = quantity + ship.getInventory().get(items.get(goods).getName());
            }
            ship.getInventory().put(items.get(goods).getName(), quantity);
            items.get(goods).setQuantity(items.get(goods).getQuantity() - quantity);
            // if special item
            if (goods == 8) {
                return -1;
            }
            return 0;
        }
    }

    /*
    return int
    -1 = can't sell
    0 = no problem
    1 = goods not available in market
    2 = not enough goods in cargo
     */

    public int sellGoods(int goods, int quantity, Player player, Ship ship, Region region) {
        if (goods > region.getTechLevel() && goods <= 7) {
            return 1;
        } else if (ship.size() == 0
                || !ship.getInventory().containsKey(items.get(goods).getName())
                || ship.getInventory().get(items.get(goods).getName()) < quantity) {
            return 2;
        } else if (goods == 8) {
            // can't sell a special item
            return -1;
        } else {
            player.setCredits(player.getCredits() + items.get(goods).getSellPrice() * quantity);
            quantity = ship.getInventory().get(items.get(goods).getName()) - quantity;
            ship.getInventory().put(items.get(goods).getName(), quantity);
            items.get(goods).setQuantity(
                    items.get(goods).getQuantity() + quantity);
            return 0;
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

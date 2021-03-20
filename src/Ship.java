import java.util.HashMap;

/**
 *  Class representing basic functionality of the ship
 *
 *  @author Zhi Wei Chen
 *  @version 1.0
 */

public class Ship {
    private String name;
    private int cargo;
    private int fuel;
    private int health;
    private HashMap<String, Integer> inventory = new HashMap<String, Integer>();

    /**
     * Constructs ship
     * @param name represents the name of ship
     * @param cargo represents the max capacity of cargo
     * @param fuel represents the max capacity of fuel
     * @param health represents the health of the ship
     * @param inventory represents the item inventory of the ship
     */
    public Ship(String name, int cargo, HashMap inventory, int fuel, int health) {
        this.name = name;
        this.cargo = cargo;
        this.fuel = fuel;
        this.health = health;
        this.inventory = inventory;
    }

    /**
     * Calculates the size of the cargo
     * @return size of the cargo
     */
    public int size() {
        int size = 0;
        for (int num : inventory.values()) {
            size += num;
        }
        return size;
    }

    /**
     * Returns the name of the ship
     * @return name of ship
     */

    public String getName() {
        return this.name;
    }

    /**
     * Returns the cargo capacity of ship
     * @return cargo capacity of ship
     */

    public int getCargo() {
        return this.cargo;
    }

    /**
     * Returns the item inventory of the ship
     * @return item inventory of the ship
     */

    public HashMap<String, Integer> getInventory() {
        return this.inventory;
    }

    /**
     * Returns the fuel capacity of ship
     * @return fuel capacity of ship
     */

    public int getFuel() {
        return this.fuel;
    }
    /**
     * Returns the health of ship
     * @return health of ship
     */
    public int getHealth() {
        return this.health;
    }


    public int getRepairPrice(Player player) {
        int engineerSkill = player.getEngineer();
        int repairPrice = 200;
        if (engineerSkill == 0) {
            return repairPrice;
        }
        return repairPrice / engineerSkill;
    }

    public int getRefuelPrice(Player player) {
        return 100;
    }

    /**
     * Sets the name of ship
     * @param name represents the name of ship
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the max capacity of cargo for the ship
     * @param cargo represents the max capacity of cargo
     */

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    /**
     * Sets the item inventory of the ship
     * @param inventory represents the item inventory of the ship
     */

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * Sets the max capacity of fuel for the ship
     * @param fuel represents the max capacity of fuel
     */

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * Sets the health for the ship
     * @param health represents the health of the ship
     */

    public void setHealth(int health) {
        this.health = health;
    }
}
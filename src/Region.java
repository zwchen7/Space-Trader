import java.util.ArrayList;
import java.util.Random;

/**
 *  Class representing basic functionality of the region
 *
 *  @author Zhi Wei Chen, Peyton Howell
 *  @version 1.0
 */

public class Region {
    private String name;
    private int regionNumber;
    private int x;
    private int y;
    private int techLevel;
    private boolean visited;
    private Random rand = new Random();
    private static ArrayList<Integer> xCoordinates = new ArrayList(10);
    private static ArrayList<Integer> yCoordinates = new ArrayList(10);
    private String adjective;
    private String adjective2;
    private String[] adjectives = {"cool", "great", "hot", "cold",
                                   "steaming", "evil", "amazing",
                                   "beautiful", "terrifying", "jolly",
                                   "skinny", "silly", "worried", "lazy",
                                   "witty", "plain", "brave", "grumpy",
                                   "smart", "proud", "panicky", "uptight",
                                   "scary", "repulsive", "itchy", "huge",
                                   "obnoxious", "tiny", "small", "zealous"};
    // 30 words

    /**
     * Creates region
     * @param name represents the name of the region
     */

    public Region(String name) {
        this.name = name;
        this.x = rand.nextInt(750);
        this.checkIf5AwayX(this.x);
        xCoordinates.add(this.x);
        this.y = rand.nextInt(350);
        this.checkIf5AwayY(this.y);
        yCoordinates.add(this.y);
        this.techLevel = rand.nextInt(8);
        visited = false;
        generateAdjective();
        generateAdjective2();
        char temp = name.charAt(6);
        String temp2 = Character.toString(temp);
        regionNumber = Integer.parseInt(temp2);
    }

    public int getRegionNumber() {
        return regionNumber;
    }

    private void generateAdjective() {
        int pick = rand.nextInt(adjectives.length);
        this.adjective = adjectives[pick];
    }

    private void generateAdjective2() {
        int pick = rand.nextInt(adjectives.length);
        this.adjective2 = adjectives[pick];
    }

    private void checkIf5AwayX(int coordinate) {
        boolean done = false;
        while (!done) {
            for (Integer xOther: xCoordinates) {
                if (Math.abs(xOther - this.x) < 25) {
                    this.x = rand.nextInt(750);
                }
            }
            done = true;
        }
    }

    private void checkIf5AwayY(int coordinate) {
        boolean done = false;
        while (!done) {
            done = true;
            for (Integer yOther: yCoordinates) {
                if (Math.abs(yOther - this.y) < 25) {
                    this.y = rand.nextInt(350);
                    done = false;
                }
            }
        }
    }

    /**
     * Returns the x-coordinate array list of the regions
     * @return Array List containing all the X coordinates
     */

    public static ArrayList<Integer> getXCoordinates() {
        return xCoordinates;
    }

    /**
     * Returns the y-coordinate of all the regions
     * @return Array List containing all the Y coordinates
     */

    public ArrayList<Integer> getYCoordinates() {
        return yCoordinates;
    }

    /**
     * Returns the x-coordinate of the region
     * @return this.x representing the x-coordinate of the region
     */

    public int getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the region
     * @return this.y representing the y-coordinate of the region
     */

    public int getY() {
        return this.y;
    }

    /**
     * Returns the tech level of the region
     * @return this.techLevel representing the tech level of the region
     */

    public int getTechLevel() {
        return this.techLevel;
    }

    /**
     * Gets the name of the region
     * @return this.name which is the name of the region
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns whether or not a region has being visited by the player
     * @return this.visited representing whether or not a region has being visited by the player
     */

    public boolean getVisited() {
        return this.visited;
    }

    /**
     * Sets whether or not the region has being visited by the player
     * @param visited whether or not a region has being visited by the player
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * toString method that helps display the information of the current region
     * @return the information of the current region
     */
    @Override
    public String toString() {
        if (visited) {
            return "Region " + name + " is located at ("
                    + x + ", " + y + ") and has a tech level of "
                    + techLevel + "!\n"
                    + "This region has a population that is "
                    + adjective + " and " + adjective2 + "!";
        } else {
            return "Unknown region, has not being visited yet";
        }
    }
}

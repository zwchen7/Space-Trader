/**
 *  Class representing basic functionality of the main player
 *
 *  @author Peyton Howell, Anna Ho
 *  @version 1.0
 */
public class
    Player {
    private String name;
    private int difficulty;
    private int pilot;
    private int fighter;
    private int merchant;
    private int engineer;
    private int skillPoints;
    private int credits;
    private int startPoints;
    private String region;

    /**
     * Creates player based off Player inputs at initial configuration screen
     * Sets the skillPoints and credits based on the difficulty
     * @param name represents the inputted name of the character
     * @param difficulty represents the chosen difficulty
     *                   ranging from 0 to 2. Make drop down menu/ option in javaFX
     *                   to avoid invalid input
     * @throws IllegalArgumentException if the name is empty or null
     */
    public Player(String name, int difficulty) {
        if (name.equals("") || name == null) {
            throw new IllegalArgumentException("Cannot input a null name");
        }

        this.name = name;
        this.setDifficulty(difficulty);
        if (difficulty == 0) {
            startPoints = 10;
            skillPoints = 10;
            credits = 1000;
        } else if (difficulty == 1) {
            startPoints = 8;
            skillPoints = 8;
            credits = 800;
        } else {
            startPoints = 6;
            skillPoints = 6;
            credits = 600;
        }
        region = "default";
    }

    /**
     * Returns the name of the Player
     *
     * @return this.name representing the string name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for the player name
     *
     * @param newName the new name being set for player
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Increments the pilot skill and decreases skillPoints by one each
     *
     * @throws IllegalArgumentException if the player doesn't have enough skillPoints
     */
    public void incrementPilot() {
        if (skillPoints == 0) {
            throw new IllegalArgumentException(
                    "Player is out of skillPoints. "
                           + "Cannot increment skills any further");
        }
        skillPoints--;
        pilot++;
    }

    /**
     * Decrements the pilot skill and increase skillPoints by one each
     */
    public void decrementPilot() {
        skillPoints++;
        pilot--;
    }

    /**
     * Returns the number of skillPoints towards the pilot sklll
     * @return this.pilot representing the  number of skill points the player has towards pilot
     */
    public int getPilot() {
        return this.pilot;
    }


    /**
     * Increments the fighter skill and decreases skillPoints by one each
     *
     * @throws IllegalArgumentException if the player doesn't have enough skillPoints
     */
    public void incrementFighter() {
        if (skillPoints == 0) {
            throw new IllegalArgumentException(
                    "Player is out of skillPoints. "
                            + "Cannot increment skills any further");
        }
        skillPoints--;
        fighter++;
    }

    /**
     * Decrements the fighter skill and increase skillPoints by one each
     */
    public void decrementFighter() {
        skillPoints++;
        fighter--;
    }


    /**
     * Returns the number of skillPoints towards the fighter sklll
     * @return this.fighter representing the  number of skill points the player has towards fighter
     */
    public int getFighter() {
        return this.fighter;
    }


    /**
     * Increments the engineer skill and decreases skillPoints by one each
     *
     * @throws IllegalArgumentException if the player doesn't have enough skillPoints
     */
    public void incrementEngineer() {
        if (skillPoints == 0) {
            throw new IllegalArgumentException(
                    "Player is out of skillPoints. "
                            + "Cannot increment skills any further");
        }
        skillPoints--;
        engineer++;
    }

    /**
     * Decrements the engineer skill and increase skillPoints by one each
     */
    public void decrementEngineer() {
        skillPoints++;
        engineer--;
    }


    /**
     * Returns the number of skillPoints towards the engineer sklll
     * @return this.engineer representing the  number of skill
     * points the player has towards engineer
     */
    public int getEngineer() {
        return this.engineer;
    }
    /**
     * Increments the merchant skill and decreases skillPoints by one each
     *
     * @throws IllegalArgumentException if the player doesn't have enough skillPoints
     */
    public void incrementMerchant() {
        if (skillPoints == 0) {
            throw new IllegalArgumentException(
                    "Player is out of skillPoints. "
                           + "Cannot increment skills any further");
        }
        skillPoints--;
        merchant++;
    }

    /**
     * Decrements the merchant skill and increase skillPoints by one each
     */
    public void decrementMerchant() {
        skillPoints++;
        merchant--;
    }

    /**
     * Returns the number of skillPoints towards the merchant sklll
     * @return this.merchant representing the  number of
     * skill points the player has towards merchant
     */
    public int getMerchant() {
        return this.merchant;
    }

    /**
     * Gets starting amount of skillPoints.
     * @return the starting amount of points as an int
     */
    public int getStartPoints() {
        return this.startPoints;
    }

    /**
     * Gets the current amount of skillPoints that the player has
     * @return this.skillPoints to show how many skillPoints the player currently has
     */
    public int getSkillPoints() {
        return this.skillPoints;
    }


    /**
     * Changes the number of skillPoints the player has to the input parameter
     * @param skillPoints is the new number of skillPoints the player will have
     */
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }


    /**
     * Returns the difficulty of the game
     * @return this.difficulty -- the difficulty of the game for the player
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Sets the difficulty of the game
     *
     * @param difficulty an int for the new difficulty
     * @throws IllegalArgumentException if the difficulty is greater than 2 or less than 0
     */
    public void setDifficulty(int difficulty) {
        if (difficulty > 2) {
            throw new IllegalArgumentException(
                    "difficulty is greater than 2 or less than 0");
        }
        this.difficulty = difficulty;
    }

    /**
     * Gets the number of credits the player has
     * @return this.credits which is the total amount of credits the player has
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Sets the player's credits
     * @param credits is the amount of credits that the player will be set to
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getRegion() {
        return this.region;
    }

    /**
     * Temporarily here for the purposes of basic testing
     * @param args should probably just be blank

    public static void main(String[] args) {
        Player me = new Player("", 2);
        System.out.println(me.getDifficulty());
        System.out.println(me.getSkillPoints());
        me.incrementEngineer();
        me.incrementFighter();
        System.out.println(me.getSkillPoints());
        System.out.println(me.getCredits());
    }
    */
}


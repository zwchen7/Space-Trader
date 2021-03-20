import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
//import java.io.File;
import java.net.URL;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *  JavaFX Class to start up the Peon Travelers game.
 *
 *  @author Nestor Galarza, Anna Ho, Peyton Howell, Noah Bond
 *  @version 1.0
 */
public class Start extends Application {
    private Stage stage;
    private Player aPlayer = new Player("nullName4566123138487865513211455@fhjkaldf mdz.zv", -1);
    private HashMap<String, Integer> inventory = new HashMap<>();
    private Ship ship;
    private Market market;
    private ArrayList<Item> listItems;
    private Text infoName;
    private Text difficultySetting;
    private Text pilotPoint;
    private Text fighterPoint;
    private Text merchantPoint;
    private Text engineerPoint;
    private Text startingCredit;
    private Stage primaryStage;
    private Scene startingScene;
    private Scene characterCreationScene;
    private Scene characterInformationScene;
    private Scene universeScreen;
    private Scene customizationScreen;
    private Scene regionScene;
    private Scene endCredits;
    private Scene winScene;
    private Button startButton;
    private Button confirm;
    private Button beginGame;
    private Button confirmUpgrades = new Button("CONFIRM UPGRADES");
    private Button goToUniverse = new Button("Go to Universe");
    private Button goToCustomization = new Button("Player Upgrades");
    private Button restartButton = new Button("Restart?");
    private Button continueCredits = new Button("Continue?");
    private TextField name;
    private ToggleGroup group;
    private ToggleButton easy;
    private ToggleButton med;
    private ToggleButton hard;
    private Spinner<Integer> spinner1;
    private Spinner<Integer> spinner2;
    private Spinner<Integer> spinner3;
    private Spinner<Integer> spinner4;
    private ArrayList<ImageView> regionList;
    private  Region[] regions = new Region[10];
    private ImageView region1i;
    private ImageView region2i;
    private ImageView region3i;
    private ImageView region4i;
    private ImageView region5i;
    private ImageView region6i;
    private ImageView region7i;
    private ImageView region8i;
    private ImageView region9i;
    private ImageView region10i;
    private ImageView regionB;
    private ImageView regionA;
    private String startingRegion;
    private Label regionInformation = new Label("Region currently not selected.");
    private int quantity = 1;
    private Text playerCredits;
    private Random rand = new Random();
    private int gameWinningRegion = rand.nextInt(10);
    private boolean win = false;


    /**
     * Creates the game over screen
     * @return the screen of game over
     */
    private Scene gameOverScreen() {
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        VBox leftPane = new VBox(20);
        HBox topPane = new HBox(20);
        BackgroundImage bgI3 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        canvas.setBackground(new Background(bgI3));
        Label gameOverLabel = new Label("GAME OVER! YOUR SHIP WAS DESTROYED!");
        bottomPane.getChildren().addAll(gameOverLabel, continueCredits);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(topPane, canvas, bottomPane);
        Scene gameOverScreen = new Scene(vBox, 820, 470);
        return gameOverScreen;
    }

    /**
     * Creates end credits screen
     * @return the end credits scene
     */
    private Scene endCredits() {
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        VBox leftPane = new VBox(20);
        HBox topPane = new HBox(20);
        BackgroundImage bgI3 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        canvas.setBackground(new Background(bgI3));
        Label gameOverLabel = new Label("Credits:Team 84 - the Peons. \n Thanks for Playing!");
        bottomPane.getChildren().addAll(gameOverLabel, restartButton);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(topPane, canvas, bottomPane);
        Scene endCreditsScene = new Scene(vBox, 820, 470);
        return endCreditsScene;
    }



    /**
     * Creates starting scene
     * @return starting scene to be staged
     */
    private Scene startingScene() {
        // Scene 1 - Starting Screen //

        // Children - Background Image from Reddit by u/k4sma
        // We can replace later / add a credits area
        ship = new Ship("Ship", 3, inventory, 10, 10);
        BackgroundImage bgI = new BackgroundImage(new Image("space.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Children - Text
        Text titleText = new Text(50, 50, "Space Travelers"); // title text
        titleText.setFill(Color.LIGHTGREEN);
        titleText.setFont(Font.font("Futura", FontWeight.BOLD, 40));

        // Children - Buttons
        startButton = new Button("Start"); // the start button object
        startButton.setPrefSize(360, 140);
        startButton.setFont(Font.font("Futura", FontWeight.NORMAL, 36));
        startButton.setOnAction(e -> {
            stage.setScene(characterCreationScene);
            stage.show();
        });
        Button exitButton = new Button("Exit"); // the exit button object
        exitButton.setPrefSize(360, 140);
        exitButton.setFont(Font.font("Futura", FontWeight.NORMAL, 36));
        // Event on exitButton to exit the game
        exitButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
        // Panes
        VBox buttonHolder = new VBox(20); // a VBox to put buttons in
        buttonHolder.setAlignment(Pos.CENTER);

        // Add children to panes or resize panes
        buttonHolder.getChildren().addAll(titleText, startButton, exitButton);
        buttonHolder.setPrefSize(820, 400); // this leaves top with 70 Height
        buttonHolder.setBackground(new Background(bgI));

        // Add panes and create the scene
        Scene startingScene = new Scene(buttonHolder, 820, 470);
        // Event on startButton to switch to CharacterCreation scene

        return startingScene;
    }
    /**
     * creates character selection scene
     * @return the created scene
     */
    private Scene characterCreationScene() {
        aPlayer = new Player("nullName4566123138487865513211455@fhjkaldf mdz.zv", -1);
        BackgroundImage bgI = new BackgroundImage(new Image("space.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        // Panes
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        GridPane center = new GridPane();
        center.setAlignment(Pos.TOP_CENTER);
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(10, 10, 10, 10));

        HBox bottom = new HBox();
        bottom.setAlignment(Pos.BOTTOM_RIGHT);

        // Add panes and create the scene
        Scene characterCreationScene = new Scene(borderPane, 820, 470);

        //Children
        //Welcome, Character Name + Text Box
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFill(Color.BLUEVIOLET);
        sceneTitle.setFont(Font.font("Futura", FontWeight.BOLD, 25));
        center.add(sceneTitle, 0, 0, 2, 2);

        Text charName = new Text(50, 50, "Character Name: ");
        charName.setFill(Color.ORCHID);
        charName.setFont(Font.font("Futura", FontWeight.BOLD, 16));
        center.add(charName, 0, 2, 1, 3);

        name = new TextField();
        name.setFont(Font.font("Futura", 16));
        name.setPromptText("Enter a name.");
        center.add(name, 1, 2, 1, 3);

        name.setTextFormatter(new TextFormatter<>(entry -> {
            if (entry.getText().equals(" ")) {
                entry.setText("");
            }
            return entry;
        }));


        //Game Difficulty, easy (10pts), med (8pts), hard (6pts)
        Text level = new Text("Difficulty: ");
        level.setFill(Color.ORCHID);
        level.setFont(Font.font("Futura", FontWeight.BOLD, 16));

        //Easy, med, hard buttons
        group = new ToggleGroup();
        easy = new ToggleButton("EASY");
        med = new ToggleButton("MEDIUM");
        hard = new ToggleButton("HARD");

        easy.setToggleGroup(group);
        med.setToggleGroup(group);
        hard.setToggleGroup(group);

        easy.setStyle("-fx-text-fill: #77b300");
        med.setStyle("-fx-text-fill: #669900");
        hard.setStyle("-fx-text-fill: #446600");

        easy.setMinSize(90, 25);
        med.setMinSize(90, 25);
        hard.setMinSize(90, 25);

        easy.setFont(Font.font("Futura", FontWeight.BOLD, 15.5));
        med.setFont(Font.font("Futura", FontWeight.BOLD, 15.5));
        hard.setFont(Font.font("Futura", FontWeight.BOLD, 15.5));

        //Adds children (buttons) to center gridpane
        center.add(level, 0, 6);
        center.add(easy, 1, 6);
        center.add(med, 1, 7);
        center.add(hard, 1, 8);

        //Skill Points Allocation to (Pilot, Fighter, Merchant, & Engineer)
        //(only up to set amount)
        Text skills = new Text("Skills:");
        skills.setFill(Color.ORCHID);
        skills.setFont(Font.font("Futura", FontWeight.BOLD, 16));

        Text skillsAllocate = new Text("Allocate your skill points.");
        skillsAllocate.setFill(Color.ORCHID);
        skillsAllocate.setFont(Font.font("Futura", 16));

        Text pilot = new Text("Pilot");
        pilot.setFill(Color.OLIVEDRAB);
        pilot.setFont(Font.font("Futura", FontWeight.BOLD, 15));

        Text fighter = new Text("Fighter");
        fighter.setFill(Color.OLIVEDRAB);
        fighter.setFont(Font.font("Futura", FontWeight.BOLD, 15));

        Text merchant = new Text("Merchant");
        merchant.setFill(Color.OLIVEDRAB);
        merchant.setFont(Font.font("Futura", FontWeight.BOLD, 15));

        Text engineer = new Text("Engineer");
        engineer.setFill(Color.OLIVEDRAB);
        engineer.setFont(Font.font("Futura", FontWeight.BOLD, 15));

        //Adds children to centerpane
        center.add(skills, 0, 10);
        center.add(skillsAllocate, 1, 10);
        center.add(pilot, 0, 11);
        center.add(fighter, 0, 12);
        center.add(merchant, 0, 13);
        center.add(engineer, 0, 14);

        //Spinner to allow point allocation
        spinner1 = (Spinner<Integer>) new Spinner(0, 12, 0);
        spinner1.setMaxSize(90, 25);
        center.add(spinner1, 1, 11);

        spinner2 = (Spinner<Integer>) new Spinner(0, 12, 0);
        spinner2.setMaxSize(90, 25);
        center.add(spinner2, 1, 12);

        spinner3 = (Spinner<Integer>) new Spinner(0, 12, 0);
        spinner3.setMaxSize(90, 25);
        center.add(spinner3, 1, 13);

        spinner4 = (Spinner<Integer>) new Spinner(0, 12, 0);
        spinner4.setMaxSize(90, 25);
        center.add(spinner4, 1, 14);

        confirm = new Button("CONFIRM");
        confirm.setFont(Font.font("Futura", FontWeight.BOLD, 14));
        confirm.setStyle("-fx-text-fill: #005ce6");
        bottom.getChildren().add(confirm);

        borderPane.setBackground(new Background(bgI));
        borderPane.setCenter(center);
        borderPane.setBottom(bottom);

        return characterCreationScene;
    }

    /**
     * Creates character information scene
     * @return the aforementioned scene
     */
    private Scene characterInformationScene() {
        //Background image for character information page
        BackgroundImage infoBackground = new BackgroundImage(new Image("infoBackground.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        //Main Panes
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        VBox infoPage = new VBox(20);
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.BOTTOM_RIGHT);

        //Title of the page
        Text infoTitle = new Text(50, 50, "Character Information");

        //font style change
        infoTitle.setFill(Color.MEDIUMPURPLE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 32));


        //Add children to page and change alignment
        infoPage.setAlignment(Pos.CENTER);
        //Added the background
        infoPage.setBackground(new Background(infoBackground));
        if (aPlayer.getDifficulty() == 0) {
            difficultySetting = new Text(50, 50, "Current difficulty setting is : Easy");
        } else if (aPlayer.getDifficulty() == 1) {
            difficultySetting = new Text(50, 50, "Current difficulty setting is : Medium");
        } else {
            difficultySetting = new Text(50, 50, "Current difficulty setting is : Hard");
        }
        pilotPoint = new Text(50, 50,
                "Pilot skill points: " + aPlayer.getPilot());
        fighterPoint = new Text(50, 50,
                "Fighter skill points: " + aPlayer.getFighter());
        merchantPoint = new Text(50, 50,
                "Merchant skill points: " + aPlayer.getMerchant());
        engineerPoint = new  Text(50, 50,
                "Engineer skill points: " + aPlayer.getEngineer());
        startingCredit = new  Text(50, 50,
                "Starting credits: " + aPlayer.getCredits());
        infoName = new Text(50, 50, "Name: " + aPlayer.getName());
        infoName.setFont(Font.font("Futura", FontWeight.BOLD, 24));
        infoName.setFill(Color.WHITE);
        difficultySetting.setFont(Font.font("Futura", 20));
        difficultySetting.setFill(Color.WHITE);
        pilotPoint.setFont(Font.font("Futura", 20));
        pilotPoint.setFill(Color.WHITE);
        fighterPoint.setFont(Font.font("Futura", 20));
        fighterPoint.setFill(Color.WHITE);
        merchantPoint.setFont(Font.font("Futura", 20));
        merchantPoint.setFill(Color.WHITE);
        engineerPoint.setFont(Font.font("Futura", 20));
        engineerPoint.setFill(Color.WHITE);
        startingCredit.setFont(Font.font("Futura", 20));
        startingCredit.setFill(Color.WHITE);

        infoPage.getChildren().addAll(infoTitle,
                infoName, difficultySetting, pilotPoint, fighterPoint,
                merchantPoint, engineerPoint, startingCredit);

        // edit the "UNIVERSE" button
        beginGame.setFont(Font.font("Futura", FontWeight.BOLD, 16));
        beginGame.setStyle("-fx-text-fill: #005ce6");
        bottom.getChildren().add(beginGame);

        // Add panes and create the scene

        borderPane.setBackground(new Background(infoBackground));
        borderPane.setCenter(infoPage);
        borderPane.setBottom(bottom);
        characterInformationScene = new Scene(borderPane, 820, 470);
        return characterInformationScene;
    }

    /**
     * Creates the universe screen
     * @return the scene for the universe
     */
    private Scene universeScreen() {

        BackgroundImage bgI = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        // Instantiate Panes
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        VBox leftPane = new VBox(20);
        HBox topPane = new HBox(20);

        // Instantiate Objects
        //Title of the page
        Text infoTitle = new Text(50, 75, "Choose a Region");

        //font style change
        infoTitle.setFill(Color.LIGHTGREEN);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 20));

        Label labelCoords = new Label("(00.0, 00.0)");
        labelCoords.setFont(Font.font("Futura", FontWeight.NORMAL, 12));
        Text text2 = new Text(50, 75, "Number of regions: 10");
        text2.setFont(Font.font("Futura", FontWeight.NORMAL, 12));
        goToCustomization.setFont(Font.font("Futura", FontWeight.NORMAL, 12));
        Button musicButton = new Button("Play Music");
        musicButton.setPrefSize(90, 20);
        musicButton.setFont(Font.font("Futura", FontWeight.NORMAL, 12));
        Label tbd = new Label("TBD");
        tbd.setFont(Font.font("Futura", FontWeight.NORMAL, 12));
        regionInformation.setFont(Font.font("Futura", FontWeight.NORMAL, 12));

        // Create an array of all the regions
        regionList = new ArrayList(10);
        Image region1 = new Image("region0.png", 50, 50, false, false);
        region1i = new ImageView(region1);
        regionList.add(region1i);
        Image region2 = new Image("region1.png", 50, 50, false, false);
        region2i = new ImageView(region2);
        regionList.add(region2i);
        Image region3 = new Image("region2.png", 50, 50, false, false);
        region3i = new ImageView(region3);
        regionList.add(region3i);
        Image region4 = new Image("region3.png", 50, 50, false, false);
        region4i = new ImageView(region4);
        regionList.add(region4i);
        Image region5 = new Image("region4.png", 50, 50, false, false);
        region5i = new ImageView(region5);
        regionList.add(region5i);
        Image region6 = new Image("region5.png", 50, 50, false, false);
        region6i = new ImageView(region6);
        regionList.add(region6i);
        Image region7 = new Image("region6.png", 50, 50, false, false);
        region7i = new ImageView(region7);
        regionList.add(region7i);
        Image region8 = new Image("region7.png", 50, 50, false, false);
        region8i = new ImageView(region8);
        regionList.add(region8i);
        Image region9 = new Image("region8.png", 50, 50, false, false);
        region9i = new ImageView(region9);
        regionList.add(region9i);
        Image region10 = new Image("region9.png", 50, 50, false, false);
        region10i = new ImageView(region10);
        regionList.add(region10i);


        URL resource = getClass().getResource("spacesong.mp3");
        Media song = new Media(resource.toString());
        MediaPlayer player = new MediaPlayer(song);

        int i = 0;
        for (ImageView region : regionList) {
            regions[i] = new Region("region" + (i));
            canvas.getChildren().add(region);
            region.setLayoutX(regions[i].getX());
            region.setLayoutY(regions[i].getY());
            i++;
        }

        // Add or Customize Panes
        topPane.getChildren().addAll(infoTitle);
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(bgI));
        leftPane.getChildren().addAll(tbd, musicButton);
        bottomPane.getChildren().addAll(labelCoords, goToCustomization,
                musicButton, regionInformation);
        leftPane.setStyle("-fx-background-color: lightgrey");
        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        canvas.setBackground(new Background(bgI));

        canvas.setOnMouseMoved(e -> {
            String s = new String("Coordinates: (" + (e.getX()) + "," + (e.getY()) + ")");
            labelCoords.setText(s);
        });


        musicButton.setOnAction(e -> {
            player.setVolume(1);
            player.play();
        });



        // add to the final pane
        VBox vPane = new VBox();
        vPane.getChildren().add(topPane);
        vPane.getChildren().add(canvas);
        vPane.getChildren().add(bottomPane);
        //borderPane.setLeft(leftPane);


        universeScreen = new Scene(vPane, 820, 470);
        return universeScreen;
    }

    /**
     * Creates the scene of the region the player is currently in
     * @param region is the region that the player is in
     * @return the scene representing the region
     */
    private Scene regionScene(Region region) {
        //*** Create the ship ***
        if (ship.getHealth() <= 0) {
            stage.setScene(gameOverScreen());
            stage.show();
        }
        Label fuelLabel = new Label("Fuel: " + ship.getFuel());
        Label healthLabel = new Label("Health: " + ship.getHealth());
        Button repairButton = new Button("Repair: " + ship.getRepairPrice(aPlayer));
        Button refuelButton = new Button("Refuel: " + ship.getRefuelPrice(aPlayer));

        repairButton.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            if (aPlayer.getCredits() < ship.getRepairPrice(aPlayer)) {
                alert.setContentText("Not enough credits!");
            } else if (ship.getHealth() < 10) {
                alert.setContentText("Success!");
                aPlayer.setCredits(aPlayer.getCredits() - ship.getRepairPrice(aPlayer));
                ship.setHealth(10);
                playerCredits.setText("Credits Left: " + aPlayer.getCredits());
            } else {
                alert.setContentText("Too high of health to purchase!");
            }
            alert.showAndWait();
        });

        refuelButton.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            if (aPlayer.getCredits() < ship.getRefuelPrice(aPlayer)) {
                alert.setContentText("Not enough credits!");
            } else if (ship.getFuel() < 10) {
                alert.setContentText("Success! Will show up on next travel.");
                aPlayer.setCredits(aPlayer.getCredits() - ship.getRefuelPrice(aPlayer));
                ship.setFuel(10);
                playerCredits.setText("Credits Left: " + aPlayer.getCredits());
            } else {
                alert.setContentText("Too high of fuel to purchase!");
            }
            alert.showAndWait();
        });
        fuelLabel.setTextFill(Color.GREEN);
        healthLabel.setTextFill(Color.RED);

        BackgroundImage bgI2 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Image regionImage = new Image(region.getName() + ".png",
                100, 100, false, false);
        ImageView regionI = new ImageView(regionImage);
        region.setVisited(true);
        // Instantiate Panes
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        VBox leftPane = new VBox(20);
        HBox topPane = new HBox(20);
        // Instantiate Objects
        Text infoTitle = new Text(100, 100, region.getName());
        Label info = new Label(region.toString());
        Text marketPlaceTitle = new Text("Marketplace:");
        Text otherRegionsTitle = new Text("Other Regions:");
        Rectangle marketPlaceTitleBox = new Rectangle(165, 29);
        Rectangle otherRegionsTitleBox = new Rectangle(150, 29);
        Rectangle infoBox = new Rectangle(430, 35);
        Rectangle marketPlaceBox = new Rectangle(242, 350);
        Rectangle nearbyRegionsBox = new Rectangle(260, 30);
        //font style change
        infoTitle.setFill(Color.MEDIUMPURPLE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 35));
        info.setTextFill(Color.LIGHTGREEN);
        info.setFont(Font.font("Futura", FontWeight.NORMAL, 13.5));
        marketPlaceTitle.setFont(Font.font("Futura", FontWeight.BOLD, 20));
        marketPlaceTitle.setFill(Color.DARKORANGE);
        otherRegionsTitle.setFont(Font.font("Futura", FontWeight.BOLD, 15));
        otherRegionsTitle.setFill(Color.MEDIUMPURPLE);
        marketPlaceTitleBox.setFill(Color.BLACK);
        otherRegionsTitleBox.setFill(Color.BLACK);
        infoBox.setFill(Color.BLACK);
        marketPlaceBox.setFill(Color.TRANSPARENT);
        marketPlaceBox.setStroke(Color.DARKORANGE);
        nearbyRegionsBox.setFill(Color.BLACK);


        int  regionNum = Integer.parseInt(
                region.getName().substring(region.getName().length() - 1));
        int regionBeforeNum = Math.floorMod((regionNum - 1), 10);
        Image regionBefore = new Image("region" + regionBeforeNum
                + ".png", 50, 50, false, false);
        int regionAfterNum = (regionNum + 1) % 10;
        Image regionAfter = new Image("region" + regionAfterNum
                + ".png", 50, 50, false, false);
        regionB = new ImageView(regionBefore);
        regionA = new ImageView(regionAfter);
        String regionBeforeString = "region" + Math.floorMod((regionNum - 1), 10);
        String regionAfterString = "region" + Math.abs((regionNum + 1) % 10);
        Text regionBeforeText = new Text(regionBeforeString);
        Text regionAfterText = new Text(regionAfterString);
        Text nearbyRegionsText = new Text("<- Nearby Region(s) ->");
        nearbyRegionsText.setFill(Color.MEDIUMPURPLE);
        regionBeforeText.setFill(Color.MEDIUMPURPLE);
        regionAfterText.setFill(Color.MEDIUMPURPLE);
        regionBeforeText.setFont(Font.font("Futura", FontWeight.BOLD, 13));
        regionAfterText.setFont(Font.font("Futura", FontWeight.BOLD, 13));
        nearbyRegionsText.setFont(Font.font("Futura", FontWeight.BOLD, 17));
        // B = before A = after
        nearbyRegionsText.setLayoutX(520);
        nearbyRegionsText.setLayoutY(140);
        regionBeforeText.setLayoutY(93);
        regionBeforeText.setLayoutX(450);
        regionB.setLayoutX(450);
        regionB.setLayoutY(110);
        regionAfterText.setLayoutY(93);
        regionAfterText.setLayoutX(748);
        regionA.setLayoutX(750);
        regionA.setLayoutY(110);
        //I moved the line of code under to lower in this method.
        //canvas.getChildren().addAll(regionB, regionA, regionAfterText, regionBeforeText);
        //Add children to page and change alignment
        topPane.setAlignment(Pos.CENTER);
        //Added the background
        goToUniverse.setFont(Font.font("Futura", FontWeight.NORMAL, 14));
        Button musicButton = new Button("Play Music");
        musicButton.setFont(Font.font("Futura", FontWeight.NORMAL, 14));
        URL resource = getClass().getResource("spacesong.mp3");
        Media song = new Media(resource.toString());
        MediaPlayer player = new MediaPlayer(song);
        // Add or Customize Panes
        topPane.getChildren().addAll(infoTitle);
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(bgI2));
        leftPane.getChildren().addAll(musicButton);
        bottomPane.getChildren().addAll(musicButton, goToUniverse);
        leftPane.setStyle("-fx-background-color: lightgrey");
        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        canvas.setBackground(new Background(bgI2));
        canvas.getChildren().addAll(marketPlaceTitleBox, otherRegionsTitleBox, infoBox,
                marketPlaceBox, nearbyRegionsBox,
                info, regionI, marketPlaceTitle, otherRegionsTitle);
        canvas.getChildren().addAll(regionB, regionA, regionAfterText, regionBeforeText,
                nearbyRegionsText);
        canvas.getChildren().addAll(fuelLabel, healthLabel, repairButton, refuelButton);
        nearbyRegionsBox.setLayoutX(450);
        nearbyRegionsBox.setLayoutY(160);
        otherRegionsTitle.setLayoutX(310);
        otherRegionsTitle.setLayoutY(140);
        otherRegionsTitleBox.setLayoutX(305);
        otherRegionsTitleBox.setLayoutY(120);
        marketPlaceTitleBox.setLayoutX(29);
        marketPlaceTitleBox.setLayoutY(7);
        marketPlaceTitle.setLayoutX(40);
        marketPlaceTitle.setLayoutY(20);
        marketPlaceBox.setLayoutX(26);
        marketPlaceBox.setLayoutY(24);
        infoBox.setLayoutX(370);
        infoBox.setLayoutY(40);
        info.setLayoutX(390);
        info.setLayoutY(35);
        regionI.setLayoutX(275);
        regionI.setLayoutY(10);
        fuelLabel.setLayoutX(550);
        fuelLabel.setLayoutY(200);
        healthLabel.setLayoutX(550);
        healthLabel.setLayoutY(220);
        repairButton.setLayoutX(550);
        refuelButton.setLayoutX(550);
        repairButton.setLayoutY(245);
        refuelButton.setLayoutY(285);
        musicButton.setOnAction(e -> {
            player.setVolume(1);
            player.play();
        });
        regionB.setOnMouseClicked(e -> {
            int regionNum1 = ((Integer.parseInt(
                    aPlayer.getRegion().substring(aPlayer.getRegion().length() - 1)) + 9) % 10);
            Region nextRegion = regions[regionNum1];
            aPlayer.setRegion(nextRegion.getName());
            regions[regionNum1].setVisited(true);
            regionScene = regionScene(nextRegion);
            int rand = (int) (Math.random() * 2);
            ship.setFuel(ship.getFuel() - 1);
            fuelLabel.setText("Fuel:" + ship.getFuel());
            if (ship.getFuel() < 1) {
                System.out.println("You ran out of fuel! GAME OVER!");
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                if (rand == 0) {
                    stage.setScene(randomNPC(regionScene));
                    stage.show();
                } else {
                    stage.setScene(regionScene);
                    stage.show();
                }
            }
        });
        regionA.setOnMouseClicked(e -> {
            System.out.println(aPlayer.getCredits());
            int regionNum1 = (Integer.parseInt(
                    aPlayer.getRegion().substring(aPlayer.getRegion().length() - 1)) + 1) % 10;
            Region nextRegion = regions[regionNum1];
            aPlayer.setRegion(nextRegion.getName());
            regions[regionNum1].setVisited(true);
            regionScene = regionScene(nextRegion);
            int rand = (int) (Math.random() * 2);
            ship.setFuel(ship.getFuel() - 1);
            fuelLabel.setText("Fuel:" + ship.getFuel());
            if (ship.getFuel() < 1) {
                System.out.println("You ran out of fuel! GAME OVER!");
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                if (rand == 0) {
                    stage.setScene(randomNPC(regionScene));
                    stage.show();
                } else {
                    stage.setScene(regionScene);
                    stage.show();
                }
            }

        });

        //*** Create the current market & Get items from the market ***
        market = new Market(aPlayer, regions[regionNum]);
        listItems = market.getItems();
        //        //*** Create the ship ***
        //        ship = new Ship("Ship", 3, inventory, 10, 10);
        //        Label fuelLabel = new Label("Fuel: " + ship.getFuel());
        //        canvas.getChildren().add(fuelLabel);
        //        fuelLabel.setTextFill(Color.GREEN);
        //        fuelLabel.setLayoutX(550);
        //        fuelLabel.setLayoutY(200);
        //*** Text to display player's inventory ***
        Text itemAmountText = new Text();
        itemAmountText.setFill(Color.DARKORANGE);
        itemAmountText.setFont(Font.font("Futura", FontWeight.BOLD, 11.5));
        itemAmountText.setLayoutX(310);
        itemAmountText.setLayoutY(335);
        Text amountToBuySell = new Text("Amount to Buy/Sell: ");
        canvas.getChildren().add(amountToBuySell);
        amountToBuySell.setFill(Color.DARKORANGE);
        amountToBuySell.setFont(Font.font("Futura", FontWeight.BOLD, 12));
        amountToBuySell.setLayoutX(30);
        amountToBuySell.setLayoutY(365);
        TextField amount = new TextField();
        canvas.getChildren().add(amount);
        amount.setFont(Font.font("Futura", 11.5));
        amount.setPromptText("#");
        amount.setLayoutX(165);
        amount.setLayoutY(345);
        amount.setMaxSize(50, 25);
        playerCredits = new Text();
        canvas.getChildren().add(playerCredits);
        playerCredits.setFill(Color.CORNFLOWERBLUE);
        playerCredits.setFont(Font.font("Futura", FontWeight.BOLD, 11.5));
        playerCredits.setLayoutX(310);
        playerCredits.setLayoutY(370);
        //*** Create buy and sell buttons for all goods ***
        for (int j = 0; j < listItems.size(); j++) {
            Item x = listItems.get(j);
            Button itemButton = new Button(x.getName() + " (" + x.getQuantity() + ")");
            Button buyButton = new Button("Buy: " + x.getBuyPrice());
            Button sellButton = new Button("Sell: " + x.getSellPrice());
            canvas.getChildren().addAll(itemButton, buyButton, sellButton);
            itemButton.setMinSize(90, 25);
            itemButton.setLayoutX(30);
            itemButton.setLayoutY(28 + ((j) * 35));
            itemButton.setFont(Font.font("Futura", FontWeight.NORMAL, 11.5));
            buyButton.setMinSize(71, 25);
            buyButton.setLayoutX(123);
            buyButton.setLayoutY(28 + ((j) * 35));
            buyButton.setFont(Font.font("Futura", FontWeight.NORMAL, 11.5));
            sellButton.setMinSize(67, 25);
            sellButton.setLayoutX(197);
            sellButton.setLayoutY(28 + ((j) * 35));
            sellButton.setFont(Font.font("Futura", FontWeight.NORMAL, 11.5));

            // j = # corresponding to goods
            // Make k final j for lamda
            final int k = j;

            amount.setOnKeyReleased(e -> {
                try {
                    if (amount.getText() != null && !amount.getText().trim().isEmpty()) {
                        quantity = Integer.parseInt(amount.getText());
                    } else if (amount.getText().trim().isEmpty()) {
                        quantity = 1;
                    }
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Illegal Character");
                    alert.setContentText("Oops, enter a valid number.");
                    alert.showAndWait();
                }
            });
            buyButton.setOnAction(e -> {
                // remove old inventory before updating
                canvas.getChildren().remove(itemAmountText);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                int m = market.buyGoods(k, quantity, aPlayer, ship, regions[regionNum]);
                if (m == 1) {
                    alert.setContentText("Goods not available in market!");
                } else if (m == 2) {
                    alert.setContentText("Not enough goods in market");
                } else if (m == 3) {
                    alert.setContentText("Cargo will be too full after purchase!");
                } else if (m == 4) {
                    alert.setContentText("Not enough credits!");
                } else {
                    alert.setContentText("No problem!");
                }
                alert.showAndWait();
                playerCredits.setText("Credits Left: " + aPlayer.getCredits());
                itemButton.setText(x.getName() + " (" + x.getQuantity() + ")");

                // Displays updated items and their amounts in inventory
                String itemAmountString = "";
                for (Map.Entry<String, Integer> entry : ship.getInventory().entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    itemAmountString =  itemAmountString + key + ": " + value + " / ";
                }
                itemAmountText.setText(itemAmountString);
                canvas.getChildren().add(itemAmountText);
            });
            sellButton.setOnAction(e -> {
                canvas.getChildren().remove(itemAmountText);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                int m = market.sellGoods(k, quantity, aPlayer, ship, regions[regionNum]);
                if (m == 1) {
                    alert.setContentText("Goods not available in market!");
                } else if (m == 2) {
                    alert.setContentText("Not enough goods in cargo");
                }  else if (m == -1) {
                    alert.setContentText("Can't sell a rare item!");
                } else {
                    alert.setContentText("No problem!");
                }
                alert.showAndWait();
                playerCredits.setText("Credits Left: " + aPlayer.getCredits());
                itemButton.setText(x.getName() + " (" + x.getQuantity() + ")");

                // Displays items and their amounts
                String itemAmountString = "";
                for (Map.Entry<String, Integer> entry : ship.getInventory().entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    itemAmountString = itemAmountString + key + ": " + value + " / ";
                }
                itemAmountText.setText(itemAmountString);
                canvas.getChildren().add(itemAmountText);
            });
        }

        VBox vPane = new VBox();
        vPane.getChildren().add(topPane);
        vPane.getChildren().add(canvas);
        vPane.getChildren().add(bottomPane);
        if (region.getRegionNumber() == gameWinningRegion) {
            //if region.getRegionNumber() == gameWinningRegion
            Button winButton = new Button("RARE: Win Amulet - 1000 Credits");
            canvas.getChildren().add(winButton);
            winButton.setLayoutX(550);
            winButton.setLayoutY(310);
            winButton.setOnMouseClicked(e -> {
                win = false;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                if (aPlayer.getCredits() < 1000) {
                    alert.setContentText("Not enough credits!");
                } else {
                    alert.setContentText("Success! YOU WIN THE GAME!");
                    aPlayer.setCredits(aPlayer.getCredits() - 1000);
                    win = true;
                }
                alert.showAndWait();
                if (win) {
                    win = false;
                    stage.setScene(winScene());
                    stage.show();

                }
            });
        }
        if (ship.getHealth() <= 0) {
            stage.setScene(gameOverScreen());
            stage.show();

        }
        regionScene = new Scene(vPane, 820, 470);
        return regionScene;
    }
    private Scene winScene() {
        Label gameWinLabel = new Label("GAME COMPLETE!");
        gameWinLabel.setTextFill(Color.DARKRED);
        gameWinLabel.setLayoutX(500);
        gameWinLabel.setLayoutY(400);
        VBox vPane = new VBox();

        vPane.getChildren().addAll(gameWinLabel, continueCredits);
        Scene winScene = new Scene(vPane, 820, 470);
        return winScene;
    }
    /**
     * Method to create interaction with bandit
     * @param nextRegion the scene that it will go to next depending on interactions
     * @return the bandit scene
     */
    private Scene banditScene(Scene nextRegion) {
        BackgroundImage bgI2 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        //creating panes
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        canvas.setBackground(new Background(bgI2));
        VBox leftPane = new VBox(20);
        HBox topPane = new HBox(20);
        bottomPane.setAlignment(Pos.CENTER);
        topPane.setAlignment(Pos.CENTER);
        leftPane.setStyle("-fx-background-color: lightgrey");
        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        Bandit aBandit = new Bandit();

        Label itemsWanted = new Label(aBandit.toString());
        int demandAmount = aBandit.getPayment(); //tentative, not changing yet
        Button payDemands = new Button("Pay " + demandAmount + " Credits");
        if (aPlayer.getCredits() < demandAmount) {
            payDemands.setText("Pay Demands (NOT ENOUGH CREDITS)");
        }
        Button fightBandit = new Button("Fight Back");
        Button fleeBandit = new Button("Flee Bandit (Consumes Fuel)");
        payDemands.setLayoutX(100);
        fightBandit.setLayoutX(375);
        fleeBandit.setLayoutX(625);
        payDemands.setLayoutY(300);
        fightBandit.setLayoutY(300);
        fleeBandit.setLayoutY(300);
        payDemands.setOnMouseClicked(e -> {
            int trytoPay = aBandit.pay(aPlayer, ship);
            if (trytoPay == 4) {
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        fightBandit.setOnMouseClicked(e -> {
            int trytoFight = aBandit.fight(aPlayer, ship);
            if (trytoFight == 3) {
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        fleeBandit.setOnMouseClicked(e -> {
            int trytoFlee = aBandit.flee(aPlayer, ship);
            ship.setFuel(ship.getFuel() - 1);
            if (ship.getFuel() < 1) {
                System.out.println("You ran out of fuel! GAME OVER!");
                stage.setScene(gameOverScreen());
                stage.show();
            }
            if (trytoFlee == 3) {
                stage.setScene(gameOverScreen());
                stage.show();

            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });

        Image banditImage = new Image("bandit.png", 240, 320, false, false);
        ImageView banditI = new ImageView(banditImage);

        Text infoTitle = new Text(100, 100, "Bandit!");
        infoTitle.setFill(Color.MEDIUMPURPLE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 35));
        topPane.getChildren().addAll(infoTitle);
        canvas.getChildren().addAll(banditI, payDemands, fightBandit, fleeBandit);
        bottomPane.getChildren().addAll(itemsWanted);
        VBox vBox = new VBox();
        banditI.setLayoutX(280);
        banditI.setLayoutY(20);
        vBox.getChildren().addAll(topPane, canvas, bottomPane);
        if (ship.getHealth() <= 0) {
            Label gameOverLabel = new Label("GAME OVER");
            Button restart = new Button("Restart");
            gameOverLabel.setTextFill(Color.DARKRED);
            gameOverLabel.setLayoutX(500);
            gameOverLabel.setLayoutY(400);
            vBox.getChildren().clear();
            vBox.getChildren().addAll(gameOverLabel, restart);
        }
        Scene banditScene = new Scene(vBox, 820, 470);
        return  banditScene;
    }

    /**
     * Creates the interaction with the police
     * @param nextRegion the scene that you're trying to go to
     * @return the interaction scene with the police
     */
    private Scene policeScene(Scene nextRegion) {

        //creating panes
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        HBox topPane = new HBox(20);
        Police aPolice = new Police(ship);


        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        bottomPane.setAlignment(Pos.CENTER);
        topPane.setAlignment(Pos.CENTER);
        BackgroundImage bgI2 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        canvas.setBackground(new Background(bgI2));
        Label itemsWanted = new Label(aPolice.toString());
        Button forfeitButton = new Button("Forfeit Your Items");
        Button fleeButton = new Button("Flee (Consumes Fuel)");
        Button fightButton = new Button("Fight Back");
        forfeitButton.setLayoutX(100);
        fleeButton.setLayoutX(375);
        fightButton.setLayoutX(625);
        forfeitButton.setLayoutY(300);
        fleeButton.setLayoutY(300);
        fightButton.setLayoutY(300);
        forfeitButton.setOnMouseClicked(e -> {
            aPolice.forfeit(ship);
            stage.setScene(nextRegion);
            stage.show();
        });
        fleeButton.setOnMouseClicked(e -> {
            int trytoFlee = aPolice.flee(aPlayer, ship);
            ship.setFuel(ship.getFuel() - 1);
            if (ship.getFuel() < 1) {
                System.out.println("You ran out of fuel! GAME OVER!");
                stage.setScene(gameOverScreen());
                stage.show();

            }
            if (trytoFlee == 3) {
                stage.setScene(gameOverScreen());
                stage.show();

            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        fightButton.setOnMouseClicked(e -> {
            int trytoFight = aPolice.fight(aPlayer, ship);
            if (trytoFight == 3) {
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        Image banditImage = new Image("police.png", 240, 320, false, false);
        ImageView banditI = new ImageView(banditImage);
        Text infoTitle = new Text(100, 100, "Police!");
        infoTitle.setFill(Color.MEDIUMPURPLE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 35));
        topPane.getChildren().addAll(infoTitle);
        canvas.getChildren().addAll(banditI, forfeitButton, fleeButton, fightButton);
        bottomPane.getChildren().addAll(itemsWanted);
        banditI.setLayoutX(280);
        banditI.setLayoutY(20);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(topPane, canvas, bottomPane);
        if (ship.getHealth() <= 0) {
            Label gameOverLabel = new Label("GAME OVER");
            Button restart = new Button("Restart");
            gameOverLabel.setTextFill(Color.DARKRED);
            gameOverLabel.setLayoutX(500);
            gameOverLabel.setLayoutY(400);
            vBox.getChildren().clear();
            vBox.getChildren().addAll(gameOverLabel, restart);
        }
        Scene policeScene = new Scene(vBox, 820, 470);
        return  policeScene;
    }



    /**
     * Creates the interaction with the trader
     * @param nextRegion the scene that you're trying to go to
     * @return the interaction scene with the trader
     */
    private Scene traderScene(Scene nextRegion) {
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane();
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        BackgroundImage bgI2 = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        canvas.setBackground(new Background(bgI2));
        HBox topPane = new HBox(20);
        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        Trader aTrader = new Trader(aPlayer, regions[Integer.parseInt(aPlayer.getRegion().substring(
                aPlayer.getRegion().length() - 1))]);
        Label itemSelling = new Label("I'm offering " + aTrader.getTraderGood().getName()
                + " for " + aTrader.getTraderGood().getBuyPrice() + " credits.");

        Button buyButton = new Button("Buy Goods");
        Button robButton = new Button("Rob Trader");
        Button negotiateButton = new Button("Negotiate the Price");

        Image traderImage = new Image("trader.png", 240, 320, false, false);
        ImageView traderI = new ImageView(traderImage);

        buyButton.setLayoutX(100);
        robButton.setLayoutX(375);
        negotiateButton.setLayoutX(625);
        buyButton.setLayoutY(300);
        robButton.setLayoutY(300);
        negotiateButton.setLayoutY(300);

        buyButton.setOnMouseClicked(e -> {
            int tryBuyGoods = aTrader.buyGoods(1, aPlayer, ship, regions[Integer.parseInt(
                    aPlayer.getRegion().substring(aPlayer.getRegion().length() - 1))]);
            if (tryBuyGoods == 0) {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        robButton.setOnMouseClicked(e -> {
            int tryRob = aTrader.robTrader(aPlayer, ship);

            if (tryRob == 0 && ship.getHealth() <= 0) {
                stage.setScene(gameOverScreen());
                stage.show();
            } else {
                stage.setScene(nextRegion);
                stage.show();
            }

        });
        negotiateButton.setOnMouseClicked(e -> {
            int tryNegotiate = aTrader.negotiateTrader(aPlayer, ship);
            itemSelling.setText("I am now offering " + aTrader.getTraderGood().getName()
                    + "for " + aTrader.getTraderGood().getBuyPrice() + " credits instead.");
            //stage.setScene(nextRegion);
            //stage.show();
            canvas.getChildren().remove(negotiateButton);
        });
        bottomPane.setAlignment(Pos.CENTER);
        topPane.setAlignment(Pos.CENTER);
        Text infoTitle = new Text(100, 100, "Trader!");
        infoTitle.setFill(Color.MEDIUMPURPLE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 35));
        topPane.getChildren().addAll(infoTitle);
        canvas.getChildren().addAll(traderI, buyButton, robButton, negotiateButton);
        bottomPane.getChildren().addAll(itemSelling);
        VBox vBox = new VBox();
        traderI.setLayoutX(280);
        traderI.setLayoutY(20);
        vBox.getChildren().addAll(topPane, canvas, bottomPane);
        if (ship.getHealth() <= 0) {
            Label gameOverLabel = new Label("GAME OVER");
            gameOverLabel.setTextFill(Color.DARKRED);
            gameOverLabel.setLayoutX(500);
            gameOverLabel.setLayoutY(400);
            vBox.getChildren().clear();
            vBox.getChildren().addAll(gameOverLabel, restartButton);
        }
        Scene traderScene = new Scene(vBox, 820, 470);
        return  traderScene;

    }
    /**
     * Chooses the random npc scene you will go to
     * @param nextRegion the scene that you're trying to go to
     * @return the type of scene that is randomly picked
     */
    private Scene randomNPC(Scene nextRegion) {
        int rand = (int) (Math.random() * 10);
        if (rand < 3) {
            return traderScene(nextRegion);
        } else if (rand < 6) {
            return banditScene(nextRegion);
        } else {
            if (!ship.getInventory().isEmpty()) {
                return policeScene(nextRegion);
            } else {
                if (rand % 2 == 0) {
                    if (aPlayer.getDifficulty() < 3) {
                        return traderScene(nextRegion);
                    } else {
                        return banditScene(nextRegion);
                    }
                } else {
                    return banditScene(nextRegion);
                }
            }
        }
    }

    /**
     * Screen that allows player upgrades
     * @return the screen for interaction
     */
    private Scene customizationScreen() {
        BackgroundImage bgI = new BackgroundImage(new Image("universe.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Text infoTitle = new Text(100, 100, "Player Upgrades");
        infoTitle.setFill(Color.MEDIUMBLUE);
        infoTitle.setFont(Font.font("Futura", FontWeight.BOLD, 35));
        confirmUpgrades.setFont(Font.font("Futura", FontWeight.BOLD, 16));
        confirmUpgrades.setStyle("-fx-text-fill: #005ce6");
        Text currentUpgrade = new Text("No current upgrade enabled!");
        currentUpgrade.setFill(Color.MEDIUMPURPLE);
        currentUpgrade.setFont(Font.font("Futura", FontWeight.BOLD, 15));
        Button pilotUpgradeEnable = new Button("Equip Pilot+1");
        Button pilotUpgradeDisable = new Button("Eject Pilot+1");
        Button engineerUpgradeEnable = new Button("Equip Engineer+1");
        Button engineerUpgradeDisable = new Button("Eject Engineer+1");
        Button merchantUpgradeEnable = new Button("Equip Merchant+1");
        Button merchantUpgradeDisable = new Button("Eject Merchant+1");
        Button fighterUpgradeEnable = new Button("Equip Fighter+1");
        Button fighterUpgradeDisable = new Button("Eject Fighter+1");

        Image playerImage = new Image("player.png", 240, 320, false, false);
        ImageView playerI = new ImageView(playerImage);


        // Instantiate Panes
        HBox bottomPane = new HBox(20); // number of spacing between children
        Pane canvas = new Pane(pilotUpgradeEnable, pilotUpgradeDisable,
                engineerUpgradeEnable, engineerUpgradeDisable,
                merchantUpgradeEnable, merchantUpgradeDisable,
                fighterUpgradeEnable, fighterUpgradeDisable,
                playerI, currentUpgrade);
        HBox topPane = new HBox(20);

        playerI.setLayoutX(280);
        playerI.setLayoutY(30);

        currentUpgrade.setLayoutX(280);
        currentUpgrade.setLayoutY(345);

        pilotUpgradeEnable.setLayoutX(50);
        pilotUpgradeEnable.setLayoutY(100);

        pilotUpgradeDisable.setLayoutX(50);
        pilotUpgradeDisable.setLayoutY(125);

        engineerUpgradeEnable.setLayoutX(50);
        engineerUpgradeEnable.setLayoutY(175);

        engineerUpgradeDisable.setLayoutX(50);
        engineerUpgradeDisable.setLayoutY(200);

        merchantUpgradeEnable.setLayoutX(650);
        merchantUpgradeEnable.setLayoutY(100);

        merchantUpgradeDisable.setLayoutX(650);
        merchantUpgradeDisable.setLayoutY(125);

        fighterUpgradeEnable.setLayoutX(650);
        fighterUpgradeEnable.setLayoutY(175);

        fighterUpgradeDisable.setLayoutX(650);
        fighterUpgradeDisable.setLayoutY(200);

        pilotUpgradeEnable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Pilot+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                aPlayer.incrementPilot();
                System.out.println(aPlayer.getPilot());
                currentUpgrade.setFill(Color.GOLDENROD);
                currentUpgrade.setText("Pilot+1 is currently enabled!");
                alert.setContentText("Confirmed upgrade!");
            } else if (!currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Another upgrade already enabled");
            } else {
                alert.setContentText("Player does not own upgrade!");
            }
            alert.showAndWait();
        });

        pilotUpgradeDisable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Pilot+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "Pilot+1 is currently enabled!") && contains) {
                aPlayer.decrementPilot();
                System.out.println(aPlayer.getPilot());
                currentUpgrade.setText("No current upgrade enabled!");
                currentUpgrade.setFill(Color.MEDIUMPURPLE);
                alert.setContentText("Confirmed upgrade!");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Upgrade already disabled");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && !contains) {
                alert.setContentText("Player does not own upgrade!");
            } else {
                alert.setContentText("Another upgrade is currently enabled!");
            }
            alert.showAndWait();
        });

        engineerUpgradeEnable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Engineer+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals("No current upgrade enabled!") && contains) {
                aPlayer.incrementEngineer();
                System.out.println(aPlayer.getEngineer());
                currentUpgrade.setFill(Color.GREEN);
                currentUpgrade.setText("Engineer+1 currently enabled!");
                alert.setContentText("Confirmed upgrade!");
            } else if (!currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Another upgrade already enabled");
            } else {
                alert.setContentText("Player does not own upgrade!");
            }
            alert.showAndWait();
        });

        engineerUpgradeDisable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Engineer+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "Engineer+1 currently enabled!") && contains) {
                aPlayer.decrementEngineer();
                System.out.println(aPlayer.getEngineer());
                currentUpgrade.setText("No current upgrade enabled!");
                currentUpgrade.setFill(Color.MEDIUMPURPLE);
                alert.setContentText("Confirmed upgrade!");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Upgrade already disabled");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && !contains) {
                alert.setContentText("Player does not own upgrade!");
            } else {
                alert.setContentText("Another upgrade is currently enabled!");
            }
            alert.showAndWait();
        });

        merchantUpgradeEnable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Merchant+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                aPlayer.incrementMerchant();
                System.out.println(aPlayer.getMerchant());
                currentUpgrade.setFill(Color.BROWN);
                currentUpgrade.setText("Merchant+1 currently enabled!");
                alert.setContentText("Confirmed upgrade!");
            } else if (!currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Another upgrade already enabled");
            } else {
                alert.setContentText("Player does not own upgrade!");
            }
            alert.showAndWait();
        });

        merchantUpgradeDisable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Merchant+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "Merchant+1 currently enabled!") && contains) {
                aPlayer.decrementMerchant();
                System.out.println(aPlayer.getMerchant());
                currentUpgrade.setText("No current upgrade enabled!");
                currentUpgrade.setFill(Color.MEDIUMPURPLE);
                alert.setContentText("Confirmed upgrade!");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Upgrade already disabled");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && !contains) {
                alert.setContentText("Player does not own upgrade!");
            } else {
                alert.setContentText("Another upgrade is currently enabled!");
            }
            alert.showAndWait();
        });

        fighterUpgradeEnable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Fighter+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                aPlayer.incrementFighter();
                System.out.println(aPlayer.getFighter());
                currentUpgrade.setFill(Color.RED);
                currentUpgrade.setText("Fighter+1 is currently enabled!");
                alert.setContentText("Confirmed upgrade!");
            } else if (!currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Another upgrade already enabled");
            } else {
                alert.setContentText("Player does not own upgrade!");
            }
            alert.showAndWait();
        });

        fighterUpgradeDisable.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            boolean contains = false;
            for (String item: ship.getInventory().keySet()) {
                if (item.equals("Fighter+1")) {
                    contains = true;
                }
            }
            if (currentUpgrade.getText().equals(
                    "Fighter+1 is currently enabled!") && contains) {
                aPlayer.decrementFighter();
                System.out.println(aPlayer.getFighter());
                currentUpgrade.setText("No current upgrade enabled!");
                currentUpgrade.setFill(Color.MEDIUMPURPLE);
                alert.setContentText("Confirmed upgrade!");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && contains) {
                alert.setContentText("Upgrade already disabled");
            } else if (currentUpgrade.getText().equals(
                    "No current upgrade enabled!") && !contains) {
                alert.setContentText("Player does not own upgrade!");
            } else {
                alert.setContentText("Another upgrade is currently enabled!");
            }
            alert.showAndWait();
        });


        topPane.setAlignment(Pos.CENTER);
        topPane.setStyle("-fx-background-color: black");
        bottomPane.setStyle("-fx-background-color: lightgrey");
        canvas.setPrefSize(900, 470);
        canvas.setMaxSize(900, 470);
        canvas.setBackground(new Background(bgI));

        topPane.getChildren().addAll(infoTitle);
        bottomPane.getChildren().add(confirmUpgrades);

        VBox vPane = new VBox();
        vPane.getChildren().add(topPane);
        vPane.getChildren().add(canvas);
        vPane.getChildren().add(bottomPane);


        customizationScreen = new Scene(vPane, 820, 470);
        return customizationScreen;
    }

    /**
     * Creates javafx application
     * @param primaryStage represents javafx main stage
     * @throws Exception if parameters entered are null
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        BackgroundImage bgI = new BackgroundImage(new Image("space.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        customizationScreen = customizationScreen();
        universeScreen = universeScreen();
        characterCreationScene = characterCreationScene();
        startingScene = startingScene();
        Random rand = new Random();
        int firstScene = rand.nextInt(10);
        startingRegion = "region" + firstScene;
        Region first = regions[firstScene];

        regionScene =  regionScene(first);


        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setTitle("Space Travelers"); // Set the stage title
        primaryStage.setResizable(false); // User cannot resize the window
        primaryStage.setScene(startingScene); // Place the starting scene in the stage
        primaryStage.show(); // Display the stage


        confirm.setOnAction(e -> {
            if ((name.getText() != null) && (
                    !name.getText().trim().isEmpty())
                    && group.getSelectedToggle() != null) {
                Scene characterInformationScene = characterInformationScene();
                primaryStage.setScene(characterInformationScene);

                primaryStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("No Name or No Difficulty Selection");
                alert.setContentText(
                        "Oops, you forgot to enter a name or select a difficulty!");
                alert.showAndWait();
            }
        });

        // Initiated again here since character creation scene initiation
        // is inside a lambda - DO NOT DELETE -
        beginGame = new Button("Begin Game");

        beginGame.setOnAction(e -> {
            int newSkillPoints = this.aPlayer.getSkillPoints() + 1;
            this.aPlayer.setSkillPoints(newSkillPoints);
            aPlayer.setRegion(startingRegion);
            primaryStage.setScene(regionScene);
            primaryStage.show();
        });

        confirmUpgrades.setOnAction(e -> {
            primaryStage.setScene(universeScreen);
            primaryStage.show();
        });

        spinner1.setOnMouseReleased(e -> {

            try {
                int difficulty;
                if (easy.isSelected()) {
                    difficulty = 0;
                } else if (med.isSelected()) {
                    difficulty = 1;
                } else if (hard.isSelected()) {
                    difficulty = 2;
                } else {
                    difficulty = 5;
                }
                if (aPlayer.getName().equals(
                        "nullName4566123138487865513211455@fhjkaldf mdz.zv")
                        || aPlayer.getDifficulty() == -1) {
                    aPlayer = new Player(name.getText(), difficulty);
                }
                if (spinner1.getValue() > aPlayer.getPilot()) {
                    aPlayer.incrementPilot();
                } else if (spinner1.getValue() < aPlayer.getPilot()) {
                    aPlayer.decrementPilot();
                }
            } catch (IllegalArgumentException noInfo) {
                if (spinner1.getValue() + spinner2.getValue()
                        + spinner3.getValue() + spinner4.getValue()
                        > aPlayer.getStartPoints()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Skill Points");
                    alert.setContentText("Oops, you've exceeded your allotted skill points.");
                    Integer newValue = spinner1.getValue() - 1;
                    spinner1.getValueFactory().setValue(newValue);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("No Name or No Difficulty Selection");
                    alert.setContentText(
                            "Oops, you forgot to enter a name or select a difficulty!");
                    alert.showAndWait();
                    Integer newValue = spinner1.getValue() - 1;
                    spinner1.getValueFactory().setValue(newValue);
                }
            }
        });
        spinner2.setOnMouseReleased(e -> {

            try {
                int difficulty;
                if (easy.isSelected()) {
                    difficulty = 0;
                } else if (med.isSelected()) {
                    difficulty = 1;
                } else if (hard.isSelected()) {
                    difficulty = 2;
                } else {
                    difficulty = 5;
                }
                if (aPlayer.getName().equals(
                        "nullName4566123138487865513211455@fhjkaldf mdz.zv")
                        || aPlayer.getDifficulty() == -1) {
                    aPlayer = new Player(name.getText(), difficulty);
                }
                if (spinner2.getValue() > aPlayer.getFighter()) {
                    aPlayer.incrementFighter();
                } else if (spinner2.getValue() < aPlayer.getFighter()) {
                    aPlayer.decrementFighter();
                }
            } catch (IllegalArgumentException noInfo) {
                if (spinner1.getValue() + spinner2.getValue()
                        + spinner3.getValue() + spinner4.getValue()
                        > aPlayer.getStartPoints()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Skill Points");
                    alert.setContentText("Oops, you've exceeded your allotted skill points.");
                    Integer newValue = spinner2.getValue() - 1;
                    spinner2.getValueFactory().setValue(newValue);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("No Name or No Difficulty Selection");
                    alert.setContentText(
                            "Oops, you forgot to enter a name or select a difficulty!");
                    alert.showAndWait();
                    Integer newValue = spinner2.getValue() - 1;
                    spinner2.getValueFactory().setValue(newValue);
                }
            }
        });
        spinner3.setOnMouseReleased(e -> {

            try {
                int difficulty;
                if (easy.isSelected()) {
                    difficulty = 0;
                } else if (med.isSelected()) {
                    difficulty = 1;
                } else if (hard.isSelected()) {
                    difficulty = 2;
                } else {
                    difficulty = 5;
                }
                if (aPlayer.getName().equals(
                        "nullName4566123138487865513211455@fhjkaldf mdz.zv")
                        || aPlayer.getDifficulty() == -1) {
                    aPlayer = new Player(name.getText(), difficulty);
                }
                if (spinner3.getValue() > aPlayer.getMerchant()) {
                    aPlayer.incrementMerchant();
                } else if (spinner3.getValue() < aPlayer.getMerchant()) {
                    aPlayer.decrementMerchant();
                }
            } catch (IllegalArgumentException noInfo) {
                if (spinner1.getValue() + spinner2.getValue()
                        + spinner3.getValue() + spinner4.getValue()
                        > aPlayer.getStartPoints()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Skill Points");
                    alert.setContentText("Oops, you've exceeded your allotted skill points.");
                    Integer newValue = spinner3.getValue() - 1;
                    spinner3.getValueFactory().setValue(newValue);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("No Name or No Difficulty Selection");
                    alert.setContentText(
                            "Oops, you forgot to enter a name or select a difficulty!");
                    alert.showAndWait();
                    Integer newValue = spinner3.getValue() - 1;
                    spinner3.getValueFactory().setValue(newValue);
                }
            }
        });
        spinner4.setOnMouseReleased(e -> {

            try {
                int difficulty;
                if (easy.isSelected()) {
                    difficulty = 0;
                } else if (med.isSelected()) {
                    difficulty = 1;
                } else if (hard.isSelected()) {
                    difficulty = 2;
                } else {
                    difficulty = 5;
                }
                if (aPlayer.getName().equals(
                        "nullName4566123138487865513211455@fhjkaldf mdz.zv")
                        || aPlayer.getDifficulty() == -1) {
                    aPlayer = new Player(name.getText(), difficulty);
                }
                if (spinner4.getValue() > aPlayer.getEngineer()) {
                    aPlayer.incrementEngineer();
                } else if (spinner4.getValue() < aPlayer.getEngineer()) {
                    aPlayer.decrementEngineer();
                }
            } catch (IllegalArgumentException noInfo) {
                if (spinner1.getValue() + spinner2.getValue()
                        + spinner3.getValue() + spinner4.getValue()
                        > aPlayer.getStartPoints()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Skill Points");
                    alert.setContentText("Oops, you've exceeded your allotted skill points.");
                    Integer newValue = spinner4.getValue() - 1;
                    spinner4.getValueFactory().setValue(newValue);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("No Name or No Difficulty Selection");
                    alert.setContentText(
                            "Oops, you forgot to enter a name or select a difficulty!");
                    alert.showAndWait();
                    Integer newValue = spinner4.getValue() - 1;
                    spinner4.getValueFactory().setValue(newValue);
                }
            }
        });

        List<Spinner<Integer>> list = new ArrayList();
        list.add(spinner1);
        list.add(spinner2);
        list.add(spinner3);
        list.add(spinner4);
        Integer newValue = 0;

        easy.setOnAction(e -> {
            try {
                aPlayer = new Player(name.getText(), 0);
                for (Spinner<Integer> spinner : list) {
                    spinner.getValueFactory().setValue(newValue);
                }
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Name");
                alert.setContentText("Oops, enter a name first!");
                easy.setSelected(false);
                alert.showAndWait();
            }
        });
        med.setOnAction(e -> {
            try {
                aPlayer = new Player(name.getText(), 1);
                for (Spinner<Integer> spinner : list) {
                    spinner.getValueFactory().setValue(newValue);
                }
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Name");
                alert.setContentText("Oops, enter a name first!");
                med.setSelected(false);
                alert.showAndWait();
            }
        });
        hard.setOnAction(e -> {
            try {
                aPlayer = new Player(name.getText(), 2);
                for (Spinner<Integer> spinner : list) {
                    spinner.getValueFactory().setValue(newValue);
                }
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Name");
                alert.setContentText("Oops, enter a name first!");
                hard.setSelected(false);
                alert.showAndWait();
            }
        });
        goToUniverse.setOnAction(e -> {
            primaryStage.setScene(universeScreen);
            primaryStage.show();
        });

        goToCustomization.setOnAction(e -> {
            primaryStage.setScene(customizationScreen);
            primaryStage.show();
        });

        // All click events for all regions
        region1i.setOnMouseClicked(e -> {
            String aString;
            if (regions[0].getVisited()) {
                Region currentRegion = regions[0];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region0");
                primaryStage.setScene(regionScene);
                primaryStage.show();

            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region2i.setOnMouseClicked(e -> {
            String aString;
            if (regions[1].getVisited()) {
                Region currentRegion = regions[1];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region1");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }

            
        });

        region3i.setOnMouseClicked(e -> {
            String aString;
            if (regions[2].getVisited()) {
                Region currentRegion = regions[2];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region2");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
        });

        region4i.setOnMouseClicked(e -> {
            String aString;
            if (regions[3].getVisited()) {
                Region currentRegion = regions[3];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region3");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region5i.setOnMouseClicked(e -> {
            String aString;
            if (regions[4].getVisited()) {
                Region currentRegion = regions[4];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region4");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region6i.setOnMouseClicked(e -> {
            String aString;
            if (regions[5].getVisited()) {
                Region currentRegion = regions[5];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region5");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region7i.setOnMouseClicked(e -> {
            String aString;
            if (regions[6].getVisited()) {
                Region currentRegion = regions[6];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region6");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region8i.setOnMouseClicked(e -> {
            String aString;
            if (regions[7].getVisited()) {
                Region currentRegion = regions[7];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region7");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region9i.setOnMouseClicked(e -> {
            String aString;
            if (regions[8].getVisited()) {
                Region currentRegion = regions[8];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region8");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });

        region10i.setOnMouseClicked(e -> {
            String aString;
            if (regions[9].getVisited()) {
                Region currentRegion = regions[9];
                aString = "Name: "
                        + currentRegion.getName()
                        + "  / Visited: "
                        + currentRegion.getVisited()
                        + "  / Technology Level: " + currentRegion.getTechLevel();
                this.regionInformation.setText(aString);
                this.regionScene = regionScene(currentRegion);
                aPlayer.setRegion("region9");
                primaryStage.setScene(regionScene);
                primaryStage.show();
            } else {
                aString = "Name: "
                        + "Hidden - Not Visited";
                this.regionInformation.setText(aString);
            }
            
        });
        restartButton.setOnMouseClicked(e -> {
            aPlayer = new Player("nullName4566123138487865513211455@fhjkaldf mdz.zv", -1);
            stage.setScene(startingScene());
            stage.show();
        });
        continueCredits.setOnMouseClicked(e -> {

            stage.setScene(endCredits());
            stage.show();
        });

    }

    /**
     * Main method to start program on certain IDE's
     *
     * @param args String array with all the inputs put into the commandline.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

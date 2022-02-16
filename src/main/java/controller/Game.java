package controller;

import models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;
import java.util.List;

public class Game {
    SceneContainer scenes;
    public static Person player = new Person();
    private static final String os = System.getProperty("os.name").toLowerCase();
    boolean isWindows = System.getProperty("os.name").contains("Windows");
    private MainFrame mainFrame = new MainFrame(); // Java Swing
    private GUILogicTranslator translator;

    public Game () {
        scenes = new SceneContainer();
        translator = new GUILogicTranslator(this, mainFrame);
//        setAllActionListeners();
//        gameBanner();
    }

    public void execute() {


        welcome();

//        checkSaveFile();
//        getPlayerBasicData();
//        clearScreen();
//        runSceneOneCareer(player);
//

//        playAgainOrExit();
    }
    private void mainGameLoop(){
        if (shouldPlay()) {
            Scene currentScene = scenes.getRandomScene(player);
            player.addAge(5);
            updatePlayerAttributesDisplay();
            mainFrame.mainLoop.continueButton.setVisible(false);
            removeAllActionListeners(mainFrame.mainLoop.continueButton);
            removeAllActionListeners(mainFrame.mainLoop.option1);
            removeAllActionListeners(mainFrame.mainLoop.option2);
            mainFrame.mainLoop.option1.setVisible(true);
            mainFrame.mainLoop.option2.setVisible(true);

            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, currentScene.getPrompt());
            translator.editButtonText(mainFrame.mainLoop.option1, currentScene.getOptions().get(0));
            translator.editButtonText(mainFrame.mainLoop.option2, currentScene.getOptions().get(1));
            mainFrame.mainLoop.option1.addActionListener(e -> {
                        translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, currentScene.getOutcomes().get(0));
                        mainFrame.mainLoop.continueButton.setVisible(true);
                        mainFrame.mainLoop.option2.setVisible(false);
                        removeAllActionListeners(mainFrame.mainLoop.option1);
                        EffectsTranslator.doEffects(player, currentScene.getEffects().get(0));
                        mainFrame.mainLoop.continueButton.addActionListener(el -> {
                            System.out.println("lets go!");
                            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, displaySceneSummary(player.addSalary()));

                            removeAllActionListeners(mainFrame.mainLoop.continueButton);

                            mainFrame.mainLoop.continueButton.addActionListener(event -> mainGameLoop());
                        });
                    });
            mainFrame.mainLoop.option2.addActionListener(e -> {
                translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, currentScene.getOutcomes().get(1));
                mainFrame.mainLoop.continueButton.setVisible(true);
                mainFrame.mainLoop.option1.setVisible(false);
                removeAllActionListeners(mainFrame.mainLoop.option2);
                EffectsTranslator.doEffects(player, currentScene.getEffects().get(1));
                mainFrame.mainLoop.continueButton.addActionListener(el -> {
                    System.out.println("lets go!");
                    translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, displaySceneSummary(player.addSalary()));

                    removeAllActionListeners(mainFrame.mainLoop.continueButton);
                    mainFrame.mainLoop.continueButton.setVisible(true);
                    mainFrame.mainLoop.continueButton.addActionListener(event -> mainGameLoop());
                });
            });

        }
    }

    // At the end of a scene user is prompted if they want to save, quit or continue
    private void nextTurnPrompt() {
        System.out.println("\nEnter any key to continue or type 'save' to save the game. Or 'quit' to end the game.");
        String askToSave = getInput();

        if (askToSave.equalsIgnoreCase("quit")) {
            System.out.println("Quitting game");
            System.exit(1);
        }

        if (askToSave.equalsIgnoreCase("save")) {
//            WriteFile saveGame = new WriteFile("saveFile.txt", displaySceneSummary(""));
//            saveGame.save();
            saveGame();
        }
    }

    private void updatePlayerAttributesDisplay(){
        String playerAttributes =  "\n" +
                " | Name: " + player.getName() +
                " | Age: " + player.getAge() +
                " | Career Choice: " + player.getCareer() +
                " | NetWorth: " + player.getPrettyNetWorth() +
                " | Health: " + player.getHealthPoints() + " |\n\n" +
                " | Strength: " + player.getStrength() +
                " | Intellect: " + player.getIntellect() +
                " | Creativity: " + player.getCreativity() + " | \n";

        translator.writeToComponent(mainFrame.mainLoop.playerInfoTextArea, playerAttributes);
        mainFrame.mainLoop.playerInfoTextArea.setFont(new Font("Hei", Font.BOLD, 16));
    }

    private void playAgainOrExit() {
    }

    //doesn't clear the scroll bar
    public void clearScreen() {
        // depending on your Operating system, this method is able to clear the screen using a new ProcessBuilder()
        ProcessBuilder var0 = os.contains("windows") ? new ProcessBuilder(new String[]{"cmd", "/c", "cls"}) : new ProcessBuilder(new String[]{"clear"});

        try {
            var0.inheritIO().start().waitFor();
        } catch (InterruptedException var2) {
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    private String displaySceneSummary(String salaryBreakdown) {
        String values = "";
        System.out.println("\n++++++ 5-Year Summary ++++++");
        System.out.println("Player: " + player.getName());
        System.out.println("Age: " + player.getAge());
        System.out.println("Net Worth: " + player.getPrettyNetWorth());
        System.out.println("Health: " + player.getHealthPoints());
        System.out.println("Children: " + player.getChildren());
        if (player.isMarried()) {
            System.out.println("Spouse: Sam");
        } else {
            System.out.println("Partner: " + (player.getPartner() == null ? "none" : "Sam"));
        }
        System.out.println(salaryBreakdown);
        // This is currently being used to output the summary.
        // This can go away when serialization is implemented
        values += ("++++++ 5-Year Summary ++++++");
        values += ("\nPlayer: " + player.getName());
        values += ("\nNet Worth: " + player.getPrettyNetWorth());
        values += ("\nChildren: " + player.getChildren());
        if (player.isMarried()) {
            values += ("\nSpouse: " + player.getPartner());
        } else {
            values += ("\nPartner: " + (player.getPartner() == null ? "none" : player.getPartner().getName()));
        }
        return values;
    }

    // At the end, Each scene has an effect on a player, such as loss of money or health, effects are a properties found in the JSON objects
    private void runEffect(int index, Scene currentScene) {
        EffectsTranslator.doEffects(player, currentScene.getEffects().get(index)); // handles mutating the player object
    }

    private void displayOutcome(int index, Scene currentScene) {
        System.out.println(currentScene.getOutcomes().get(index));
        System.out.println();
    }

    // prints the scene details and prompts user to select one of the options provided
    private int prompt(Scene currentScene) {
        System.out.println();
        System.out.println(currentScene.getPrompt());
        System.out.println();
        for (String option : currentScene.getOptions())
            System.out.println(option);

        String input = getInput(currentScene.getOptions());

        int selectedIndex = 0;

        // currentScene.getOptions.indexOf(input) is case-sensitive and the user might not enter the correct case
        // doing it this way ignores case and still gets the index
        for(String option : currentScene.getOptions()) {
            if (option.equalsIgnoreCase(input))
                break;

            selectedIndex++;
        }


        return selectedIndex;
    }

    // first Scene after college, for the career choice
    private void runSceneOneCareer(Person player) {

//        mainFrame.showAttributesScreen(player);
        // availCareers are dictated by user choice in regards going to college

        if(player.hasEducation()) {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, "Congratulations!\nYou finished college.");
        }
        else {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, "You decided to skip the college route.");
        }
        mainFrame.mainLoop.continueButton.setVisible(true);
        mainFrame.mainLoop.option2.addActionListener( e -> {
            chooseCareer();
            mainFrame.changeView("careerChoice");
        });
        mainFrame.mainLoop.continueButton.addActionListener( e-> {
            mainFrame.changeView("careerChoice");
            chooseCareer();
        });


    }

    private void chooseCareer() {
        Map<Careers, List<String>> availCareers = player.hasEducation() ? Careers.getCollegeCareers() : Careers.getNonCollegeCareers();
        System.out.println("What career do you want?");

        // loops and prints out available careers depending on your college decision
        List<String> allValidCareers = new ArrayList<>();
        for (Careers career : availCareers.keySet()) {
            for (String specialty : availCareers.get(career)) {
                System.out.println(specialty);
                allValidCareers.add(specialty);
            }
        }

        translator.editButtonText(mainFrame.careerChoice.button1, "New Text!");

       for (int i = 0; i < mainFrame.careerChoice.allCareerChoiceButtons.size(); i++){
           //edit button text
           translator.editButtonText(mainFrame.careerChoice.allCareerChoiceButtons.get(i), allValidCareers.get(i));
           String choice = allValidCareers.get(i);
           //add ev ent listener
           mainFrame.careerChoice.allCareerChoiceButtons.get(i).addActionListener( e -> {
               setPlayerCareerFromCareerChoices(choice, availCareers, allValidCareers);
           });
       }



        System.out.println("\nYou chose a " + player.getCareer() + " job");

    }

    private void setPlayerCareerFromCareerChoices(String choice,  Map<Careers, List<String>> availCareers, List<String> allValidCareers){

        // sets career and breaks if you have selected a valid career
        for (Careers career : availCareers.keySet()) {
            for (String specialty : availCareers.get(career)) {
                if (choice.equalsIgnoreCase(specialty)) {
                    player.setCareer(career);
                    break;
                }
            }
        }
        mainGameLoop();
        mainFrame.changeView("mainLoop");
    }

    private String getInput(Collection<String> options) {
        String[] optionsArray = options.toArray(new String[0]);
        return getInput(optionsArray);
    }

    /**
     * Gets the user input
     *
     * @param selections a list of valid selections
     * @return lower case version of user input
     */
    private String getInput(String... selections) { // Asks as our prompter class for the backstory scene
        Scanner scanner = new Scanner(System.in);
        // expects either the right user input from the selections provided, "help" or "quit"
        while (true) {
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equalsIgnoreCase("Help"))
//                this.helpMenu();

            if (userInput.equalsIgnoreCase("quit")) {
                System.out.println("Quitting game");
                System.exit(1);
                return "";
            }

            if (selections.length == 0)
                return userInput;

            for (String selection : selections)
                if (userInput.equalsIgnoreCase(selection))
                    return userInput;

            System.out.println("\nInvalid input. Valid options are:\n");

            for (String selection : selections)
                System.out.println(selection);
        }
    }

    public void checkSaveFile() // Not working as expected
    {
        File checkFile = new File("saveFile.txt");
        try
        { // able to read the existing saveFile, however once game is loaded, it takes you back to the backstory
            if(checkFile.exists() == true)
            {
                System.out.println("Enter name of player...");
                String playerSavedName = getInput();
                System.out.println(playerSavedName);
                ReadFile read = new ReadFile("saveFile.txt");
                String info = "";
                for(String str: read.getStringArray())
                {
                    int i = 0;
                    if(str.toUpperCase().contains(playerSavedName.toUpperCase()))
                    {
                        System.out.println("Found name");
                        for(String str1: read.getStringArray())
                        {
                            info+=str1;
                            info+="\n";
                            if(str.contains("+") && i >0)
                            {
                                break;
                            }
                            i++;
                        }
                    }
                }
                String[] infoArray = info.split("\n");
                for(int i = 0; i < infoArray.length; i++)
                {
                    System.out.println(infoArray[i]);
                }
                //System.out.println(read.toString());
            }
            else if(checkFile.exists() == false)
            {
                //System.out.println("File does not exist");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    // generates the backstory for the game (from a child -> college)
    public void getPlayerBasicData() {
        String printBackstoryArt = Art.getArt("backstory");
        System.out.println(printBackstoryArt);  // Prints backstory banner
        System.out.println("Enter your Name: ");

        System.out.println("Select your privilege status (Working Class)/(Middle Class): ");
        translator.editButtonText(mainFrame.backstory.button1, "Working Class");
        translator.editButtonText(mainFrame.backstory.button2, "Middle Class");
        translator.editButtonText(mainFrame.backstory.button3, "");
        mainFrame.backstory.button3.setVisible(false);
        mainFrame.backstory.continueButton.setVisible(false);
        translator.writeToComponent(mainFrame.backstory.textArea,"Select your privilege status!" );



        mainFrame.backstory.button1.addActionListener(e -> {
            this.player.setNetWorth(player.getNetWorth() - 25000);
            mainFrame.backstory.button3.setVisible(true);
            startBackstoryScenes();});

        mainFrame.backstory.button2.addActionListener(e -> {
            this.player.setNetWorth(player.getNetWorth() + 25000);
            mainFrame.backstory.button3.setVisible(true);
            startBackstoryScenes();});
    }

    private void startBackstoryScenes(){
        List<Backstory> backstories = getBackStoryScenes(); // stores backstory data from JSON in a List
        processBackstories(backstories, 0);
    }


    private void collegeScene(){
//        // TODO: Make this better narrative
//        // Going to college reduces your net worth by -100000
        translator.writeToComponent(mainFrame.backstory.textArea, "Would you like to go to college?");
        translator.editButtonText(mainFrame.backstory.button1, "Yes");
        translator.editButtonText(mainFrame.backstory.button2, "No");
        mainFrame.backstory.button3.setVisible(false);
        removeAllActionListeners(mainFrame.backstory.button3);
        mainFrame.backstory.button1.addActionListener(e -> {
            player.addMoney(-100000);
            player.setEducation(true);

            //these should trigger career choice scene
            mainFrame.changeView("mainLoop");
            runSceneOneCareer(player);

        });
        mainFrame.backstory.button2.addActionListener(e -> {
            player.setEducation(false);
            //these should trigger career choice scene
            mainFrame.changeView("mainLoop");
            runSceneOneCareer(player);

        });

    }

    // Responsible for simulating the backstory
    private void processBackstories(List<Backstory> backstories, int j) {
        /*
        displays backstory banner
        displays the current scene (prompt & options)
        prompts user for an answer
         */

        System.out.println("Your character's stats:");
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Intellect: " + player.getIntellect());
        System.out.println("Creativity: " + player.getCreativity());

        //reset teh action listeners here
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(0));
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(1));
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(2));
        removeAllActionListeners(mainFrame.backstory.continueButton);
//        mainFrame.hideContinueButton();
        mainFrame.backstory.button1.setVisible(true);
        mainFrame.backstory.button2.setVisible(true);
        mainFrame.backstory.button3.setVisible(true);

        if (j > backstories.size() - 1){
//            mainFrame.hideBackstorySelectionScreen();
            System.out.println("**** FINISIHED END OF PROCESS BACKSTORIES *****");
            collegeScene();
            return;
        }
        else{
            Backstory backstory = backstories.get(j);
            System.out.println("J____" + j);
            int i = 0;
            translator.writeToComponent(mainFrame.backstory.textArea, backstory.getPrompt());
            for (BackstoryOption option : backstory.getOptions()){
                translator.editButtonText(mainFrame.backstory.allBackstoryOptionsButtons.get(i), option.getText());

                mainFrame.backstory.allBackstoryOptionsButtons.get(i).addActionListener(e -> {
                    mainFrame.backstory.continueButton.setVisible(true);
                    mainFrame.backstory.button1.setVisible(false);
                    mainFrame.backstory.button2.setVisible(false);
                    mainFrame.backstory.button3.setVisible(false);
                    mainFrame.backstory.continueButton.addActionListener(event -> {
                        mainFrame.backstory.continueButton.setVisible(false);
                        processBackstories(backstories, j + 1);

                    });
                    translator.writeToComponent(mainFrame.backstory.textArea, option.getOutcome());
                    EffectsTranslator.getAttribute(player, option.getAttribute());

                });
                i++;
            };

        }

    }



    // Reads backstory data from JSON file and stores it in an arrayList
    private List<Backstory> getBackStoryScenes() {
        List<Backstory> backstories = new ArrayList<>();
        JSONArray fileData = readJsonArray("scenes/backstory.json");
        for (Object jsonBackstory : fileData) {
            Backstory backstory = Backstory.fromJson((JSONObject) jsonBackstory);
            backstories.add(backstory);
        }
        return backstories;
    }

    private JSONArray readJsonArray(String path) {
        File file = new File(path);
        StringBuilder jsonString = new StringBuilder();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
                jsonString.append(reader.nextLine());

        } catch (Exception e) {
            System.out.println(e);
        }

        return new JSONArray(jsonString.toString());
    }

    private String welcome() {
        String art = "\n" +
                "  /$$$$$$              /$$           /$$$$$$$  /$$           /$$              /$$$$$$                  /$$$$$$$  /$$                 /$$$$$$$$                  /$$                    \n" +
                " /$$__  $$            | $$          | $$__  $$|__/          | $$             /$$__  $$                | $$__  $$|__/                |__  $$__/                 |__/                    \n" +
                "| $$  \\__/  /$$$$$$  /$$$$$$        | $$  \\ $$ /$$  /$$$$$$$| $$$$$$$       | $$  \\ $$  /$$$$$$       | $$  \\ $$ /$$  /$$$$$$          | $$  /$$$$$$  /$$   /$$ /$$ /$$$$$$$   /$$$$$$ \n" +
                "| $$ /$$$$ /$$__  $$|_  $$_/        | $$$$$$$/| $$ /$$_____/| $$__  $$      | $$  | $$ /$$__  $$      | $$  | $$| $$ /$$__  $$         | $$ /$$__  $$| $$  | $$| $$| $$__  $$ /$$__  $$\n" +
                "| $$|_  $$| $$$$$$$$  | $$          | $$__  $$| $$| $$      | $$  \\ $$      | $$  | $$| $$  \\__/      | $$  | $$| $$| $$$$$$$$         | $$| $$  \\__/| $$  | $$| $$| $$  \\ $$| $$  \\ $$\n" +
                "| $$  \\ $$| $$_____/  | $$ /$$      | $$  \\ $$| $$| $$      | $$  | $$      | $$  | $$| $$            | $$  | $$| $$| $$_____/         | $$| $$      | $$  | $$| $$| $$  | $$| $$  | $$\n" +
                "|  $$$$$$/|  $$$$$$$  |  $$$$/      | $$  | $$| $$|  $$$$$$$| $$  | $$      |  $$$$$$/| $$            | $$$$$$$/| $$|  $$$$$$$         | $$| $$      |  $$$$$$$| $$| $$  | $$|  $$$$$$$\n" +
                " \\______/  \\_______/   \\___/        |__/  |__/|__/ \\_______/|__/  |__/       \\______/ |__/            |_______/ |__/ \\_______/         |__/|__/       \\____  $$|__/|__/  |__/ \\____  $$\n" +
                "                                                                                                                                                      /$$  | $$               /$$  \\ $$\n" +
                "                                                                                                                                                     |  $$$$$$/              |  $$$$$$/\n" +
                "                                                                                                                                                      \\______/                \\______/ \n";
        System.out.println(art);
        mainFrame.writeToTextArea(mainFrame.intro.textArea, "Welcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\n Each choice you make will affect your net worth and health levels.\n\n Enter name to start");

       // getInput();
//        clearScreen();
        return "";
    }

    private boolean shouldPlay() {
        if (player.getHealthPoints() <= 0) {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea,"Game Over. You died because you ran out of health points: " + player.getHealthPoints());
            return false;
        }

        if (player.getNetWorth() >= 1000000) {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea,"You win. You have: " + player.getPrettyNetWorth());
            return false;
        }

        return true;
    }

    public String helpMenu() {
//        mainFrame.writeToTextArea();
        String instructions = "Game is meant to simulate life." +
                "\nThe intent of the game is to have 1 million dollars by the end of the game" +
                "\nChoices will change how much money you have, as well as health points." +
                "\nEx: choosing education will grant you an extra money to your salary" +
                "\nbut skipping college will start you out with less debt." +
                "\nChoose carefully, your life depends on it" +
                "\nIf you're done with the help section, press any key to continue.";

        System.out.println("Game is meant to simulate life." +
                "\nThe intent of the game is to have 1 million dollars by the end of the game" +
                "\nChoices will change how much money you have, as well as health points." +
                "\nEx: choosing education will grant you an extra money to your salary" +
                "\nbut skipping college will start you out with less debt." +
                "\nChoose carefully, your life depends on it" +
                "\nIf you're done with the help section, press any key to continue.");
        return instructions;
    }

//    public void helpMenu() {
//
//        mainFrame.writeToTextArea(mainFrame.textArea, Color.white, "Game is meant to simulate life." +
//                "\nThe intent of the game is to have 1 million dollars by the end of the game" +
//
//                "\nChoices will change how much money you have, as well as health points." +
//                "\nEx: choosing education will grant you an extra money to your salary" +
//                "\nbut skipping college will start you out with less debt.\n" +
//                "\nChoose carefully, your life depends on it!" +
//                "\nIf you're done with the help section, please make a selection below:");
//
//        System.out.println("Game is meant to simulate life." +
//                "\nThe intent of the game is to have 1 million dollars by the end of the game" +
//                "\nChoices will change how much money you have, as well as health points." +
//                "\nEx: choosing education will grant you an extra money to your salary" +
//                "\nbut skipping college will start you out with less debt." +
//                "\nChoose carefully, your life depends on it" +
//                "\nIf you're done with the help section, press any key to continue.");
////        try {
////            System.in.read();
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        }
//    }

    //TODO: move this to eventual translator class
//    private void setAllActionListeners () {
//        mainFrame.playButton.addActionListener(e -> execute());
//        mainFrame.exitButton.addActionListener(e -> exitGame());
//        mainFrame.loadButton.addActionListener(e -> loadGame());
//        mainFrame.helpButton.addActionListener(e -> helpMenu());
//        mainFrame.skipBackstory.addActionListener(e -> displayAttributes());
//    }

//    private void gameBanner() {
//        mainFrame.writeToTextArea(mainFrame.textArea, Color.white, "\nWelcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\nEach choice you make will affect your net worth and health levels.");
//    }

    public static void exitGame() {
        System.exit(1);
    }


    // Testing purposes for now
//    private void displayAttributes(){
//        mainFrame.hideMenuScreen();
//        player.setName("DEV");
//        player.setPrivilege(true);
//        player.setEducation(true);
//        player.addStrength(5);
//        player.addIntellect(5);
//        player.addCreativity(5);
//        player.setCareer(Careers.PASSION);
//        mainFrame.showAttributesScreen(player);
//        System.out.println("Playing the game in DEV mode");
//        System.out.println("Your character's stats:");
//        System.out.println("Strength: " + player.getStrength());
//        System.out.println("Intellect: " + player.getIntellect());
//        System.out.println("Creativity: " + player.getCreativity());
//
//}
    private void removeAllActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }


    public static void saveGame() {
        JSONObject saveData = new JSONObject();

        saveData.put("Career", player.getCareer());
        saveData.put("Education", player.hasEducation());
        saveData.put("Partner", player.getPartner());
        saveData.put("NetWorth", player.getNetWorth());
        saveData.put("Age", player.getAge());
        saveData.put("Health", player.getHealthPoints());
        saveData.put("Children", player.getChildren());
        saveData.put("Career Choice", player.getCareer());
        saveData.put("Strength Attribute", player.getStrength());
        saveData.put("Intellect Attribute", player.getIntellect());
        saveData.put("Creativity Attribute", player.getCreativity());

        JSONObject newSaveData = new JSONObject();
        newSaveData.put(player.getName(), saveData);

        try{
            FileWriter file = new FileWriter("resources/saves/"+player.getName()+".json");
            file.write(newSaveData.toString());
            file.close();
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void loadGame() {
        try {
            String name = JOptionPane.showInputDialog(null, "Please enter player name", "LOAD GAME", JOptionPane.INFORMATION_MESSAGE);
            org.json.simple.JSONObject loadFile = (org.json.simple.JSONObject) new JSONParser().parse(new FileReader("resources/saves/"+name+".json"));
            org.json.simple.JSONObject loadedData = (org.json.simple.JSONObject) loadFile.get(name);
            System.out.println("Loaded File" + loadedData.get("NetWorth"));

            //Set player info from saved file
            player.setName(name);
            long loadedNetWorth = (long) loadedData.get("NetWorth");
            player.setNetWorth((int) loadedNetWorth);
            long loadedAge = (long) loadedData.get("Age");
            player.setAge((int) loadedAge);
            long loadedHealth = (long) loadedData.get("Health");
            player.setHealth((int) loadedHealth);
            long loadedChildren = (long) loadedData.get("Children");
            player.addChild((int) loadedChildren);
            String loadedCareer = (String) loadedData.get("Career");
            System.out.println(loadedCareer);
            player.setCareer(Careers.valueOf(loadedCareer));


            // Set player Attributes
            long loadedStrength = (long) loadedData.get("Strength Attribute");
            player.addStrength((int) loadedStrength);
            long loadedIntellect = (long) loadedData.get("Intellect Attribute");
            player.addIntellect((int) loadedIntellect);
            long loadedCreativity = (long) loadedData.get("Creativity Attribute");
            player.addCreativity((int) loadedCreativity);

            // Load game to main game loop
            MainFrame mainFrame = new MainFrame();
            mainFrame.changeView("mainLoop");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Could not locate your save file");
            System.out.println("ERROR: Could not locate your saved file");

        }
    }
    public static void promptPlayerName () {
        String name = JOptionPane.showInputDialog(null, "Please enter desired name for player", "PLAYER NAME", JOptionPane.INFORMATION_MESSAGE);
        player.setName(name);
        MainFrame.changeView("backstory"); // Once player enters their name this starts the backstory
    }
}



package controller;

import models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import view.MainFrame;
import javax.swing.*;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;

public class Game {
    SceneContainer scenes;
    Person player = new Person();
    private static final String os = System.getProperty("os.name").toLowerCase();
    boolean isWindows = System.getProperty("os.name").contains("Windows");
    private MainFrame mainFrame = new MainFrame(); // Java Swing

    public Game () {
        setAllActionListeners();
        gameBanner();
    }

    public void execute() {
        mainFrame.hideMenuScreen();
        mainFrame.showBackstoryScreen();
        scenes = new SceneContainer();
//        welcome();
//        checkSaveFile();
//        getPlayerBasicData();
//        clearScreen();
//        runSceneOneCareer(player);
//
//        while (shouldPlay()) {
//            clearScreen();
//            Scene currentScene = scenes.getRandomScene(player);
//            System.out.println(currentScene.getArt());
//            System.out.println("\n+++++++ 5 years later +++++++");
//            player.addAge(5);
//            int input = prompt(currentScene);
//            clearScreen();
//            displayOutcome(input, currentScene);
//            runEffect(input, currentScene);
//            String salaryReport = player.addSalary();
//            System.out.println("\nEnter any key to see your 5-year summary");
//            getInput();
//            displaySceneSummary(salaryReport);
//            nextTurnPrompt();
//        }
//        playAgainOrExit();

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
        // availCareers are dictated by user choice in regards going to college
        Map<Careers, List<String>> availCareers = player.hasEducation() ? Careers.getCollegeCareers() : Careers.getNonCollegeCareers();
        String collegeSummary = player.hasEducation() ? "Congratulations!\nYou finished college." : "You decided to skip the college route.";
        System.out.println(collegeSummary);
        System.out.println("What career do you want?");

        // loops and prints out available careers depending on your college decision
        List<String> allValidCareers = new ArrayList<>();
        for (Careers career : availCareers.keySet()) {
            for (String specialty : availCareers.get(career)) {
                System.out.println(specialty);
                allValidCareers.add(specialty);
            }
        }

        String selectedCareer = getInput(allValidCareers); // stores selected career

        // sets career and breaks if you have selected a valid career
        topLoop:
        for (Careers career : availCareers.keySet()) {
            for (String specialty : availCareers.get(career)) {
                if (selectedCareer.equalsIgnoreCase(specialty)) {
                    player.setCareer(career);
                    break topLoop;
                }
            }
        }

        System.out.println("\nYou chose a " + player.getCareer() + " job");

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
                this.helpMenu();

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
    private void getPlayerBasicData() {
        String printBackstoryArt = Art.getArt("backstory");
        System.out.println(printBackstoryArt);  // Prints backstory banner
        System.out.println("Enter your Name: ");
        String playerName = getInput(); // validates and stores user input
        while(playerName.isEmpty()){    // prevents empty strings
            System.out.println("Name is required. Please enter your name.");
            playerName = getInput();
        }

        if (playerName.equalsIgnoreCase("DEV")) {   // cheat code for DEV
            player.setName("DEV");
            player.setPrivilege(true);
            player.setEducation(true);
            player.addStrength(5);
            player.addIntellect(5);
            player.addCreativity(5);
            System.out.println("Playing the game in DEV mode");
            return;
        }
        System.out.println("Select your privilege status (Working Class)/(Middle Class): ");
        String getChoice = getInput("working class", "middle class");
        // Selecting middle class starts you off $25k more to your net worth while working class $25k less(ie it's easier)
        if (getChoice.equalsIgnoreCase("working class")) {
            this.player.setNetWorth(player.getNetWorth() - 25000);
        } else if (getChoice.contains("middle class")) {
            this.player.setNetWorth(player.getNetWorth() + 25000);
        }
        System.out.println("" +
                "You chose: " + getChoice + "\n" +
                "Your Net Worth is: " + player.getPrettyNetWorth() + "\n\n");

        clearScreen();

//        System.out.println(printBackstoryArt);
        List<Backstory> backstories = getBackStoryScenes(); // stores backstory data from JSON in a List
        processBackstories(backstories);
        System.out.println();
        // TODO: Make this better narrative
        // Going to college reduces your net worth by -100000
        System.out.println("Do you want to go to college? (Y/N): ");
        String educationChoice = getInput("y", "n");

        boolean userWantsCollege = educationChoice.equalsIgnoreCase("y");
//        System.out.printf("Your name is %s. You chose to %s college.", playerName, userWantsCollege ? "go to" : "skip");

        if(userWantsCollege)
            player.addMoney(-100000);

        player.setName(playerName);
        player.setEducation(userWantsCollege);
    }

    // Responsible for simulating the backstory
    private void processBackstories(List<Backstory> backstories) {
        /*
        displays backstory banner
        displays the current scene (prompt & options)
        prompts user for an answer
         */
        for (Backstory backstory : backstories) {
            System.out.println(Art.getArt("backstory"));
            System.out.println(backstory.getPrompt());
            System.out.println();

            for (BackstoryOption option : backstory.getOptions())
                System.out.println(option.getText());

            String resp = getInput(backstory.getBackstoryOptionsText()); // user response from options stored here
            BackstoryOption selectedBackstoryOption = null;
            for (BackstoryOption option : backstory.getOptions()) {
                // If the user has selected a correct option, its stored as selectedBackstoryOption
                if (option.getText().contains(resp)) {
                    selectedBackstoryOption = option;
                    break;
                }
            }
            System.out.println();
            System.out.println(selectedBackstoryOption.getOutcome());
            EffectsTranslator.getAttribute(player, selectedBackstoryOption.getAttribute());
            System.out.println("\nPress any key to continue or help for additional instructions");
            getInput();
            clearScreen();
        }
        System.out.println("Your character's stats:");
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Intellect: " + player.getIntellect());
        System.out.println("Creativity: " + player.getCreativity());
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
        System.out.println("Welcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\n Each choice you make will affect your net worth and health levels.");
        System.out.println("\nPress any key to continue.");
        getInput();
        clearScreen();
        return "";
    }

    private boolean shouldPlay() {
        if (player.getHealthPoints() <= 0) {
            System.out.println("Game Over. You died because you ran out of health points: " + player.getHealthPoints());
            return false;
        }

        if (player.getNetWorth() >= 1000000) {
            System.out.println("You win. You have: " + player.getPrettyNetWorth());
            return false;
        }

        return true;
    }

    public void helpMenu() {
        mainFrame.writeToTextArea(mainFrame.textArea,"Game is meant to simulate life." +
                "\nThe intent of the game is to have 1 million dollars by the end of the game." +
                "\nChoices will change how much money you have, as well as health points." +
                "\nEx: choosing education will grant you an extra money to your salary" +
                "\nbut skipping college will start you out with less debt.\n" +
                "\nChoose carefully, your life depends on it!" +
                "\nIf you're done with the help section, please make a selection below:");

        System.out.println("Game is meant to simulate life." +
                "\nThe intent of the game is to have 1 million dollars by the end of the game" +
                "\nChoices will change how much money you have, as well as health points." +
                "\nEx: choosing education will grant you an extra money to your salary" +
                "\nbut skipping college will start you out with less debt." +
                "\nChoose carefully, your life depends on it" +
                "\nIf you're done with the help section, press any key to continue.");
//        try {
//            System.in.read();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    private void setAllActionListeners () {
        mainFrame.playButton.addActionListener(e -> execute());
        mainFrame.exitButton.addActionListener(e -> exitGame());
        mainFrame.loadButton.addActionListener(e -> loadGame());
        mainFrame.helpButton.addActionListener(e -> helpMenu());
    }

    private void gameBanner() {
        mainFrame.writeToTextArea(mainFrame.textArea, "\nWelcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\nEach choice you make will affect your net worth and health levels.");
    }

    private void exitGame() {
        System.exit(1);
    }

    private void saveGame() {
        JSONObject saveData = new JSONObject();

        saveData.put("Career", player.getCareer());
        saveData.put("Education", player.hasEducation());
        saveData.put("Partner", player.getPartner());
        saveData.put("NetWorth", player.getNetWorth());
        saveData.put("Age", player.getAge());
        saveData.put("Health", player.getHealthPoints());
        saveData.put("Children", player.getChildren());

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

    private void loadGame () {
        try {
            String name = JOptionPane.showInputDialog(null, "Please enter player name", "LOAD GAME", JOptionPane.INFORMATION_MESSAGE).toLowerCase();
            org.json.simple.JSONObject loadFile = (org.json.simple.JSONObject) new JSONParser().parse(new FileReader("resources/saves/"+name+".json"));
            org.json.simple.JSONObject loadedData = (org.json.simple.JSONObject) loadFile.get(name);
            //Load scene

            //Load player info
            long loadedNetWorth = (long) loadedData.get("NetWorth");
            player.setNetWorth((int) loadedNetWorth);
            long loadedAge = (long) loadedData.get("Age");
            player.setAge((int) loadedAge);
            long loadedHealth = (long) loadedData.get("Health");
            player.setHealth((int) loadedHealth);
            System.out.println("Example of saved data:" + player.getPrettyNetWorth() + player.getAge() + player.getHealthPoints());


        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Could not locate your save file");
            System.out.println("ERROR: Could not locate your saved file");
        }
    }
}



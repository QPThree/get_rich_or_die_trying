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
    private MainFrame mainFrame = new MainFrame(); // Java Swing
    private GUILogicTranslator translator;

    public Game () {
        scenes = new SceneContainer();
        translator = new GUILogicTranslator(this, mainFrame);

    }
/*
 * ---- Primary Game functions ----
 * Game flow is:
 *      1. getPlayerBasicData()
 *      2. startBackstoryScenes()
 *      3. processBackstories()
 *      4. collegeScene()
 *      5. runSceneOneCareer()
 *      6. chooseCareer()
 *      7. setPlayerCareerFromCareerChoices()
 *      8. mainGameLoop()
 *  -- helper methods below--
 */
    // generates the backstory for the game (from a child -> college)
    public void getPlayerBasicData() {
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

    // Responsible for simulating the backstory
    private void processBackstories(List<Backstory> backstories, int j) {
        /*
        displays backstory banner
        displays the current scene (prompt & options)
        prompts user for an answer
         */

        //reset the action listeners here
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(0));
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(1));
        removeAllActionListeners(mainFrame.backstory.allBackstoryOptionsButtons.get(2));
        removeAllActionListeners(mainFrame.backstory.continueButton);
        mainFrame.backstory.button1.setVisible(true);
        mainFrame.backstory.button2.setVisible(true);
        mainFrame.backstory.button3.setVisible(true);

        if (j > backstories.size() - 1){
            collegeScene();
            return;
        }
        else{
            Backstory backstory = backstories.get(j);

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

    private void collegeScene(){
//        // Going to college reduces your net worth by -100000
        translator.writeToComponent(mainFrame.backstory.textArea, "Would you like to go to college?");
        translator.editButtonText(mainFrame.backstory.button1, "Yes");
        translator.editButtonText(mainFrame.backstory.button2, "No");
        mainFrame.backstory.button3.setVisible(false);
        removeAllActionListeners(mainFrame.backstory.button3);
        mainFrame.backstory.button1.addActionListener(e -> {
            player.addMoney(-100000);
            player.setEducation(true);
            removeAllActionListeners(mainFrame.backstory.button1);
            removeAllActionListeners(mainFrame.backstory.button2);
            //these should trigger career choice scene
            mainFrame.changeView("mainLoop");
            runSceneOneCareer(player);
        });
        mainFrame.backstory.button2.addActionListener(e -> {
            player.setEducation(false);
            removeAllActionListeners(mainFrame.backstory.button1);
            removeAllActionListeners(mainFrame.backstory.button2);
            //these should trigger career choice scene
            mainFrame.changeView("mainLoop");
            runSceneOneCareer(player);

        });

    }

    // first Scene after college, for the career choice
    private void runSceneOneCareer(Person player) {
        updatePlayerAttributesDisplay();
        // availCareers are dictated by user choice in regards going to college
        if(player.hasEducation()) {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, "Congratulations!\nYou finished college.");
        }
        else {
            translator.writeToComponent(mainFrame.mainLoop.sceneInfoTextArea, "You decided to skip the college route.");
        }
        mainFrame.mainLoop.continueButton.setVisible(true);
        mainFrame.mainLoop.option1.setVisible(false);
        mainFrame.mainLoop.option2.setVisible(false);
        mainFrame.mainLoop.continueButton.addActionListener( e-> {
            mainFrame.changeView("careerChoice");
            //DONE: chooseCareers needs to be called from the continue button. Continue button not currently displaying. Switch when it is.
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


        for (int i = 0; i < mainFrame.careerChoice.allCareerChoiceButtons.size(); i++){
            //edit button text
            translator.editButtonText(mainFrame.careerChoice.allCareerChoiceButtons.get(i), allValidCareers.get(i));
            String choice = allValidCareers.get(i);
            //add event listener
            mainFrame.careerChoice.allCareerChoiceButtons.get(i).addActionListener( e -> {
                //pass info to parse and set players career
                setPlayerCareerFromCareerChoices(choice, availCareers, allValidCareers);
                player.setJobTitle(choice.toLowerCase(Locale.ROOT));
            });
        }

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
        updatePlayerAttributesDisplay();
        mainFrame.changeView("mainLoop");

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
                    mainFrame.mainLoop.continueButton.addActionListener(event -> mainGameLoop());
                });
            });

        }
    }

    // ---- End Main Game Functions ----

    // Helper and utility functions
    private boolean shouldPlay() {
        if (player.getNetWorth() >= 1000000) {
            mainFrame.changeView("winner");
            translator.writeToComponent(mainFrame.winner.textArea,"You win. Your NetWorth is: " + player.getPrettyNetWorth() +
                    "\n" + player.getName() + " you were able to become a millionaire at the age of " + player.getAge() +
                    "\nYou have made some pretty good life decisions. \nHope you enjoy the millionaire lifestyle!!!" );
            return false;
        }

        if (player.getHealthPoints() <= 0) {
            mainFrame.changeView("gameOver");
            translator.writeToComponent(mainFrame.gameOver.textArea,"Game Over! " + player.getName()+ " you have lost because you ran out of health points" +
                    "\nYou have died trying to become a millionaire" +
                    "\nAt the time of your death you were " + player.getAge() + " year's old " +
                    "\nwith a NetWorth of " + player.getPrettyNetWorth() +
                    "\n\nYou may want to rethink some of your life decisions ");
            return false;
        }
        return true;
    }

    public void updatePlayerAttributesDisplay(){
        String playerAttributes =  "\n" +
                " | Name: " + player.getName() +
                " | Age: " + player.getAge() +
                (player.getJobTitle() == null? " ":" | Job: " + player.getJobTitle())+
                " | NetWorth: " + player.getPrettyNetWorth() +
                " | Health: " + player.getHealthPoints() + " |\n\n" +
                " | Strength: " + player.getStrength() +
                " | Intellect: " + player.getIntellect() +
                " | Creativity: " + player.getCreativity() +
                " | Career Path: " + player.getCareer() + " | \n";

        translator.writeToComponent(mainFrame.mainLoop.playerInfoTextArea, playerAttributes);
        mainFrame.mainLoop.playerInfoTextArea.setFont(new Font("Hei", Font.BOLD, 16));
    }

    private String displaySceneSummary(String salaryBreakdown) {
        String values = "";
        if (player.isMarried()) {
            System.out.println("Spouse: Sam");
        } else {
            System.out.println("Partner: " + (player.getPartner() == null ? "none" : "Sam"));
        }
        System.out.println(salaryBreakdown);
        // This is currently being used to output the summary.
        // This can go away when serialization is implemented
        values += (player.getMoneyChange() +"\n");
        values += (player.getHealthChange() == null ? "No changes to health from your decision" : player.getHealthChange() +"\n\n");
        values += ("++++++ 5-Year Summary ++++++");
        values += ("\nPlayer: " + player.getName());
        values += ("\nEarnings: " + player.getCareer().getSalaryAmount() + " x 5 = " +  player.getCareer().getSalaryAmount() * 5);
        values += ("\nNet Worth: " + player.getPrettyNetWorth());
        values += ("\nChildren: " + player.getChildren());
        if (player.isMarried()) {
            values += ("\nSpouse: " + player.getPartner().getName());
        } else {
            values += ("\nPartner: " + (player.getPartner() == null ? "none" : player.getPartner().getName()));
        }
        return values;
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

    public static void exitGame() {
        System.exit(1);
    }

    private void removeAllActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

    //  ---- Saving and Loading ----
    public void saveGame() {
        JSONObject saveData = new JSONObject();

        saveData.put("Education", player.hasEducation());
        saveData.put("Partner Status", player.getPartnerStatus());
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
            JOptionPane.showMessageDialog(null,"File has been saved with filename:  " + player.getName());
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null,e.getLocalizedMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadGame() {
        try {
            String name = JOptionPane.showInputDialog(null, "Please enter filename", "LOAD GAME", JOptionPane.INFORMATION_MESSAGE);
            if (name != null) {
                org.json.simple.JSONObject loadFile = (org.json.simple.JSONObject) new JSONParser().parse(new FileReader("resources/saves/" + name + ".json"));
                org.json.simple.JSONObject loadedData = (org.json.simple.JSONObject) loadFile.get(name);
                System.out.println("Loaded File" + loadedData.get("NetWorth"));

                //Set player info from saved file
                player.setName(name);
                long savedNetWorth = (long) loadedData.get("NetWorth");
                player.setNetWorth((int) savedNetWorth);
                long savedAge = (long) loadedData.get("Age");
                player.setAge((int) savedAge - 5);
                long savedHealth = (long) loadedData.get("Health");
                player.setHealth((int) savedHealth);
                long savedChildren = (long) loadedData.get("Children");
                player.addChild((int) savedChildren);
                String savedCareer = (String) loadedData.get("Career Choice");
                System.out.println(savedCareer);
                player.setCareer(Careers.valueOf(savedCareer));
                String loadedPartnerStatus = (String) loadedData.get("Partner Status");
                if (loadedPartnerStatus.equals("married")) {
                    player.setMarried(true);
                    player.addPartner(1);
                }
                if (loadedPartnerStatus.equals("partner")) {
                    player.setMarried(false);
                    player.addPartner(1);
                }

                if (loadedPartnerStatus.equals("single")) {
                    player.setMarried(false);
                    player.removePartner();
                }

                // Set player Attributes
                long savedStrength = (long) loadedData.get("Strength Attribute");
                player.setStrength((int) savedStrength);
                long savedIntellect = (long) loadedData.get("Intellect Attribute");
                player.setIntellect((int) savedIntellect);
                long savedCreativity = (long) loadedData.get("Creativity Attribute");
                player.setCreativity((int) savedCreativity);

                // Load game to main game loop
                mainFrame.changeView("mainLoop");
                mainGameLoop();
            } else {
                MainFrame.changeView("intro"); // if player hits cancel on the dialog box it will return them to the intro page
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Could not locate your saved filename");
            System.out.println("ERROR: Could not locate your saved file");

        }
    }

    public static void promptPlayerName () {
        // Resetting player attributes needed when they play again
        player.setNetWorth(0);
        player.setHealth(100);
        player.setAge(18);
        player.setStrength(0);
        player.setCreativity(0);
        player.setIntellect(0);
        player.setMarried(false);
        player.removePartner();
        String name = JOptionPane.showInputDialog(null, "Please enter desired name for player", "PLAYER NAME", JOptionPane.INFORMATION_MESSAGE);
        if(name !=null) {
            player.setName(name);
            MainFrame.changeView("backstory"); // Once player enters their name this starts the backstory
        } else {
            MainFrame.changeView("intro"); // if player hits cancel on the dialog box it will return them to the intro page
        }
    }
}



package controller;


import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

public class GameTest {
    //Primarily testing the existence of resources needed to run Game.

    @Test
    public void saveGame_savesDirectoryShouldExist() {
        File resources = new File("resources/saves");
        assertTrue(resources.exists());
        assertTrue(resources.isDirectory());
    }


    @Test
    public void getBackStoryScenes_scenesDirectoryShouldExist(){
        File scenesDir = new File("scenes/");
        assertTrue(scenesDir.exists()); //directory is there
        assertTrue(scenesDir.isDirectory()); //directory is...a directory
    }

    @Test//tests individual files in the scenes directory
    public void getBackstoryScenes_sceneFilesShouldAllExist() {
        String[] allFiles = {"art", "backstory", "career", "children", "education", "health", "partner", "privilege"};
        int total = 0;
        for (String filename : allFiles){
            File file = new File("scenes/"+filename + ".json");
            assertTrue(file.exists());
            total++;
        }
        assertEquals(total, 8); //8 matches number of files needed in scenes directory


    }
}
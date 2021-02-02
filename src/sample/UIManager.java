package sample;

import java.util.ArrayList;

public class UIManager {
    private ArrayList<Screen> scenes = new ArrayList<Screen>();

    public UIManager()
    {

    }

    public void addScene(Screen newScene)
    {
        scenes.add(newScene);
    }

    // Shows the scene at a given index in the scene array list.
    // 0    - Home
    // 1    - Recipe Overview
    // 2    - Change Recipe
    public void showScene(int sceneIndex)
    {
        if(sceneIndex < scenes.size() && sceneIndex >= 0)
        {
            scenes.get(sceneIndex).show();
        }
        else
        {
            System.out.println("Scene index out of bounds.");
        }

    }

    public String toString()
    {
        String allScenes = "";
        for(int i = 0; i < scenes.size(); i++)
        {
            allScenes += i + ". " + scenes.get(i).toString() + "\n";
        }
        return allScenes;
    }



}

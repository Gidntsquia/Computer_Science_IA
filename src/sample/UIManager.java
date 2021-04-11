package sample;

import com.aquafx_project.AquaFx;
import com.guigarage.responsive.ResponsiveHandler;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.time.*;



public class UIManager {
    public static Stage stage;
    public static ArrayList<CheckBox> recipeIngredientDesciptors = new ArrayList<>();
    protected static ArrayList<Recipe> recipes = new ArrayList<>();
    protected static ArrayList<Screen> scenes = new ArrayList<>();
    private ArrayList<Node> homeNodes;
    private ArrayList<Node> overviewNodes;
    private ArrayList<Node> changeRecipeNodes;
    protected ArrayList<Node> normalTopNodes;
    protected ArrayList<Node> changeRecipeTopNodes;
    protected static int currentSceneIndex;
    protected static BorderPane root;
    protected static boolean creatingNewRecipe = false;
    protected static boolean creatingNewIngredient = false;




    public UIManager()
    {
        downloadRecipes();
        currentSceneIndex = -1;
        root = new BorderPane();
        // Creates the buttons on the left side of the UI.
        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> {
            sortRecipesByLastAccess();
            showScene(0);
        });

        Button changeRecipeBtn = new Button("Change Recipe");
        changeRecipeBtn.setOnAction(event -> {
            // Only works if on Recipe Overview screen.
            if (currentSceneIndex == 1) {

                scenes.get(2).setRecipe(scenes.get(1).getRecipe());
                showScene(2);
            }


        });

        Button newRecipeBtn = new Button("New Recipe");
        newRecipeBtn.setOnAction(event -> {
            creatingNewRecipe = true;
            Recipe newRecipe = new Recipe("");
            newRecipe.setProcedures(new ArrayList<String>(1));
            newRecipe.setIngredients(new ArrayList<Ingredient>(1));
            scenes.get(2).setRecipe(newRecipe);
            showScene(2);

        });

        Button newIngredientBtn = new Button("New Ingredient");
        newIngredientBtn.setOnAction(event -> {
            creatingNewIngredient = true;
            scenes.get(3).setIngredient(new Ingredient(""));
            showScene(3);

        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(event -> {
            if(currentSceneIndex == 3)
            {
                Ingredient.allIngredients.remove(scenes.get(currentSceneIndex).getIngredient());
                saveRecipes();
            }
            else
            {
                deleteRecipe(scenes.get(currentSceneIndex).getRecipe());
            }

            showScene(0);
            refreshGeneralUI();
        });

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            if (getCurrentSceneIndex() == 0) {
                // Save all recipes if on home screen
                saveRecipes();
            } else if (getCurrentSceneIndex() == 2) {
                // Save recipe that is currently being changed if on change recipe screen.
                scenes.get(2).saveInfo();

                if (creatingNewRecipe) {
                    addRecipe(scenes.get(2).getRecipe());
                    creatingNewRecipe = false;
                }
                saveRecipes();
                showScene(0);
            } else if (getCurrentSceneIndex() == 3) {
                // Save ingredient currently being changed if on change ingredient screen.
                scenes.get(3).saveInfo();
                saveRecipes();
                showScene(0);
            }

        });

        Button downloadBtn = new Button("Download");
        downloadBtn.setOnAction(event -> {
            downloadRecipes();
        });

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> {
            sortRecipesByLastAccess();
            scenes.get(1).getRecipe().resetCurrentlyServes();
            showScene(0);
        });
        Button quitBtn = new Button("Quit");
        quitBtn.setOnAction(event -> {
            if (currentSceneIndex == 0) {
                saveRecipes();
                Platform.exit();
            } else {
                sortRecipesByLastAccess();
                showScene(0);
            }
        });
        homeNodes = new ArrayList<>(Arrays.asList(homeBtn, newRecipeBtn, newIngredientBtn, quitBtn));
        overviewNodes = new ArrayList<>(Arrays.asList(homeBtn, newRecipeBtn, changeRecipeBtn, deleteBtn, cancelBtn));
        changeRecipeNodes = new ArrayList<>(Arrays.asList(homeBtn, deleteBtn, saveBtn, cancelBtn));


        // Creates the search bar at the top of the UI.
        String searchText = "";
        Label recipesLabel = new Label("Recipes");
        TextField searchBar = new TextField();
        searchBar.setOnAction(event -> {
            showScene(0);
            searchRecipes(searchBar);
        });
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(event -> {
            showScene(0);
            System.out.println("Text: (" + ")");
            searchRecipes(searchBar);
        });

        normalTopNodes = new ArrayList<>(Arrays.asList(recipesLabel, searchBar, searchBtn));
        changeRecipeTopNodes = new ArrayList<>();

    }



    protected HBox createTopUI(ArrayList<Node> nodes)
    {
        HBox topUI = new HBox();
        for(Node node : nodes)
        {
            topUI.getChildren().add(node);
        }

        topUI.setPadding(new Insets(20, 5, 0,100));
        topUI.setSpacing(5);

        return topUI;
    }


    private VBox createLeftUI(ArrayList<Node> nodes)
    {
        // Creates the pane for this side.
        VBox leftUI = new VBox();



        // Assigns functionality to the buttons.
        for(Node node : nodes)
        {
            leftUI.getChildren().add(node);
        }

        leftUI.setSpacing(10);

        return leftUI;
    }

    // Creates Ingredient menu on right side of UI.
    public VBox createRightUI()
    {
        VBox rightUI = new VBox();
        MenuButton ingredientList = new MenuButton("Ingredients");
        ArrayList<MenuItem> ingredientMenuItems = new ArrayList<>();
        for(int i = 0; i < Ingredient.allIngredients.size(); i++)
        {
            ingredientMenuItems.add(new MenuItem(Ingredient.allIngredients.get(i).getName()));
            // finalI workaround used here to ensure each ingredient menu calls on correct index.
            int finalI = i;
            ingredientMenuItems.get(i).setOnAction(event -> {
                creatingNewIngredient = false;
                scenes.get(3).setIngredient(Ingredient.allIngredients.get(finalI));
                scenes.get(3).runBirthMethods();
                showScene(3);
            });
        }
        for(MenuItem ingredient : ingredientMenuItems)
        {
            ingredientList.getItems().add(ingredient);
        }
        rightUI.getChildren().add(ingredientList);
        return rightUI;
    }

    // Sorts recipes in order of relevance to search terms
    // and brings up home screen. If search terms are empty,
    // then the recipes are sorted based on last access time.
    private void searchRecipes(TextField searchBar)
    {
        if(searchBar.getText().isEmpty())
        {
            sortRecipesByLastAccess();

        }
        else
        {
            sortRecipesByRelevance(searchBar);
        }
        scenes.get(0).updateUI();
        if(currentSceneIndex != 0)
        {
            showScene(0);
        }
    }

    // Sorts recipes based on closeness to search terms.
    // Closer recipes will be displayed higher in home screen.
    private void sortRecipesByRelevance(TextField searchBar)
    {
        recipes.sort((o1, o2)
                -> o1.compareTo(o2, searchBar.getText()));
    }

    // Sorts recipes based on last time user interacted with the recipe.
    // The Collection is reversed to get descending order with most
    // recent time of access at top. Uses LocalDateTime.
    private void sortRecipesByLastAccess()
    {
        recipes.sort(Comparator.comparing(Recipe::getLastAccessTime));
        Collections.reverse(recipes);
    }

    protected void refreshGeneralUI()
    {
        root.setRight(createRightUI());
        if(currentSceneIndex == 0)
        {

            root.setTop(createTopUI(normalTopNodes));
            root.setLeft(createLeftUI(homeNodes));
        }
        else if(currentSceneIndex == 1)
        {
            root.setTop(createTopUI(normalTopNodes));
            root.setLeft(createLeftUI(overviewNodes));
        }
        else
        {
            // This is for both Change Recipe and Change Ingredient
            root.setTop(createTopUI(changeRecipeTopNodes));
            root.setLeft(createLeftUI(changeRecipeNodes));
        }

    }


    public void show()
    {
        sortRecipesByLastAccess();
        refreshGeneralUI();
        showScene(0);
        Scene myScene = new Scene(root, 800, 800);

        stage.setScene(myScene);
        stage.show();
    }


    // Adds a recipe to the recipes ArrayList. Updates home UI accordingly.
    public void addRecipe(Recipe recipeToBeAdded)
    {
        recipes.add(recipeToBeAdded);
        saveRecipes();
        scenes.get(0).updateUI();
    }

    // Deletes a recipe at a given index from the recipes ArrayList. Updates home UI accordingly.
    public void deleteRecipe(int deleteIndex)
    {
        recipes.remove(deleteIndex);
        saveRecipes();
        scenes.get(0).updateUI();
    }

    // Deletes a given recipe from the recipes ArrayList. Updates home UI accordingly.
    public void deleteRecipe(Recipe recipeForRemoval)
    {
        recipes.remove(recipeForRemoval);
        saveRecipes();
        scenes.get(0).updateUI();
    }

    private void downloadRecipes()
    {
        try{
            recipes = new ArrayList<>(readFileRecipes());
            Ingredient.allIngredients = new ArrayList<>(readFileIngredients());
        }catch(Exception myException){System.out.println(myException);}
    }

    protected void saveRecipes()
    {
        try{
            Writer w1 = new FileWriter("recipes.txt", false);
            Writer w2 = new FileWriter("ingredients.txt", false);
            writeRecipes(recipes);
            writeIngredients(Ingredient.allIngredients);

        }catch(Exception myException) {System.out.println(myException);}

    }

    public static void writeRecipes(ArrayList<Recipe> recipes) throws IOException {
        try (FileOutputStream os = new FileOutputStream("recipes.txt");
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(recipes);
        }
    }

    public static ArrayList<Recipe> readFileRecipes() throws IOException, ClassNotFoundException {
        try (FileInputStream is = new FileInputStream("recipes.txt");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (ArrayList<Recipe>) ois.readObject();
        }
    }

    public static void writeIngredients(ArrayList<Ingredient> ingredients) throws IOException {
        try (FileOutputStream os = new FileOutputStream("ingredients.txt");
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(ingredients);
        }
    }

    public static ArrayList<Ingredient> readFileIngredients() throws IOException, ClassNotFoundException {
        try (FileInputStream is = new FileInputStream("ingredients.txt");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (ArrayList<Ingredient>) ois.readObject();
        }
    }

    public void addIngredient(Ingredient ingredientToBeAdded)
    {
        Ingredient.allIngredients.add(ingredientToBeAdded);
    }

    public void openRecipeOverview(Recipe recipe)
    {
        scenes.get(1).openRecipe(recipe);
        showScene(1);
    }



    public void addScene(Screen newScene)
    {
        scenes.add(newScene);
    }

    // Shows the scene at a given index in the scene array list.
    // 0    - Home
    // 1    - Recipe Overview
    // 2    - Change Recipe
    // 3    - Change Ingredient
    public void showScene(int sceneIndex)
    {
        if(sceneIndex != currentSceneIndex)
        {
            if(sceneIndex < scenes.size() && sceneIndex >= 0)
            {
                currentSceneIndex = sceneIndex;
                refreshGeneralUI();
                scenes.get(sceneIndex).runBirthMethods();
                scenes.get(sceneIndex).updateUI();
                root.setCenter(scenes.get(sceneIndex).getUI());
                stage.setTitle(scenes.get(currentSceneIndex).getTitle());

            }
            else
            {
                System.out.println("Scene index out of bounds.");
            }
        }
        else
        {
            // Simply refresh UI if already on it.
            refreshGeneralUI();
            scenes.get(sceneIndex).updateUI();
        }
    }

    public int getCurrentSceneIndex() {
        return currentSceneIndex;
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

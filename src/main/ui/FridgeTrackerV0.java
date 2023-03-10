//package ui;
//
//import model.*;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//
//// fridge tracker application
//public class FridgeTrackerV0 implements ActionListener {
//    // field and constants
//    private static final String JSON_STORE = "./data/myFridgeOne.json";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_RESET = "\u001B[0m";
//    private Fridge fridge;
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//
//    // EFFECTS: initializes and runs the tacker application
//    public FridgeTrackerV0() {
//        fridge = new Fridge("my Fridge One");
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runTracker();
//    }
//
//    // while loop happens here
//    // MODIFIES: THIS
//    // EFFECTS: process the user input
//    private void runTracker() {
//        boolean keepGoing = true;
//        String command = "";
//
//
//        while (keepGoing) {
//            if (!command.equals("n")) {
//                doShowFoodList();
//            }
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nGoodbye!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        switch (command) {
//            case "a":
//                doAddFood(command);
//                break;
//            case "d":
//                doDiscard();
//                break;
//            case "r":
//                doRemove(command);
//                break;
//            case "s":
//                doSave();
//                break;
//            case "l":
//                doLoad();
//                break;
//            case "n":
//                doNextDay(command);
//                break;
//            default:
//                System.out.println("Selection not valid...");
//                break;
//        }
//    }
//
//    // EFFECTS: displays the main menu
//    private void displayMenu() {
//        System.out.println("\nMain Menu: Select from the following options");
//        System.out.println("\ta -> add new food item to the fridge tracker");
//        System.out.println("\tr -> remove food item from the fridge tracker");
//        System.out.println("\td -> discard the expired food");
//        System.out.println("\ts -> save fridge tracker data to file");
//        System.out.println("\tl -> load fridge tracker date from file");
//        System.out.println("\tq -> quit");
//        System.out.println("\t-----------------------------------------------");
//        System.out.println("\tn -> forward to next day");
//    }
//
//    // EFFECTS: show sub-menu-forward
//    private void showSubMenuForward() {
//        System.out.println("\nSelect from the following options");
//        System.out.println("\tn -> keep forwarding");
//        System.out.println("\to -> return to the main menu");
//    }
//
//    // EFFECTS: show sub-menu-add
//    private void showSubMenuAdd() {
//        System.out.println("\nSelect from the following options");
//        System.out.println("\ta -> keep adding");
//        System.out.println("\to -> return to the main menu");
//    }
//
//    // EFFECTS: show sub-menu-remove
//    private void showSubMenuRemove() {
//        System.out.println("\nSelect from the following options");
//        System.out.println("\tr -> keep removing");
//        System.out.println("\to -> return to the main menu");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add new food item to the fridge
//    private void doAddFood(String cmd) {
//        while (cmd.equals("a")) {
//            Food newFood = instantiateFood();
//            fridge.putInFridge(newFood);
//            System.out.println("\n" + newFood.getName().toUpperCase() + " has been added to the fridge tracker.");
//            showSubMenuAdd();
//            cmd = input.next();
//        }
//    }
//
//    // EFFECTS: instantiates new Food item
//    private Food instantiateFood() {
//        Category category = enterCategory();
//        String name = enterName();
//        int remaining = enterExpiry();
//        return new Food(name, category, remaining);
//    }
//
//    // REQUIRES: input must be one of "v","f","m","l"
//    // EFFECTS: displays options for user to enter type of the food item added
//    private Category enterCategory() {
//        System.out.println("\nPlease select a category for the food");
//
//        int menuLabel = 1;
//        for (Category c : Category.values()) {
//            System.out.println(menuLabel + ": " + c);
//            menuLabel++;
//        }
//
//        int menuSelection = input.nextInt();
//        return Category.values()[menuSelection - 1];
//    }
//
//    // REQUIRES: input must be on non-empty string
//    // EFFECTS: displays options for user to enter name of the food item added
//    private String enterName() {
//        System.out.println("\nEnter the name of item to put in the fridge: ");
//        return input.next();
//    }
//
//    // REQUIRES: input must be a positive integer
//    // EFFECTS: displays options for user to enter remaining days of the food item added
//    private int enterExpiry() {
//        System.out.println("\nEnter the number of days remaining before it expires: ");
//        return input.nextInt();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: show all the food recorded in the fridge tracker
//    //          if (the fridge is empty)
//    //              print "fridge is empty"
//    //          else
//    //              if (item is not expired)
//    //                  item will be printed out in default color
//    //              else
//    //                  item will be printed out in red
//    private void doShowFoodList() {
//        if (fridge.getFoodList().isEmpty()) {
//            System.out.println("\n----- The fridge is empty, time for grocery shopping! ----- ");
//        } else {
//            fridge.customSort();
//            List<Food> temp = fridge.getFoodList();
//            System.out.println("\nHere are all the food items in the fridge: ");
//            System.out.printf("%-20s %-25s %-10s\n", "\tItems", "Remaining Days", "Type");
//            for (Food food : temp) {
//                if (food.getRemaining() != 0) {
//                    System.out.printf("%-20s %-25s %-10s\n",
//                            "\t" + food.getName(),
//                            "" + food.getRemaining(),
//                            food.getCategory());
//                } else {
//                    System.out.printf(ANSI_RED + "%-20s %-25s %-10s\n",
//                            "\t" + food.getName(),
//                            "" + food.getRemaining(),
//                            food.getCategory() + ANSI_RESET);
//                }
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: discard expired food in the fridge
//    private void doDiscard() {
//        List<Food> foodToRemove = new ArrayList<>();
//        for (int i = 0; i < fridge.getFoodList().size(); i++) {
//            if (fridge.getFoodList().get(i).getRemaining() == 0) {
//                foodToRemove.add(fridge.getFoodList().get(i));
//            }
//        }
//        fridge.remove(foodToRemove);
//        System.out.println("\nAll the expired food has been removed.");
//    }
//
//    // REQUIRED: the current fridge track must contain the name of the item to remove
//    // MODIFIES: this
//    // EFFECTS: remove the item with the same name as user input from the fridge track
//    private void doRemove(String cmd) {
//        while (cmd.equals("r")) {
//            System.out.println("\nEnter the exact name of the item to be removed: ");
//            String itemToRemove = input.next();
//            Food temp = null;
//            List<Food> foodToRemove = new ArrayList<>();
//            for (int i = 0; i < fridge.getFoodList().size(); i++) {
//                temp = fridge.getFoodList().get(i);
//                if (temp.getName().equals(itemToRemove)) {
//                    foodToRemove.add(temp);
//                    break;
//                }
//            }
//
//            if (foodToRemove.isEmpty()) {
//                System.out.println("\nNo such food exists in current fridge tracker!");
//            } else {
//                fridge.remove(foodToRemove);
//                System.out.println("\n" + temp.getName().toUpperCase()
//                + " has been removed from the fridge tracker.");
//            }
//
//            showSubMenuRemove();
//            cmd = input.next();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: decrement the remaining days for each food in the food list by one
//    private void doNextDay(String cmd) {
//        while (cmd.equals("n")) {
//            fridge.nextDay();
//            doShowFoodList();
//            showSubMenuForward();
//            cmd = input.next();
//        }
//    }
//
//    /* ----------------- save and load ---------------------------*/
//    // EFFECTS: save the fridge tracker data to file
//    private void doSave() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(fridge);
//            jsonWriter.close();
//            System.out.println("Saved " + fridge.getName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: load fridge tracker data from file
//    private void doLoad() {
//        try {
//            fridge = jsonReader.read();
//            System.out.println("Loaded " + fridge.getName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}

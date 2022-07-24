package ui;

import model.*;

import java.util.*;

// fridge tracker application
public class FridgeTracker {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Fridge fridge;
    private Scanner input;

    // EFFECTS: runs the tacker application
    public FridgeTracker() {
        runTracker();
    }

    // while loop happens here
    // MODIFIES: THIS
    // EFFECTS: process the user input
    private void runTracker() {
        boolean keepGoing = true;
        String command = "";
        init();

        while (keepGoing) {
            if (!command.equals("n")) {
                doShowFoodList();
            }
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAddFood();
                break;
            case "d":
                doDiscard();
                break;
            case "r":
                doRemove();
                break;
            case "n":
                doNextDay(command);
                break;
            default:
                System.out.println("Selection not valid...");
                break;

        }
    }

    // MODIFIES: this
    // EFFECTS: initializes fridge
    private void init() {
        fridge = new Fridge();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the main menu
    private void displayMenu() {
        System.out.println("\nMain Menu: Select from the following options");
        System.out.println("\ta -> add new food item to the fridge tracker");
        System.out.println("\tr -> remove food item from  the fridge tracker");
        System.out.println("\td -> discard the expired food");
        System.out.println("\tq -> quit");
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tn -> forward to next day");
    }

    // MODIFIES: this
    // EFFECTS: add new food item to the fridge
    private void doAddFood() {
        Food newFood;
        String type = enterType();
        newFood = createFoodItem(type);
        newFood.setName(enterName());
        newFood.setRemaining(enterExpiry());

        fridge.putInFridge(newFood);
        System.out.println("\n" + newFood.getName().toUpperCase() + " has been added to the fridge tracker.");
    }

    // MODIFIES: this
    // EFFECTS: instantiate new food item
    private Food createFoodItem(String type) {
        Food newFood = null;
        switch (type) {
            case "v":
                newFood = new Vegetable();
                break;
            case "f":
                newFood = new Fruit();
                break;
            case "m":
                newFood = new Meat();
                break;
            case "l":
                newFood = new Leftover();
                break;
            default:
                System.out.print("Invalid Type!");
                break;
        }
        return newFood;
    }

    // REQUIRES: input must be one of "v","f","m","l"
    // EFFECTS: displays options for user to enter type of the food item added
    private String enterType() {
        System.out.println("\nEnter the type of the item: ");
        System.out.println("Select from:");
        System.out.println("\tv -> Vegetable");
        System.out.println("\tf -> Fruit");
        System.out.println("\tm -> Meat");
        System.out.println("\tl -> LeftOver");
        return input.next();
    }

    // REQUIRES: input must be on non-empty string
    // EFFECTS: displays options for user to enter name of the food item added
    private String enterName() {
        System.out.println("\nEnter the name of item to put in the fridge: ");
        return input.next();
    }


    // REQUIRES: input must be a positive integer
    // EFFECTS: displays options for user to enter remaining days of the food item added
    private int enterExpiry() {
        System.out.println("\nEnter the number of days remaining before it expires: ");
        return input.nextInt();
    }

    // MODIFIES: this
    // EFFECTS: show all the food recorded in the fridge tracker
    //          if (item is not expired)
    //              item will be printed out in default color
    //          else
    //              item will be printed out in red
    private void doShowFoodList() {
        if (fridge.getFoodList().isEmpty()) {
            System.out.println("\n----- The fridge is empty, time for grocery shopping! ----- ");
        } else {
            fridge.customSort();
            List<Food> temp = fridge.getFoodList();
            System.out.println("\nHere are all the food items in the fridge: ");
            System.out.printf("%-15s %-25s %-10s\n", "\tItems", "Remaining Days", "Type");
            for (Food food : temp) {
                if (food.getRemaining() != 0) {
                    System.out.printf("%-15s %-25s %-10s\n",
                            "\t" + food.getName(),
                            "" + food.getRemaining(),
                            food.getType());
                } else {
                    System.out.printf(ANSI_RED + "%-15s %-25s %-10s\n",
                            "\t" + food.getName(),
                            "" + food.getRemaining(),
                            food.getType() + ANSI_RESET);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: discard expired food in the fridge
    private void doDiscard() {
        List<Food> foodToRemove = new ArrayList<>();
        for (int i = 0; i < fridge.getFoodList().size(); i++) {
            if (fridge.getFoodList().get(i).getRemaining() == 0) {
                foodToRemove.add(fridge.getFoodList().get(i));
            }
        }
        fridge.remove(foodToRemove);
        System.out.println("\nAll the expired food has been removed.");
    }

    // REQUIRED: the current fridge track must contain the name of the item to remove
    // MODIFIES: this
    // EFFECTS: remove the item with the same name as user input from the fridge track
    private void doRemove() {
        System.out.println("\nEnter the exact name of the item to be removed: ");
        String itemToRemove = input.next();
        Food temp = null;
        List<Food> foodToRemove = new ArrayList<>();
        for (int i = 0; i < fridge.getFoodList().size(); i++) {
            temp = fridge.getFoodList().get(i);
            if (temp.getName().equals(itemToRemove)) {
                foodToRemove.add(temp);
                break;
            }
        }

        if (foodToRemove.isEmpty()) {
            System.out.println("\nNo such food exists in current fridge tracker!");
        } else {
            fridge.remove(foodToRemove);
            System.out.println("\n" + temp.getName().toUpperCase() + " has been removed from the fridge tracker.");
        }

    }

    // MODIFIES: this
    // EFFECTS: decrement the remaining days for each food in the food list by one
    private void doNextDay(String cmd) {
        while (cmd.equals("n")) {
            fridge.nextDay();
            doShowFoodList();
            showSubMenu();
            cmd = input.next();
        }
    }

    // EFFECTS: show sub-menu
    private void showSubMenu() {
        System.out.println("\n\tn -> keep forwarding");
        System.out.println("\to -> return to the main menu");
    }

}

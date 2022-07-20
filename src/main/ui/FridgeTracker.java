package ui;

import model.*;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// fridge tracker application
public class FridgeTracker {
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
        String command = null;

        init();

        while (keepGoing) {
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
        if (command.equals("a")) {
            doAddFood();
        } else if (command.equals("s")) {
            doShowFoodList();
        } else if (command.equals("j")) {
            doDiscard();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes fridge
    private void init() {
        fridge = new Fridge();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menus of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add new food item to the fridge");
        System.out.println("\ts -> show the current food in the fridge");
        System.out.println("\td -> discard the expired food");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add new food item to the fridge
    private void doAddFood() {

        Food newFood = null;

        String type = enterType();
        String name = enterName();
        String size = enterSize();
        int expiry = enterExpiry();

        switch (type) {
            case "v":
                newFood = new Vege();
                break;
            default:
                System.out.print("Invalid Type!");
                break;
        }

        newFood.setName(name);
        newFood.setType(type);
        newFood.setSize(size);
        newFood.setExpiry(expiry);


        if (fridge.getStorage() + newFood.getSize() <= fridge.getCapacity()) {
            fridge.putInFridge(newFood);
        } else {
            System.out.println("There is not enough space...\n");
        }
    }

    //
    private String enterType() {
        System.out.println("\nEnter the type of the item: ");
        System.out.println("Select from:");
        System.out.println("\tv -> Vegetable");
        System.out.println("\tf -> Fruit");
        System.out.println("\tm -> Meat");
        System.out.println("\tl -> LeftOver");
        String type = input.next();
        return type;
    }

    //
    private String enterName() {
        System.out.println("\nEnter the name of item to put in the fridge: ");
        String name = input.next();
        return name;
    }

    //
    private String enterSize() {
        System.out.println("\nEnter the approximate size of the food: ");
        System.out.println("Select from:");
        System.out.println("\ts -> small");
        System.out.println("\tm -> medium");
        System.out.println("\tl -> large");
        String size = input.next();
        return size;
    }

    //
    private int enterExpiry() {
        System.out.println("\nEnter the number of days remaining before it expires: ");
        int expiry = input.nextInt();
        return expiry;
    }

    // MODIFIES: this
    // EFFECTS: show all the food in the fridge
    private void doShowFoodList() {
        List<Food> myFoodList = fridge.getFoodList();
        System.out.println("\nHere are all the food items in the fridge: ");
        for (int i = 0; i < myFoodList.size(); i++) {
            System.out.printf("%s%n\n", myFoodList.get(i).getName(),myFoodList.get(i).getExpiry());
        }
    }

    // MODIFIES: this
    // EFFECTS: discard expired food in the fridge
    private void doDiscard() {

    }

//    private Fridge selectFridge() {
//
//    }
}

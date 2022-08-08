package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FridgeTracker extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/myFridgeOne.json";
    private Fridge fridge;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // frame
    private final JFrame frame;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem guide;
    private JMenuItem exit;
    private JMenuItem load;
    private JMenuItem save;

    // all the panels
    private JPanel panelCommand;
    private JPanel panelAdd;
    private JPanel panelRemove;
    private JPanel panelDiscard;
    private JPanel panelNextDay;

    // panel display
    private JTextArea textArea;

    private JButton addItem;
    private JTextField enterName;
    private JTextField enterRemainingDays;
    private JComboBox<Category> enterCategory;

    // panel remove
    private JButton removeItem;
    private JTextField enterNameToRemove;

    // panel discard
    private JButton discardItem;

    // panel nextDay
    private JButton nextDay;

    //  EFFECTS: initializes and runs the fridge tacker application
    public FridgeTracker() {
        fridge = new Fridge("my Fridge One");
        frame = new JFrame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initMenuBar();
        initFrame();
        initPanel();

        frame.setVisible(true);
    }

    // EFFECTS: initialize the overall frame
    public void initFrame() {
        frame.setTitle("Fridge Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));
        frame.setResizable(true);
        frame.setSize(1200, 1200);
        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: initialize the menu-bar
    public void initMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        help = new JMenu("Help");
        exit = new JMenuItem("Exit");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        guide = new JMenuItem("Guide");

        guide.addActionListener(this);
        exit.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);


        file.add(load);
        file.add(save);
        file.add(exit);

        help.add(guide);

        menuBar.add(file);
        menuBar.add(help);
    }

    // EFFECTS: initial textarea and command panel, then add them to the frame
    public void initPanel() {

        initTextArea();
        initPanelCommand();

        frame.add(textArea);
        frame.add(panelCommand);
    }

    // EFFECTS: initialize the textarea
    public void initTextArea() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 25));
        textArea.setText("\n----- The fridge is empty, time for grocery shopping! ----- ");
    }

    // initialize the panel for all commands
    public void initPanelCommand() {
        panelCommand = new JPanel();
        panelCommand.setBackground(Color.GRAY);
        panelCommand.setBounds(0, 600, 1200, 600);
        panelCommand.setLayout(new GridLayout(4, 1));

        panelAdd = new JPanel();
        panelRemove = new JPanel();
        panelDiscard = new JPanel();
        panelNextDay = new JPanel();

        setPanelAdd();
        setPanelRemove();
        setPanelDiscard();
        setPanelNextDay();

        panelCommand.add(panelAdd);
        panelCommand.add(panelRemove);
        panelCommand.add(panelDiscard);
        panelCommand.add(panelNextDay);
    }

    // EFFECTS: set up the panel used for Add Item button
    public void setPanelAdd() {

        enterName = new JTextField();
        enterName.setPreferredSize(new Dimension(250, 40));
        enterName.setFont(new Font("Consolas", Font.BOLD, 25));
        enterName.setText("Food Name");

        Category[] c = Category.values();
        enterCategory = new JComboBox<>(c);
        enterCategory.setFont(new Font("Consolas", Font.BOLD, 25));

        enterRemainingDays = new JTextField();
        enterRemainingDays.setPreferredSize(new Dimension(250, 40));
        enterRemainingDays.setFont(new Font("Consolas", Font.BOLD, 25));
        enterRemainingDays.setText("Remaining Days");

        addItem = new JButton();
        addItem.setFont(new Font("Consolas", Font.BOLD, 25));
        addItem.addActionListener(this);
        addItem.setText("Add");

        panelAdd.add(enterName);
        panelAdd.add(enterCategory);
        panelAdd.add(enterRemainingDays);
        panelAdd.add(addItem);
    }

    // EFFECTS: set up the panel used for Remove Item button
    public void setPanelRemove() {
        enterNameToRemove = new JTextField();
        enterNameToRemove.setPreferredSize(new Dimension(250, 40));
        enterNameToRemove.setFont(new Font("Consolas", Font.BOLD, 25));
        enterNameToRemove.setText("Food Name");

        removeItem = new JButton();
        removeItem.setFont(new Font("Consolas", Font.BOLD, 25));
        removeItem.addActionListener(this);
        removeItem.setText("Remove");

        panelRemove.add(enterNameToRemove);
        panelRemove.add(removeItem);
    }

    // EFFECTS: set up the panel used for Discard Item button
    public void setPanelDiscard() {
        discardItem = new JButton();
        discardItem.addActionListener(this);
        discardItem.setText("Discard Expired Items");
        discardItem.setFont(new Font("Consolas", Font.BOLD, 25));

        panelDiscard.add(discardItem);
    }

    // EFFECTS: set up the panel used for Next Day button
    public void setPanelNextDay() {
        nextDay = new JButton();
        nextDay.addActionListener(this);
        nextDay.setText("Next Day");
        nextDay.setFont(new Font("Consolas", Font.BOLD, 25));

        panelNextDay.add(nextDay);
    }

    // MODIFIES: this
    // EFFECTS: show all the food recorded in the fridge tracker
    private void doShowFoodList() {
        textArea.setText("");
        if (fridge.getFoodList().isEmpty()) {
            textArea.append("\n----- The fridge is empty, time for grocery shopping! ----- ");
        } else {
            fridge.customSort();
            List<Food> temp = fridge.getFoodList();
            textArea.append("\nHere are all the food items in the fridge: ");
            textArea.append("\n\tItems" + "\t\t\tRemaining Days" + "\t\tType");
            for (Food food : temp) {
                textArea.append("\n\t" + food.getName() + "\t\t\t" + food.getRemaining()
                        + "\t\t\t" + food.getCategory());
            }
        }
    }

    /* ----------------- save and load ---------------------------*/

    // EFFECTS: save the fridge tracker data to file
    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(fridge);
            jsonWriter.close();
            System.out.println("Saved " + fridge.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            String message = "Unable to write to file: " + JSON_STORE;
            String title = "Save File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        }
    }

    // MODIFIES: this
    // EFFECTS: load fridge tracker data from file
    private void doLoad() {
        try {
            fridge = jsonReader.read();
            System.out.println("Loaded " + fridge.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            String message = "Unable to read from file: " + JSON_STORE;
            String title = "Load File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        }
    }

    /* ---------------------- Action Performer ----------------------- */

    // EFFECTS: performs the actions triggered by buttons
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        // MODIFIES: this
        // EFFECTS: load fridge tracker data from file
        if (e.getSource() == load) {
            doLoad();
            String message = "Loaded " + fridge.getName() + " from " + JSON_STORE;
            String title = "Load File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
            doShowFoodList();
        }

        // EFFECTS: save the fridge tracker data to file
        if (e.getSource() == save) {
            doSave();
            String message = "Saved " + fridge.getName() + " to " + JSON_STORE;
            String title = "Save File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        }

        // EFFECTS: exit the application
        if (e.getSource() == exit) {
            System.exit(0);
            return;
        }

        // EFFECTS: display a short guide to the user
        if (e.getSource() == guide) {
            NewWindow guide = new NewWindow();
        }

        // MODIFIES: this
        // EFFECTS: add new food item to the fridge tracker
        if (e.getSource() == addItem) {
            String name = enterName.getText();
            Category category = (Category) enterCategory.getSelectedItem();
            int remainingDays = Integer.parseInt(enterRemainingDays.getText());
            Food food = new Food(name, category, remainingDays);

            fridge.putInFridge(food);
            doShowFoodList();

            String message = name + " has been added to the fridge tracker.";
            String title = "Add Item";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        }

        // MODIFIES: this
        // EFFECTS: remove food item from the fridge tracker
        if (e.getSource() == removeItem) {
            String itemToRemove = enterNameToRemove.getText();
            List<Food> foodToRemove = new ArrayList<>();
            for (int i = 0; i < fridge.getFoodList().size(); i++) {
                Food temp = fridge.getFoodList().get(i);
                if (temp.getName().equals(itemToRemove)) {
                    foodToRemove.add(temp);
                    break;
                }
            }

            fridge.remove(foodToRemove);
            doShowFoodList();

            String message;
            String title = "Remove Item";

            if (foodToRemove.isEmpty()) {
                message = "No such food exists in current fridge tracker!";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
            } else {
                message = itemToRemove + " has been removed from the fridge tracker.";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
            }

        }

        // MODIFIES: this
        // EFFECTS: discard expired food in the fridge
        if (e.getSource() == discardItem) {
            List<Food> foodToDiscard = new ArrayList<>();
            for (int i = 0; i < fridge.getFoodList().size(); i++) {
                if (fridge.getFoodList().get(i).getRemaining() == 0) {
                    foodToDiscard.add(fridge.getFoodList().get(i));
                }
            }
            fridge.remove(foodToDiscard);
            doShowFoodList();

            String message = "Expired Items have been discarded.";
            String title = "Discard Expired Item";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
        }

        // MODIFIES: this
        // EFFECTS: decrement the remaining days for each food in the food list by one
        if (e.getSource() == nextDay) {
            fridge.nextDay();
            doShowFoodList();
        }
    }
}

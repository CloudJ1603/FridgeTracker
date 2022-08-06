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
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Fridge fridge;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // frame
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenu exit;
    private JMenuItem load;
    private JMenuItem save;

    // all the panels
    private JPanel panelDisplay;
    private JPanel panelCommand;
    private JPanel panelAdd;
    private JPanel panelRemove;
    private JPanel panelDiscard;
    private JPanel panelNextDay;

    // panel display
    private JTextArea textArea;
//    private JTable table;
//    private Object[][] data = {};


    // panel add
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel remainingDaysLabel;

    private JButton addItem;
    private JTextField enterName;
    private JTextField enterRemainingDays;
    private JComboBox enterCategory;


    // panel remove
    private JButton removeItem;
    private JTextField enterNameToRemove;


    public FridgeTracker() {
        fridge = new Fridge("my Fridge One");
        frame = new JFrame();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initFrame();
        initMenuBar();
        initPanel();

        frame.setVisible(true);
    }

    // EFFECTS: return the fridge item
    public Fridge getFridge() {
        return fridge;
    }

    public void initFrame() {
        frame.setTitle("Fridge Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));
//        this.setLayout(null);
        frame.setResizable(true);
        frame.setSize(1200, 1200);

    }

    public void initMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        help = new JMenu("Help");
        exit = new JMenu("Exit");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");

        load.addActionListener(this);
        save.addActionListener(this);

        file.add(load);
        file.add(save);

        menuBar.add(file);
        menuBar.add(help);
        menuBar.add(exit);

        /* ------------------------ frame ----------------------------- */
        frame.setJMenuBar(menuBar);
    }


    public void initPanel() {

        initTextArea();
        initPanelCommand();

        frame.add(textArea);
        frame.add(panelCommand);
        frame.add(panelDiscard);
        frame.add(panelNextDay);

    }

    public void initTextArea() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 25));
        textArea.setText("\n----- The fridge is empty, time for grocery shopping! ----- ");
    }


    public void initPanelCommand() {
//        addButton();
//        removeButton();
//
//        discard = new JButton("Discard");
//        nextDay = new JButton("Next Day");
//
//        discard.addActionListener(this);
//        nextDay.addActionListener(this);

        panelCommand = new JPanel();
        panelCommand.setBackground(Color.GRAY);
        panelCommand.setBounds(0, 600, 1200, 600);
        panelCommand.setLayout(new GridLayout(4, 1));

        panelAdd = new JPanel();
        panelRemove = new JPanel();
        panelDiscard = new JPanel();
        panelNextDay = new JPanel();

        panelAddInit();
        setPanelRemoveInit();

        panelCommand.add(panelAdd);
        panelCommand.add(panelRemove);
        panelCommand.add(panelDiscard);
        panelCommand.add(panelNextDay);

    }

    public void panelAddInit() {

        enterName = new JTextField();
        enterName.setPreferredSize(new Dimension(250, 40));
        enterName.setFont(new Font("Consolas", Font.PLAIN, 25));
        enterName.setText("Food Name");

        Category[] c = Category.values();
        enterCategory = new JComboBox(c);

        enterRemainingDays = new JTextField();
        enterRemainingDays.setPreferredSize(new Dimension(250, 40));
        enterRemainingDays.setFont(new Font("Consolas", Font.PLAIN, 25));
        enterRemainingDays.setText("Remaining Days");

        addItem = new JButton();
        addItem.addActionListener(this);
        addItem.setText("Add to fridge tracker");

        panelAdd.add(enterName);
        panelAdd.add(enterCategory);
        panelAdd.add(enterRemainingDays);
        panelAdd.add(addItem);
    }

    public void setPanelRemoveInit() {
        enterNameToRemove = new JTextField();
        enterNameToRemove.setPreferredSize(new Dimension(250, 40));
        enterNameToRemove.setFont(new Font("Consolas", Font.PLAIN, 25));
        enterNameToRemove.setText("Food Name");

        removeItem = new JButton();
        removeItem.addActionListener(this);
        removeItem.setText("Remove from fridge tracker");

        panelRemove.add(enterNameToRemove);
        panelRemove.add(removeItem);

    }

    private void doShowFoodList() {
        textArea.setText("");
        if (fridge.getFoodList().isEmpty()) {
            textArea.append("\n----- The fridge is empty, time for grocery shopping! ----- ");
        } else {
            fridge.customSort();
            List<Food> temp = fridge.getFoodList();
            textArea.append("\nHere are all the food items in the fridge: ");
            textArea.append("\n\tItems" + "\t\tRemaining Days" + "\t\tType");
            for (Food food : temp) {
                if (food.getRemaining() != 0) {
                    textArea.append("\n\t" + food.getName() + "\t\t" + food.getRemaining()
                            + "\t\t\t" + food.getCategory());
                } else {
                    textArea.append("\n\t" + food.getName()
                            + "\t\t" + food.getRemaining() + "\t\t\t" + food.getCategory());
                }
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
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load fridge tracker data from file
    private void doLoad() {
        try {
            fridge = jsonReader.read();
            System.out.println("Loaded " + fridge.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: performs the action
    @Override
    public void actionPerformed(ActionEvent e) {

        //load
        if (e.getSource() == load) {
            doLoad();
            String message = "Loaded " + fridge.getName() + " from " + JSON_STORE;
            String title = "Load File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        }

        if (e.getSource() == save) {
            doSave();
            String message = "Saved " + fridge.getName() + " to " + JSON_STORE;
            String title = "Save File";
            JOptionPane.showConfirmDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        }

        // MODIFIES: this
        // EFFECTS: add new food item to the fridge tracker
        if (e.getSource() == addItem) {
            String name = (String) enterName.getText();
            Category category = (Category) enterCategory.getSelectedItem();
            int remainingDays = Integer.parseInt(enterRemainingDays.getText());
            Food food = new Food(name, category, remainingDays);

            fridge.putInFridge(food);
            doShowFoodList();
        }

        // MODIFIES: this
        // EFFECTS: remove food item from the fridge tracker
        if (e.getSource() == removeItem) {
            String itemToRemove = (String) enterNameToRemove.getText();
            Food temp = null;
            List<Food> foodToRemove = new ArrayList<>();
            for (int i = 0; i < fridge.getFoodList().size(); i++) {
                temp = fridge.getFoodList().get(i);
                if (temp.getName().equals(itemToRemove)) {
                    foodToRemove.add(temp);
                    break;
                }
            }

            fridge.remove(foodToRemove);
            doShowFoodList();
        }



    }
}

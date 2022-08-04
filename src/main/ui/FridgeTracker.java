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

    // panel
    private JPanel panelDisplay;
    private JPanel panelCommand;
    private JPanel panelAdd;
    private JPanel panelRemove;
    private JPanel panelDiscard;


    // table
    JTable table = new JTable();

    // button
    private JButton add;
    private JButton remove;
    private JButton discard;
    private JButton nextDay;

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

//    public void initTable() {
//        String[] columnNames = {"Name", "Category", "Remaining Days"};
//        Object[][] data;
//
//        List<Food> foods = fridge.getFoodList();
//        int n = foods.size();
//
//        for (int i = 0; i < n; i++) {
//            Food temp = foods.get(i);
//            data[i][0] = temp.getName();
//            data[i][1] = temp.getCategory();
//            data[i][2] = temp.getRemaining();
//        }
//
//        table = new JTable(data, columnNames);
//    }

    public void initTable() {
        String[] columnNames = {"Name", "Category", "Remaining Days"};

        Object[][] data = {
                {"Apple", "Fruit", new Integer(7)},
                {"Lettuce", "Vegetable", new Integer(3)}
        };

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        table.add(scrollPane);
    }


    public void initPanel() {

//        initTable();

        frame.add(getPanelDisplay());

        frame.add(getPanelCommand());

    }

    public void addButton() {
        add = new JButton("Add");
        add.addActionListener(this);

    }

    public void removeButton() {
        remove = new JButton("Remove");
        remove.addActionListener(this);

    }

    public JPanel getPanelDisplay() {
        panelDisplay = new JPanel();
        panelDisplay.setBackground(Color.CYAN);
        panelDisplay.setBounds(0, 0, 1200, 600);
        panelDisplay.setLayout(new BorderLayout());

        return panelDisplay;
    }

    public JPanel getPanelCommand() {
        addButton();
        removeButton();

        discard = new JButton("Discard");
        nextDay = new JButton("Next Day");

        discard.addActionListener(this);
        nextDay.addActionListener(this);

        panelCommand = new JPanel();
        panelCommand.setBackground(Color.GRAY);
        panelCommand.setBounds(0, 600, 1200, 600);
        panelCommand.setLayout(new GridLayout(4, 1));

        panelCommand.add(add);
        panelCommand.add(remove);
        panelCommand.add(discard);
        panelCommand.add(nextDay);

        return panelCommand;
    }


    public void runTracker() {

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
            JOptionPane.showConfirmDialog(null, message,title, JOptionPane.PLAIN_MESSAGE);
        }

        if (e.getSource() == save) {
            doSave();
            String message = "Saved " + fridge.getName() + " to " + JSON_STORE;
            String title = "Save File";
            JOptionPane.showConfirmDialog(null, message,title, JOptionPane.PLAIN_MESSAGE);
        }

        // MODIFIES: this
        // EFFECTS: add new food item to the fridge
        if (e.getSource() == add) {
            NewAddWindow addWindow = new NewAddWindow();
            Food newFood = addWindow.returnFood();
            fridge.putInFridge(newFood);
        }


    }
}

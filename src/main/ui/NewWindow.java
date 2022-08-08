package ui;

import javax.swing.*;
import java.awt.*;

// new window : a short user manual
public class NewWindow {
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();

    // construct the new window
    NewWindow() {
        textArea.setBounds(0,0,420,420);
        textArea.setLineWrap(true);
        textArea.append("Welcome to the fridge tracker!");
        textArea.append("\n\n You can add food item to the fridge tracker by entering a name [String],"
                + " selecting a category, entering remaining days [positive int], and then clicking \"Add\".");
        textArea.append("\n\nYou can remove food item from the fridge tracker by entering the exact name [String]"
                + "of the food item, and then clicking \"Remove\".");
        textArea.append("\n\nYou can discard the expired food items by clicking \"Discard Expired Items\".");
        textArea.append("You can forward to the next day by clicking \"Next Day\".");

        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        frame.add(textArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setVisible(true);
    }
}

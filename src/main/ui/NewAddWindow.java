package ui;

import model.Category;
import model.Food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAddWindow implements ActionListener {

    JFrame frame = new JFrame();
    JLabel label;

    JButton addItem;
    JTextField enterName;
    JTextField enterRemainingDays;

    JComboBox enterCategory;

    Food food;

    NewAddWindow() {


        //frame.add(label);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());


        enterName = new JTextField();
        enterName.setPreferredSize(new Dimension(200, 40));

        Category[] c = Category.values();
        enterCategory = new JComboBox(c);

        enterRemainingDays = new JTextField();
        enterRemainingDays.setPreferredSize(new Dimension(200, 40));

        addItem = new JButton();
        // EFFECTS: add new food item to the fridge
        addItem.addActionListener(this);
        addItem.setText("Add to fridge tracker");


        frame.add(enterName);
        frame.add(enterCategory);
        frame.add(enterRemainingDays);

        frame.add(addItem);
        //frame.pack();
        frame.setVisible(true);
    }

    public Food returnFood() {

        return food;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItem) {
            food.setCategory((Category) enterCategory.getSelectedItem());
            food.setName(enterName.getText());
            int remainingDays = Integer. parseInt(enterRemainingDays.getText());
            food.setRemaining(remainingDays);


        }
    }
}

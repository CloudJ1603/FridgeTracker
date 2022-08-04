package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame implements ActionListener {


    JMenuBar menuBar;
    JMenu file;
    JMenu help;
    JMenu exit;
    JMenuItem load;
    JMenuItem save;

    public MyFrame() {
        this.setTitle("Fridge Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(null);
        this.setResizable(true);
        this.setSize(1200, 1200);

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


        this.setJMenuBar(menuBar);
        //this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == load) {
            System.out.println("load");
        }

        if (e.getSource() == save) {
            System.out.println("save");
        }

        if (e.getSource() == exit) {
            System.exit(1);
        }

    }
}

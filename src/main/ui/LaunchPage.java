package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

    MyFrame frame = new MyFrame();

    JPanel panel1;
    JPanel panel2;

    JButton add;
    JButton remove;
    JButton discard;
    JButton nextDay;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    LaunchPage() {

        panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        panel1.setBounds(0, 0, 1200, 600);
        panel1.setLayout(new BorderLayout());

        /* ------------------------ buttons & button panel ------------------------ */
        add = new JButton("Add");
        remove = new JButton("Remove");
        discard = new JButton("Discard");
        nextDay = new JButton("Next Day");

        add.addActionListener(this);
        remove.addActionListener(this);
        discard.addActionListener(this);
        nextDay.addActionListener(this);

        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setBounds(0, 600, 1200, 600);
        panel2.setLayout(new GridLayout(4, 1));

        panel2.add(add);
        panel2.add(remove);
        panel2.add(discard);
        panel2.add(nextDay);

        /* ------------------------ frame ----------------------------- */
//        MyFrame frame = new MyFrame();
        frame.setLayout(new GridLayout(2, 1));
        frame.add(panel1);
        frame.add(panel2);

        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            NewAddWindow addWindow = new NewAddWindow();
        }

        if (e.getSource() == remove) {
            NewAddWindow removeWindow = new NewAddWindow();
        }



    }
}

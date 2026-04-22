package gui;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    public ProfilePanel(CardLayout pageLayout, JPanel pagePanel, MainFrame mainFrame) {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));

        JPanel formBox = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField nameBox = new JTextField(15);
        JTextField schoolBox = new JTextField(15);
        JTextField sessionBox = new JTextField(15);

        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {

            if (nameBox.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your username to continue.");
                return;
            }

            if (schoolBox.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your school to continue.");
                return;
            }

            if (sessionBox.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your session name to continue.");
                return;
            }

            mainFrame.updateStepIndicator(2);
            pageLayout.show(pagePanel, "define");
        });

        formBox.add(new JLabel("Username:"));
        formBox.add(nameBox);

        formBox.add(new JLabel("School:"));
        formBox.add(schoolBox);

        formBox.add(new JLabel("Session Name:"));
        formBox.add(sessionBox);

        formBox.add(new JLabel(""));
        formBox.add(nextButton);

        add(formBox);
    }
}
package gui;

import model.Scenario;
import service.ScenarioRepository;

import javax.swing.*;
import java.awt.*;

public class DefinePanel extends JPanel {

    private JRadioButton productChoice, processChoice;
    private JRadioButton educationChoice, healthChoice, customChoice;
    private JComboBox<String> scenarioCombo;

    public DefinePanel(CardLayout pageLayout, JPanel pagePanel, PlanPanel planPanel, CollectPanel collectPanel,
                       AnalysePanel analysePanel, ScenarioRepository repository, MainFrame mainFrame) {

        setLayout(new FlowLayout());

        JPanel mainBox = new JPanel(new GridLayout(10, 1, 5, 5));

        productChoice = new JRadioButton("Product Quality");
        processChoice = new JRadioButton("Process Quality");

        educationChoice = new JRadioButton("Education");
        healthChoice = new JRadioButton("Health");
        customChoice = new JRadioButton("Custom");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productChoice);
        qualityGroup.add(processChoice);

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(educationChoice);
        modeGroup.add(healthChoice);
        modeGroup.add(customChoice);

        scenarioCombo = new JComboBox<>();
        scenarioCombo.addItem("Select scenario");

        productChoice.addActionListener(e -> refreshScenarioList());
        processChoice.addActionListener(e -> refreshScenarioList());
        educationChoice.addActionListener(e -> refreshScenarioList());
        healthChoice.addActionListener(e -> refreshScenarioList());
        customChoice.addActionListener(e -> refreshScenarioList());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {mainFrame.updateStepIndicator(1);
                                                      pageLayout.show(pagePanel, "profile");});

        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {

            if (!productChoice.isSelected() && !processChoice.isSelected()) {JOptionPane.showMessageDialog(this, "Please select a quality type.");
                return;
            }

            if (!educationChoice.isSelected() && !healthChoice.isSelected() && !customChoice.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select a mode.");
                return;
            }

            if (scenarioCombo.getSelectedItem() == null || scenarioCombo.getSelectedItem().toString().equals("Select scenario")) {
                JOptionPane.showMessageDialog(this, "Please select a scenario.");
                return;
            }

            Scenario chosenScenario = null;
            String selectedText = scenarioCombo.getSelectedItem().toString();

            if (selectedText.equals("Scenario A - Learning Platform")) { chosenScenario = repository.createScenarioA(); }

            else if (selectedText.equals("Scenario B - Student Workflow")) { chosenScenario = repository.createScenarioB(); }

            else if (selectedText.equals("Scenario C - Clinic System")) { chosenScenario = repository.createScenarioC(); }

            else if (selectedText.equals("Scenario D - Hospital Process")) { chosenScenario = repository.createScenarioD(); }

            else if (selectedText.equals("Custom Scenario")) {


                String dimensionText = JOptionPane.showInputDialog(this, "Enter custom dimension name:");
                if (dimensionText == null || dimensionText.trim().isEmpty()) {
                    return;
                }

                String firstMetricText = JOptionPane.showInputDialog(this, "Enter first metric name:");
                if (firstMetricText == null || firstMetricText.trim().isEmpty()) {
                    return;
                }

                String secondMetricText = JOptionPane.showInputDialog(this, "Enter second metric name:");
                if (secondMetricText == null || secondMetricText.trim().isEmpty()) {
                    return;
                }

                chosenScenario = repository.createCustomScenario(dimensionText.trim(), firstMetricText.trim(), secondMetricText.trim());
            }

            if (chosenScenario != null) {

                planPanel.setScenario(chosenScenario);
                collectPanel.setScenario(chosenScenario);
                analysePanel.setScenario(chosenScenario);

                mainFrame.updateStepIndicator(3);
                pageLayout.show(pagePanel, "plan");
            }

        });

        JPanel buttonBox = new JPanel();
        buttonBox.add(backButton);
        buttonBox.add(nextButton);

        mainBox.add(new JLabel("Select Quality Type:"));
        mainBox.add(productChoice);
        mainBox.add(processChoice);

        mainBox.add(new JLabel("Select Mode:"));
        mainBox.add(educationChoice);
        mainBox.add(healthChoice);
        mainBox.add(customChoice);

        mainBox.add(new JLabel("Select Scenario:"));
        mainBox.add(scenarioCombo);
        mainBox.add(buttonBox);

        add(mainBox);
    }

    private void refreshScenarioList() {

        scenarioCombo.removeAllItems();
        scenarioCombo.addItem("Select scenario");

        if (educationChoice.isSelected() && productChoice.isSelected()) { scenarioCombo.addItem("Scenario A - Learning Platform"); }

        else if (educationChoice.isSelected() && processChoice.isSelected()) { scenarioCombo.addItem("Scenario B - Student Workflow"); }

        else if (healthChoice.isSelected() && productChoice.isSelected()) { scenarioCombo.addItem("Scenario C - Clinic System"); }

        else if (healthChoice.isSelected() && processChoice.isSelected()) { scenarioCombo.addItem("Scenario D - Hospital Process"); }

        else if (customChoice.isSelected()) { scenarioCombo.addItem("Custom Scenario"); }
    }
}
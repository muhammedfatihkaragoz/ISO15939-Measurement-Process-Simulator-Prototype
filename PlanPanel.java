package gui;

import model.Dimension;
import model.Direction;
import model.Metric;
import model.Scenario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends JPanel {

    private DefaultTableModel model;

    public PlanPanel(CardLayout pageLayout, JPanel pagePanel, Scenario scenario, MainFrame mainFrame) {

        setLayout(new BorderLayout(10, 10));

        add(new JLabel("Step 3 - Plan Measurement", SwingConstants.CENTER), BorderLayout.NORTH);

        String[] headers = {"Dimension", "Metric", "Coefficient", "Direction", "Range", "Unit"};

        model = new DefaultTableModel(headers, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {mainFrame.updateStepIndicator(2);
                                                      pageLayout.show(pagePanel, "define");});

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {mainFrame.updateStepIndicator(4);
                                                      pageLayout.show(pagePanel, "collect");});

        JPanel buttonBox = new JPanel();
        buttonBox.add(backButton);
        buttonBox.add(nextButton);

        add(buttonBox, BorderLayout.SOUTH);

        setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {

        model.setRowCount(0);

        for (Dimension dimension : scenario.getDimensions()) {

            for (Metric metric : dimension.getMetrics()) {

                String directionText;

                if (metric.getDirection() == Direction.HIGHER) {directionText = "Higher ↑";}

                else {directionText = "Lower ↓";}

                String rangeText = metric.getMinValue() + " - " + metric.getMaxValue();

                model.addRow(new Object[]{dimension.getName() + " (" + dimension.getCoefficient() + ")",
                                          metric.getName(), metric.getCoefficient(), directionText, rangeText, metric.getUnit()});

            }
        }
    }
}
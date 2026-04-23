package gui;

import model.Dimension;
import model.Direction;
import model.Metric;
import model.Scenario;
import service.MeasurementEngine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CollectPanel extends JPanel {

    private DefaultTableModel model;
    private MeasurementEngine calculator;

    public CollectPanel(CardLayout pageLayout, JPanel pagePanel, Scenario scenario, MainFrame mainFrame) {

        calculator = new MeasurementEngine();

        setLayout(new BorderLayout(10, 10));

        add(new JLabel("Step 4 - Collect Data", SwingConstants.CENTER), BorderLayout.NORTH);

        String[] headers = {"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"};

        model = new DefaultTableModel(headers, 0) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {mainFrame.updateStepIndicator(3);
                                                      pageLayout.show(pagePanel, "plan");});

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {mainFrame.updateStepIndicator(5);
                                                      pageLayout.show(pagePanel, "analyse");});

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

                if (metric.getRawValue() == 0) {

                    if (metric.getDirection() == Direction.HIGHER) {metric.setRawValue(metric.getMaxValue() * 0.85);}

                    else {metric.setRawValue(metric.getMaxValue() * 0.20);}
                }

                double scoreValue = calculator.calculateMetricScore(metric);
                metric.setScore(scoreValue);

                String directionText;
                if (metric.getDirection() == Direction.HIGHER) {directionText = "Higher ↑";}

                else {directionText = "Lower ↓";}

                String rangeText = formatValue(metric.getMinValue()) + " - " + formatValue(metric.getMaxValue());
                String coeffUnitText = metric.getCoefficient() + " / " + metric.getUnit();

                model.addRow(new Object[]{metric.getName(), directionText, rangeText,
                                          formatValue(metric.getRawValue()), formatValue(scoreValue), coeffUnitText});
            }
        }
    }

    private String formatValue(double value) {

        if (value == (int) value) {return String.valueOf((int) value);}

        return String.format("%.1f", value);

    }
}
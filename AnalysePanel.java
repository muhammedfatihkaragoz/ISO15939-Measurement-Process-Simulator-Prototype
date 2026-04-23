package gui;

import model.Scenario;
import service.MeasurementEngine;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends JPanel {

    private JLabel finalScoreLabel;
    private JLabel weakestLabel;
    private JLabel gapLabel;
    private JLabel qualityLabel;
    private JLabel noteLabel;

    private JProgressBar overallBar;
    private JPanel scorePanel;

    private MeasurementEngine calculator;

    public AnalysePanel(CardLayout pageLayout, JPanel pagePanel, Scenario scenario, MainFrame mainFrame) {

        calculator = new MeasurementEngine();

        setLayout(new BorderLayout(10, 10));

        add(new JLabel("Step 5 - Analyse Results", SwingConstants.CENTER), BorderLayout.NORTH);

        finalScoreLabel = new JLabel();
        weakestLabel = new JLabel();
        gapLabel = new JLabel();
        qualityLabel = new JLabel();
        noteLabel = new JLabel();
        noteLabel.setForeground(Color.RED);

        overallBar = new JProgressBar(0, 100);
        overallBar.setStringPainted(true);

        JPanel infoBox = new JPanel(new GridLayout(6, 1, 5, 5));
        infoBox.add(finalScoreLabel);
        infoBox.add(weakestLabel);
        infoBox.add(gapLabel);
        infoBox.add(qualityLabel);
        infoBox.add(noteLabel);
        infoBox.add(overallBar);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        JPanel centerBox = new JPanel(new BorderLayout());
        centerBox.add(infoBox, BorderLayout.NORTH);
        centerBox.add(new JScrollPane(scorePanel), BorderLayout.CENTER);

        add(centerBox, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {mainFrame.updateStepIndicator(4);
                                                      pageLayout.show(pagePanel, "collect");});

        JPanel buttonBox = new JPanel();
        buttonBox.add(backButton);

        add(buttonBox, BorderLayout.SOUTH);

        setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {

        double finalScore = calculator.calculateScenarioScore(scenario);
        model.Dimension lowestDimension = calculator.findLowestDimension(scenario);

        scorePanel.removeAll();

        if (lowestDimension == null) {

            finalScoreLabel.setText("Final Scenario Score: 0.00 / 5.0");
            weakestLabel.setText("Weakest Dimension: N/A");
            gapLabel.setText("Gap To Ideal: N/A");
            qualityLabel.setText("Quality Level: N/A");
            noteLabel.setText("");

            overallBar.setValue(0);
            overallBar.setString("0%");

        }

        else {

            finalScoreLabel.setText("Final Scenario Score: " + String.format("%.2f", finalScore) + " / 5.0");
            weakestLabel.setText("Weakest Dimension: " + lowestDimension.getName());
            gapLabel.setText("Gap To Ideal: " + String.format("%.2f", 5.0 - finalScore));
            qualityLabel.setText("Quality Level: " + calculator.getQualityLabel(finalScore));
            noteLabel.setText("This dimension has the lowest score and requires the most improvement.");

            int totalPercent = (int) (finalScore * 20);
            overallBar.setValue(totalPercent);
            overallBar.setString(totalPercent + "%");

            for (model.Dimension dimension : scenario.getDimensions()) {

                double dimensionScore = calculator.calculateDimensionScore(dimension);
                int percentValue = (int) (dimensionScore * 20);

                JPanel rowBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
                rowBox.add(new JLabel(dimension.getName() + ": " + String.format("%.2f", dimensionScore)));

                JProgressBar bar = new JProgressBar(0, 100);
                bar.setValue(percentValue);
                bar.setStringPainted(true);
                bar.setString(percentValue + "%");
                bar.setPreferredSize(new java.awt.Dimension(500, 22));

                rowBox.add(bar);
                scorePanel.add(rowBox);
            }
        }

        scorePanel.revalidate();
        scorePanel.repaint();
    }
}
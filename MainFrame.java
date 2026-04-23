package gui;

import model.Scenario;
import service.ScenarioRepository;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainFrame extends JFrame {

    private JLabel[] stepTexts;
    private CardLayout pageLayout;
    private JPanel pagePanel;
    private HashMap<Integer, String> steps;

    public MainFrame() {

        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        prepareSteps();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 25));
        topPanel.setPreferredSize(new Dimension(900, 80));

        stepTexts = new JLabel[5];

        for (int i = 1; i <= 5; i++) {

            stepTexts[i - 1] = new JLabel(steps.get(i));
            stepTexts[i - 1].setFont(new Font("Dialog", Font.PLAIN, 16));
            topPanel.add(stepTexts[i - 1]);
        }

        pageLayout = new CardLayout();
        pagePanel = new JPanel(pageLayout);

        ScenarioRepository repository = new ScenarioRepository();
        Scenario firstScenario = repository.createScenarioA();

        PlanPanel planPanel = new PlanPanel(pageLayout, pagePanel, firstScenario, this);
        CollectPanel collectPanel = new CollectPanel(pageLayout, pagePanel, firstScenario, this);
        AnalysePanel analysePanel = new AnalysePanel(pageLayout, pagePanel, firstScenario, this);
        ProfilePanel profilePanel = new ProfilePanel(pageLayout, pagePanel, this);
        DefinePanel definePanel = new DefinePanel(pageLayout, pagePanel, planPanel, collectPanel, analysePanel, repository, this);

        pagePanel.add(profilePanel, "profile");
        pagePanel.add(definePanel, "define");
        pagePanel.add(planPanel, "plan");
        pagePanel.add(collectPanel, "collect");
        pagePanel.add(analysePanel, "analyse");

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(pagePanel, BorderLayout.CENTER);

        add(mainPanel);

        updateStepIndicator(1);
        setVisible(true);
    }

    private void prepareSteps() {

        steps = new HashMap<>();

        steps.put(1, "1 Profile");
        steps.put(2, "2 Define");
        steps.put(3, "3 Plan");
        steps.put(4, "4 Collect");
        steps.put(5, "5 Analyse");
    }

    public void updateStepIndicator(int activeStep) {

        for (int i = 1; i <= 5; i++) {

            stepTexts[i - 1].setText(steps.get(i));
            stepTexts[i - 1].setFont(new Font("Dialog", Font.PLAIN, 16));
            stepTexts[i - 1].setForeground(Color.BLACK);

            if (i < activeStep) {

                stepTexts[i - 1].setText("✓ " + steps.get(i));
            }
        }

        stepTexts[activeStep - 1].setFont(new Font("Dialog", Font.BOLD, 16));
        stepTexts[activeStep - 1].setForeground(Color.BLUE);
    }
}
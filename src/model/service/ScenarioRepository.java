package service;

import model.Dimension;
import model.Direction;
import model.Metric;
import model.Mode;
import model.QualityType;
import model.Scenario;

public class ScenarioRepository {

    public Scenario createScenarioA() {

        Scenario scenario = new Scenario("Scenario A - Learning Platform", QualityType.PRODUCT, Mode.EDUCATION);

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("Usability score", 50, Direction.HIGHER, 0, 100, "points"));
        usability.addMetric(new Metric("Account setup time", 50, Direction.LOWER, 0, 60, "min"));
        scenario.addDimension(usability);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Video opening time", 50, Direction.LOWER, 0, 15, "sec"));
        performance.addMetric(new Metric("Simultaneous exam users", 50, Direction.HIGHER, 0, 600, "users"));
        scenario.addDimension(performance);

        Dimension accessibility = new Dimension("Accessibility", 20);
        accessibility.addMetric(new Metric("Accessibility compliance", 50, Direction.HIGHER, 0, 100, "%"));
        accessibility.addMetric(new Metric("Screen reader support", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(accessibility);

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("System uptime", 50, Direction.HIGHER, 95, 100, "%"));
        reliability.addMetric(new Metric("Recovery time", 50, Direction.LOWER, 0, 120, "min"));
        scenario.addDimension(reliability);

        Dimension functionality = new Dimension("Functional Suitability", 15);
        functionality.addMetric(new Metric("Feature completion", 50, Direction.HIGHER, 0, 100, "%"));
        functionality.addMetric(new Metric("Homework submission rate", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(functionality);

        return scenario;
    }


    public Scenario createScenarioB() {

        Scenario scenario = new Scenario("Scenario B - Student Workflow", QualityType.PROCESS, Mode.EDUCATION);

        Dimension usability = new Dimension("Usability", 30);
        usability.addMetric(new Metric("Student satisfaction score", 60, Direction.HIGHER, 0, 100, "points"));
        usability.addMetric(new Metric("Menu learning time", 40, Direction.LOWER, 0, 45, "min"));
        scenario.addDimension(usability);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Quiz page load time", 50, Direction.LOWER, 0, 10, "sec"));
        performance.addMetric(new Metric("Active student capacity", 50, Direction.HIGHER, 0, 500, "users"));
        scenario.addDimension(performance);

        Dimension accessibility = new Dimension("Accessibility", 15);
        accessibility.addMetric(new Metric("Accessibility support rate", 50, Direction.HIGHER, 0, 100, "%"));
        accessibility.addMetric(new Metric("Subtitle support score", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(accessibility);

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("Service availability", 50, Direction.HIGHER, 95, 100, "%"));
        reliability.addMetric(new Metric("Issue resolution time", 50, Direction.LOWER, 0, 100, "min"));
        scenario.addDimension(reliability);

        Dimension functionality = new Dimension("Functional Suitability", 15);
        functionality.addMetric(new Metric("Completed module rate", 50, Direction.HIGHER, 0, 100, "%"));
        functionality.addMetric(new Metric("Exam upload success", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(functionality);

        return scenario;
    }



    public Scenario createScenarioC() {

        Scenario scenario = new Scenario("Scenario C - Clinic System", QualityType.PRODUCT, Mode.HEALTH);

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(new Metric("Patient usability score", 50, Direction.HIGHER, 0, 100, "points"));
        usability.addMetric(new Metric("Appointment entry time", 50, Direction.LOWER, 0, 30, "min"));
        scenario.addDimension(usability);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(new Metric("Record display time", 50, Direction.LOWER, 0, 10, "sec"));
        performance.addMetric(new Metric("Concurrent patient users", 50, Direction.HIGHER, 0, 1000, "users"));
        scenario.addDimension(performance);

        Dimension accessibility = new Dimension("Accessibility", 20);
        accessibility.addMetric(new Metric("Accessibility compliance", 50, Direction.HIGHER, 0, 100, "%"));
        accessibility.addMetric(new Metric("Voice guidance support", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(accessibility);

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(new Metric("System uptime", 50, Direction.HIGHER, 95, 100, "%"));
        reliability.addMetric(new Metric("Recovery duration", 50, Direction.LOWER, 0, 180, "min"));
        scenario.addDimension(reliability);

        Dimension functionality = new Dimension("Functional Suitability", 15);
        functionality.addMetric(new Metric("Prescription success rate", 50, Direction.HIGHER, 0, 100, "%"));
        functionality.addMetric(new Metric("Appointment completion rate", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(functionality);

        return scenario;
    }



    public Scenario createScenarioD() {

        Scenario scenario = new Scenario("Scenario D - Hospital Process", QualityType.PROCESS, Mode.HEALTH);

        Dimension usability = new Dimension("Usability", 20);
        usability.addMetric(new Metric("Staff usability score", 50, Direction.HIGHER, 0, 100, "points"));
        usability.addMetric(new Metric("Patient admission time", 50, Direction.LOWER, 0, 25, "min"));
        scenario.addDimension(usability);

        Dimension performance = new Dimension("Performance Efficiency", 25);
        performance.addMetric(new Metric("Lab screen loading time", 50, Direction.LOWER, 0, 12, "sec"));
        performance.addMetric(new Metric("Concurrent doctor count", 50, Direction.HIGHER, 0, 800, "users"));
        scenario.addDimension(performance);

        Dimension accessibility = new Dimension("Accessibility", 15);
        accessibility.addMetric(new Metric("Readable interface score", 50, Direction.HIGHER, 0, 100, "%"));
        accessibility.addMetric(new Metric("Large text support", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(accessibility);

        Dimension reliability = new Dimension("Reliability", 25);
        reliability.addMetric(new Metric("Service uptime", 50, Direction.HIGHER, 95, 100, "%"));
        reliability.addMetric(new Metric("Critical recovery time", 50, Direction.LOWER, 0, 150, "min"));
        scenario.addDimension(reliability);

        Dimension functionality = new Dimension("Functional Suitability", 15);
        functionality.addMetric(new Metric("Record accuracy rate", 50, Direction.HIGHER, 0, 100, "%"));
        functionality.addMetric(new Metric("Report completion rate", 50, Direction.HIGHER, 0, 100, "%"));
        scenario.addDimension(functionality);

        return scenario;
    }



    public Scenario createCustomScenario(String dimensionName, String metric1Name, String metric2Name) {

        Scenario scenario = new Scenario("Custom Scenario", QualityType.PRODUCT, Mode.CUSTOM);

        Dimension customDimension = new Dimension(dimensionName, 100);
        customDimension.addMetric(new Metric(metric1Name, 50, Direction.HIGHER, 0, 100, "points"));
        customDimension.addMetric(new Metric(metric2Name, 50, Direction.LOWER, 0, 10, "sec"));

        scenario.addDimension(customDimension);

        return scenario;
    }
}
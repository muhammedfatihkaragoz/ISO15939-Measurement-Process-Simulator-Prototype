package service;

import model.Dimension;
import model.Direction;
import model.Metric;
import model.Scenario;

public class MeasurementEngine {

    public double calculateMetricScore(Metric metric) {

        double min = metric.getMinValue();
        double max = metric.getMaxValue();
        double value = metric.getRawValue();

        if (min == max) return 1.0;

        double score;

        if (metric.getDirection() == Direction.HIGHER) {  score = 1 + ((value - min) / (max - min)) * 4 ;}

        else { score = 5 - ((value - min) / (max - min)) * 4;}


        if (score < 1)  score = 1;
        if (score > 5)  score = 5;

        score = Math.round(score * 2) / 2.0;

        return score;
    }

    public double calculateDimensionScore(Dimension d) {

        double total = 0;
        int coeff = 0;

        for (Metric m : d.getMetrics()) {
            total += m.getScore() * m.getCoefficient();
            coeff += m.getCoefficient();
        }

        if (coeff == 0) return 0;

        return total / coeff;
    }

    public double calculateScenarioScore(Scenario s) {

        double total = 0;
        int coeff = 0;

        for (Dimension d : s.getDimensions()) {

            double score = calculateDimensionScore(d);
            total += score * d.getCoefficient();
            coeff += d.getCoefficient();
        }

        if (coeff == 0) return 0;

        return total / coeff;
    }

    public Dimension findLowestDimension(Scenario s) {

        if (s.getDimensions().isEmpty()) return null;

        Dimension lowest = s.getDimensions().get(0);

        for (Dimension d : s.getDimensions()) {

            if (calculateDimensionScore(d) < calculateDimensionScore(lowest)) {
                lowest = d;
            }
        }

        return lowest;
    }

    public String getQualityLabel(double score) {

        if (score >= 4.5) return "Excellent";
        if (score >= 3.5) return "Good";
        if (score >= 2.5) return "Needs Improvement";
        return "Poor";
    }
}
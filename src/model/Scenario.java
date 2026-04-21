package model;

import java.util.ArrayList;

public class Scenario {

    private String name;
    private QualityType qualityType;
    private Mode mode;
    private ArrayList<Dimension> dimensions;

    public Scenario(String name, QualityType qualityType, Mode mode) {

        this.name = name;
        this.qualityType = qualityType;
        this.mode = mode;
        this.dimensions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public QualityType getQualityType() {
        return qualityType;
    }

    public Mode getMode() {
        return mode;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }
}
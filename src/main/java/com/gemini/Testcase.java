package com.gemini;

import java.util.List;

public class Testcase {

    String sno;
    String scenarioName;
    List<String> steps;
    static String featureName;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public static String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        Testcase.featureName = featureName;
    }

    public void clear() {
        setSno(null);
        setSteps(null);
        setScenarioName(null);
        setFeatureName(null);
    }

    @Override
    public String toString() {
        return "Testcase{" +
                "sno='" + sno + '\'' +
                ", scenarioName='" + scenarioName + '\'' +
                ", steps=" + steps +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}

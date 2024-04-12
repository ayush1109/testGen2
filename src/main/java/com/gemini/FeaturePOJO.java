package com.gemini;

import java.util.List;

public class FeaturePOJO {

    String featureName;

    List<Testcase> testcase;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public List<Testcase> getTestcase() {
        return testcase;
    }

    public void setTestcase(List<Testcase> testcase) {
        this.testcase = testcase;
    }

    @Override
    public String toString() {
        return "FeaturePOJO{" +
                "featureName='" + featureName + '\'' +
                ", testcase=" + testcase +
                '}';
    }
}

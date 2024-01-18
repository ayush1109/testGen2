package com.gemini;

import java.util.ArrayList;
import java.util.HashMap;

public class LocatorPOJO {
     static HashMap<String, ArrayList<HashMap<String, String>>> features = new HashMap<>();

    public static HashMap<String, ArrayList<HashMap<String, String>>> getFeatures() {
        return features;
    }

    public static void setFeatures(HashMap<String, ArrayList<HashMap<String, String>>> features) {
        LocatorPOJO.features = features;
    }
}

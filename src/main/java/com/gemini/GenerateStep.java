package com.gemini;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class GenerateStep {
    public static String generate(HashMap<String, String> tokenMap) {

        String step = "for the <page> page, user <action> on <element> <information>";

        for (Map.Entry<String, String> entry : tokenMap.entrySet()) {

            switch (entry.getKey()) {
                case "AIK" -> step = step.replaceAll("<information>", entry.getValue());
                case "A" -> {
                    switch (tokenMap.get("AIK")) {
                        case "button" -> step = step.replaceAll("<action>", "clicks");
                        case "input" -> step = step.replaceAll("<action>", "enters");
                        case "dropdown", "radio button", "checkbox" -> step = step.replaceAll("<action>", "selects");
                    }
                }
                case "AIN" -> step = step.replaceAll("<element>", entry.getValue());
            }
        }
        return step;
    }
}

package com.gemini;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.gemini.SeleniumActions.getFeatureNameFromUrl;

public class GenerateStep {
    public static String generate(HashMap<String, String> tokenMap, String keyword) {
        try {
//            String url = Utils.readProperties("url");
//            final String page = url.split("\\.")[0].split("//")[1];

            String url = getFeatureNameFromUrl();
            if (StringUtils.equalsIgnoreCase(keyword, "when")) {

                String step = keyword + " for the <page> page, user <action> on <element> <information>";
                step = step.replace("<page>", url);

                for (Map.Entry<String, String> entry : tokenMap.entrySet()) {

                    switch (entry.getKey()) {
                        case "A" -> {
                            switch (tokenMap.get("A")) {
                                case "enter", "input", "type", "enters" -> step = step.replace("<action>", "enters");
                                case "click" -> step = step.replace("<action>", "clicks");
                            }
                        }
                        case "AIK" -> {
                            switch (tokenMap.get("AIK")) {
                                case "button", "option", "tab" -> {
                                    step = step.replace("<action>", "clicks");
                                    step = step.replace("<information>", "button");
                                }
                                case "input", "field" -> {
                                    step = step.replace("<action>", "enters");
                                    step = step.replace("<action>", "enters \"(.*?)\"");
                                }
                                case "dropdown", "radio button", "checkbox" ->
                                        step = step.replace("<action>", "selects");
                            }
                            step = step.replace("<information>", entry.getValue());
                        }
                        case "AIN" -> {
                            step = step.replace("<element>", entry.getValue().trim().replace(" ", "_"));
                            if(tokenMap.get("A").equals("enters") || tokenMap.get("A").equals("enter") || tokenMap.get("A").equals("write")) {
                                step = step.replace("<action>", "enters as \"" + tokenMap.get("DATA") + "\"");
                            }
                        }
                        default -> step = step.replace("<information>", "element");
                    }
                }
                step = step.replace("<page>", CustomPOSTagger.pageName);
                return step;
            } else {
                if (tokenMap.containsKey("AF")) {
                    String step = keyword + " for the <page> page, user verifies <data> is the text of <element> <information>";
                    step = step.replace("<page>", url);
                    for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
                        switch (entry.getKey()) {
                            case "AIK" -> {
                                switch (tokenMap.get("AIK")) {
                                    case "button" -> {
                                        step = step.replace("<information>", "button");
                                    }
                                    case "dropdown" -> {
                                        step = step.replace("<information>", "dropdown");
                                        break;
                                    }
                                    case "radio button" -> {
                                        step = step.replace("<information>", "radio button");
                                        break;
                                    }
                                    case "checkbox" -> {
                                        step = step.replace("<information>", "checkbox");
                                        break;
                                    }
                                }
                                break;
//                            step = step.replace("<information>", entry.getValue());
                            }
                            case "AIN" -> {
                                step = step.replace("<element>", tokenMap.get("AIN").trim().replace(" ", "_"));
                                break;
                            }
                            case "DATA" -> {
                                step = step.replace("<data>", tokenMap.get("DATA"));
                                break;
                            }
                        }

                    }
                    return step;
                } else {
                    String step = keyword + " for the <page> page, user verifies <element> <information> is <subaction>";
                    step = step.replace("<page>", url);
                    for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
                        switch (entry.getKey()) {
                            case "AIK" -> {
                                switch (tokenMap.get("AIK")) {
                                    case "button" -> step = step.replace("<information>", "button");
                                    case "dropdown" -> step = step.replace("<information>", "dropdown");
                                    case "radio button" -> step = step.replace("<information>", "radio button");
                                    case "checkbox" -> step = step.replace("<information>", "checkbox");
                                }
//                            step = step.replace("<information>", entry.getValue());
                            }
                            case "AIN" -> {
                                step = step.replace("<element>", tokenMap.get("AIN").trim().replace(" ", "_"));
                            }
                            case "SA" -> {
                                step = step.replace("<subaction>", tokenMap.get("SA"));
                            }
                            default -> step = step.replace("<information>", "element");
                        }
                    }
                    return step;
                }
            }


        } catch (NullPointerException e) {
            String step = "Given user navigates to <url>";

            for (Map.Entry<String, String> entry : tokenMap.entrySet()) {

                if ("AD".equals(entry.getKey())) {
                    step = step.replace("<url>", entry.getValue());
                    CustomPOSTagger.pageName = entry.getValue();
                }
            }
            return step;
        }

    }

    public static String identifyAction(HashMap<String, String> tokenMap) {
        String action = null;
        try {
            switch (tokenMap.get("AIK")) {
                case "button", "tab", "option" -> action = "click";
                case "input", "field", "type" -> action = "input";
                case "dropdown", "radio button", "checkbox" -> action = "select";
            }
            return action;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
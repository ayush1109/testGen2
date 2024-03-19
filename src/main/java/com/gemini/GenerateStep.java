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
                            String finalAction = tokenMap.get("A");
                            String action = finalAction;
                            if (Utils.clickCommands.stream().anyMatch(e -> e.equalsIgnoreCase(finalAction)))
                                action = "click";
                            else if (Utils.typeCommands.stream().anyMatch(e -> e.equalsIgnoreCase(finalAction)))
                                action = "input";
                            switch (action) {
                                case "input" -> {
                                    step = keyword + " for the <page> page, user <action> \"<data>\" as <information> for <element>";
                                    step = step.replace("<page>", url);
                                    step = step.replace("<action>", "enters");
                                    step = step.replace("<information>", "input");
                                    step = step.replace("<data>", tokenMap.get("DATA"));
                                }
                                case "click" -> {
                                    step = step.replace("<action>", "clicks");
                                    step = step.replace("<information>", "button");
                                }
                            }
                        }
                        case "AIK" -> {
                            switch (tokenMap.get("AIK")) {
                                case "button", "option", "tab" -> {
                                    step = step.replace("<action>", "clicks");
                                    step = step.replace("<information>", "button");
                                }
                                case "input", "field" -> {
                                    step = keyword + " for the <page> page, user <action> \"<data>\" as <information> for <element>";
                                    step = step.replace("<page>", url);
                                    step = step.replace("<action>", "enters");
                                    step = step.replace("<information>", "input");
                                    step = step.replace("<data>", tokenMap.get("DATA"));
                                }
                                case "dropdown", "radio button", "checkbox" ->
                                        step = step.replace("<action>", "selects");
                            }
                            step = step.replace("<information>", entry.getValue());
                        }
                        case "AIN" -> {
                            step = step.replace("<element>", entry.getValue().trim().replace(" .", "").replace(" ?", "").replace(" ", "_"));
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
                                    case "div" -> step = step.replace("<information>", "div");
                                }
//                            step = step.replace("<information>", entry.getValue());
                            }
                            case "AIN" -> {
                                step = step.replace("<element>", entry.getValue().trim().replace(" .", "").replace(" ?", "").replace(" ", "_"));
                            }
                            case "SA" -> {
                                step = step.replace("<subaction>", tokenMap.get("SA"));
                                if (tokenMap.get("SA").equalsIgnoreCase("present"))
                                    step = step.replace("present", "visible");
                                step = step.replace("Present", "visible");
                            }
                            default -> step = step.replace("<information>", "div");
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
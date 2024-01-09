package com.gemini;

import java.util.ArrayList;
import java.util.List;

public class trainData {
    static void sss(String data) {
        String[] arr = data.split(" ");

        List<String> datass = new ArrayList<>();

        String sentence = arr[0] + "_A ";
        sentence += arr[1] + "_IN ";
        sentence += arr[2] + "_DT ";
        sentence += arr[3] + " " + arr[4] + "_AIN ";
        sentence += arr[5] + "_AIK ";

        datass.add(sentence.replace("gmail", "gmail"));
        datass.add(sentence.replace("gmail", "yahoo"));
        datass.add(sentence.replace("gmail", "apply"));
        datass.add(sentence.replace("gmail", "login"));
        datass.add(sentence.replace("gmail", "signup"));
        datass.add(sentence.replace("gmail", "policy"));
        datass.add(sentence.replace("gmail", "edit"));
        datass.add(sentence.replace("gmail", "close"));
        datass.add(sentence.replace("gmail", "action"));

        datass.forEach(System.out::println);

    }
    public static void main(String[] args) {
        sss("click on the leave management option");

    }
}

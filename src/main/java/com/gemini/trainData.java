package com.gemini;

import java.util.ArrayList;
import java.util.List;

public class trainData {
    static void sss(String data) {
        String[] arr = data.split(" ");

        List<String> datass = new ArrayList<>();

        String sentence = arr[0] + "_MISC ";
        sentence += arr[1] + "_A ";
        sentence += arr[2] + "_DT ";
        sentence += arr[3] + "_AIN ";
        sentence += arr[4] + "_DATA ";

        datass.add(sentence.replace("username", "username"));
        datass.add(sentence.replace("username", "reason"));
        datass.add(sentence.replace("username", "password"));
        datass.add(sentence.replace("username", "phone"));
        datass.add(sentence.replace("username", "firstName"));
        datass.add(sentence.replace("username", "lastName"));
        datass.add(sentence.replace("username", "pincode"));
        datass.add(sentence.replace("username", "address"));
        datass.add(sentence.replace("username", "state"));

        datass.forEach(System.out::println);

    }
    public static void main(String[] args) {
        sss("user inputs the username ayush");

    }
}

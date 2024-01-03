package org.example;

public class TokenWithTag {
    String token;
    String tag;
    double probability;

    TokenWithTag(String token, String tag, double probability) {
        this.token = token;
        this.tag = tag;
        this.probability = probability;
    }
}
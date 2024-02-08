package com.gemini;

import java.util.HashMap;
import java.util.List;

public class MapTokenToKeys {
    public static HashMap<String, String> map(List<TokenWithTag> result) {
        HashMap<String, String> tokenToKeys = new HashMap<>();
        StringBuilder AIN = new StringBuilder();

        for (TokenWithTag tokenWithTag : result) {

            if (tokenWithTag.tag.equals("AIN")) {
                AIN.append(tokenWithTag.token);
                AIN.append(" ");
            }
            if (tokenWithTag.tag.equals("AIN")) tokenToKeys.put(tokenWithTag.tag, AIN.toString());
            else
                tokenToKeys.put(tokenWithTag.tag, tokenWithTag.token);

        }
        return tokenToKeys;

    }
}

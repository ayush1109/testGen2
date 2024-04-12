package com.gemini;

import java.util.HashMap;
import java.util.List;

public class MapTokenToKeys {
    public static HashMap<String, String> map(List<TokenWithTag> result) {
        HashMap<String, String> tokenToKeys = new HashMap<>();
        StringBuilder AIN = new StringBuilder();
        StringBuilder DATA = new StringBuilder();

        for (TokenWithTag tokenWithTag : result) {

            if (tokenWithTag.tag.equals("AIN")) {
                AIN.append(tokenWithTag.token);
                AIN.append(" ");
            }
            if (tokenWithTag.tag.equals("AIN")) tokenToKeys.put(tokenWithTag.tag, AIN.toString());

            if (tokenWithTag.tag.equals("DATA")) {
                DATA.append(tokenWithTag.token);
                DATA.append(" ");
            }
            if (tokenWithTag.tag.equals("DATA")) tokenToKeys.put(tokenWithTag.tag, DATA.toString());
            else {
                if (!tokenWithTag.tag.equals("AIN"))
                    tokenToKeys.put(tokenWithTag.tag, tokenWithTag.token);
            }

        }
        return tokenToKeys;

    }
}

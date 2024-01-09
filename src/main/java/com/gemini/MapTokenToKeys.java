package com.gemini;

import java.util.HashMap;
import java.util.List;

public class MapTokenToKeys {
    public static HashMap<String, String> map(List<TokenWithTag> result) {
        HashMap<String, String> tokenToKeys = new HashMap<>();

        for (TokenWithTag tokenWithTag : result) {
            tokenToKeys.put(tokenWithTag.tag, tokenWithTag.token);
        }

        return tokenToKeys;

    }
}

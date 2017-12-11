package com.sample.jcaptcha.service;

import java.util.HashMap;
import java.util.Map;

public class WordMap {

    private static Map<String, String> wordsMap = new HashMap<>();

    public static Map<String, String> getWordsMap() {
        return wordsMap;
    }

}

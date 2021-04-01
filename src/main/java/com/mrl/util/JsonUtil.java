package com.mrl.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    static Gson gson = new GsonBuilder()
            // .registerTypeAdapter(Date.class, new DateTypeAdapter())
            // .setPrettyPrinting()
            .create();

    public static String toJsonString(Object src) {
        return gson.toJson(src);
    }
}

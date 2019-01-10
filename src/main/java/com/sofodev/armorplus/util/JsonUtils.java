/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Arrays;
import java.util.Map;

public class JsonUtils {

    public static JsonArray addArray(String propertyName, JsonElement element) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(addProperty(propertyName, element));
        return jsonArray;
    }

    public static JsonArray addArray(Map<String, JsonElement> properties) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(addProperty(properties));
        return jsonArray;
    }

    public static JsonArray addArray(JsonObject jsonObject) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    public static JsonArray addArray(JsonObject... jsonObjects) {
        JsonArray jsonArray = new JsonArray();
        Arrays.stream(jsonObjects).forEach(jsonArray::add);
        return jsonArray;
    }

    public static JsonObject addProperty(String propertyName, JsonElement element) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(propertyName, element);
        return jsonObject;
    }

    public static JsonObject addProperty(String propertyName, Object element) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(propertyName, getPrimitive(element));
        return jsonObject;
    }

    public static JsonObject addProperty(Map<String, JsonElement> properties) {
        JsonObject jsonObject = new JsonObject();
        properties.forEach(jsonObject::add);
        return jsonObject;
    }

    public static JsonElement getPrimitive(Object object) {
        if (object instanceof Boolean) {
            return new JsonPrimitive((Boolean) object);
        } else if (object instanceof Number) {
            return new JsonPrimitive((Number) object);
        } else if (object instanceof String) {
            return new JsonPrimitive((String) object);
        } else if (object instanceof Character) {
            return new JsonPrimitive((Character) object);
        }
        return new JsonObject();
    }

}

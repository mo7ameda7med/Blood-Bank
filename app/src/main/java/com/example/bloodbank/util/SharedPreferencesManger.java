package com.example.bloodbank.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    private static String LANG = "LANG";
    public static final String UserData = "UserData";



    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", Context.MODE_PRIVATE);
        }
    }

    public static void saveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void saveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.apply();
        }
    }

    public static String loadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean loadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);

        return sharedPreferences.getBoolean(data_Key, false);
    }

//    public static Favorites loadUserData(Activity activity, String data_Key) {
//        Favorites favorites = null;
//
//        Gson gson = new Gson();
//        favorites = gson.fromJson(loadData(activity, data_Key), Favorites.class);
//
//        if (favorites == null) {
//            favorites = new Favorites();
//        }
//
//        return favorites;
//    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }

    public static void saveLang(Activity activity, String lang) {
        saveData(activity, LANG, lang);
    }

    public static String loadLang(Activity activity) {
        setSharedPreferences(activity);

        return sharedPreferences.getString(LANG, "en");
    }
    public static void saveState(Activity activity ,boolean isFavourite) {
        setSharedPreferences(activity);
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            editor.putBoolean("State", isFavourite);
            editor.commit();
        }
    }

    public static boolean readState() {
            return sharedPreferences.getBoolean(LANG, true);
    }
}

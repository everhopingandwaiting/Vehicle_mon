package com.example.john.global;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by john on 15-10-1.
 */
public class Aging_test
{

    private static final long validityTime = 1000 * 60 * 60 * 24 * 3;
    private static String lastLoginDate = "lastLoginDate";
    private static String userSharePref = "user_cache";
    private static String tokenCache = "tokenCache";
    private static String userName = "userName";
    static Aging_test agingTest;
    public  String token;
    public String username;

    public String getToken() {
        return token;
    }

    public static Aging_test getAgingTest(Context context) {
        if (agingTest == null) {
            agingTest = Init_aging(context);
        }
        return agingTest;
    }

    private static Aging_test Init_aging(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(userSharePref, Context.MODE_PRIVATE);
        Aging_test aging_test = new Aging_test();
        if (preferences != null) {
            if (isTokenValidity(preferences)) {
                aging_test.token = preferences.getString(tokenCache, null);
                aging_test.username = preferences.getString(userName, null);
            } else {
                logout(context);
            }
        }
        return aging_test;
    }

    public static void logout(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(userSharePref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        agingTest = null;
    }

    private static boolean isTokenValidity(SharedPreferences preferences) {

        if (preferences != null && preferences.contains(lastLoginDate)) {

            long lastLoginMillis = Long.valueOf(preferences.getString(lastLoginDate, "0"));
            long currentMillis = System.currentTimeMillis();
            if (currentMillis - lastLoginMillis <= validityTime) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void loginSucceed(String token, String uname, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(userSharePref, Context.MODE_PRIVATE);
        preferences.edit().putString(tokenCache, token)
                .putString(lastLoginDate, String.valueOf(System.currentTimeMillis()))
        .putString(userName,uname).apply();
        this.token = token;
        this.username = uname;

    }
}

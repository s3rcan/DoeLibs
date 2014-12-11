package com.groupd2.doelibs.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class TokenHelper {

	
	public static String getLevel(Context context){
		SharedPreferences storage = context.getSharedPreferences("data", 0);

		String level = storage.getString("level", "Borrower");
		return level;
	}
	public static void setLevel(String level, Context context){
		SharedPreferences.Editor storage = context.getSharedPreferences("data",
				0).edit();

		storage.putString("level",level);
		storage.commit();

	}
	
	public static String getToken(Context context) {
		SharedPreferences storage = context.getSharedPreferences("data", 0);

		String token = storage.getString("token", null);

		return token;
	}

	
	public static void setToken(Context context, String token) {
		SharedPreferences.Editor storage = context.getSharedPreferences("data",
				0).edit();

		storage.putString("token", token);
		storage.commit();

	}

	public static void removeToken(Context context) {
		SharedPreferences.Editor storage = context.getSharedPreferences("data",
				0).edit();
		storage.remove("token");

		storage.commit();
	}
}
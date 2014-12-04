package com.groupd2.doelibs.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class TokenHelper {

	private static String m_level;
	
	public static String getLevel(){
		return m_level;
	}
	public static void setLevel(String level){
		m_level = level;
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
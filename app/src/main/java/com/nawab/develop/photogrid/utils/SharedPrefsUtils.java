package com.nawab.develop.photogrid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Utility class to store/retrieve values in SharedPreferences.
 * 
 * @author sachin.gupta
 */
public class SharedPrefsUtils {

	private SharedPreferences _sharedPref;

	public SharedPrefsUtils(Context pContext, String pFileName) {
		if (_sharedPref == null) {
			_sharedPref = pContext.getSharedPreferences(pFileName,
					Context.MODE_PRIVATE);
		}
	}

	public boolean getSharedPrefBoolean(String pKey, boolean pDefault) {

		return _sharedPref.getBoolean(pKey, pDefault);
	}
	public void setSharedPrefBoolean(String pKey, boolean pValue) {
		Editor _editor = _sharedPref.edit();
		_editor.putBoolean(pKey, pValue);
		_editor.apply();
	}
	public int getSharedPrefInt(String pKey, int pDefault) {
		return _sharedPref.getInt(pKey, pDefault);
	}

	public void setSharedPrefInt(String pKey, int pValue) {
		Editor _editor = _sharedPref.edit();
		_editor.putInt(pKey, pValue);
		_editor.apply();
	}

	public String getSharedPrefString(String pKey, String pDefault) {
		return _sharedPref.getString(pKey, pDefault);
	}

	public void setSharedPrefString(String pKey, String pValue) {
		Editor _editor = _sharedPref.edit();
		_editor.putString(pKey, pValue);
		_editor.apply();
	}

	public long getSharedPrefLong(String pKey, long pDefault) {
		return _sharedPref.getLong(pKey, pDefault);
	}

	public void setSharedPrefLong(String pKey, long pValue) {
		Editor _editor = _sharedPref.edit();
		_editor.putLong(pKey, pValue);
		_editor.apply();
	}
	public float getSharedPrefFloat(String pKey, float pDefault) {
		return _sharedPref.getFloat(pKey, pDefault);
	}

	public void setSharedPrefFloat(String pKey, float pValue) {
		Editor _editor = _sharedPref.edit();
		_editor.putFloat(pKey, pValue);
		_editor.apply();
	}

	public void clearSharedPref() {
		Editor _editor = _sharedPref.edit();
		_editor.clear();
		_editor.apply();
	}
}

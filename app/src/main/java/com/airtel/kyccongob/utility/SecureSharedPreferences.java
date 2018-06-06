package com.airtel.kyccongob.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.airtel.kyccongob.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Developer: Rishabh Dutt Sharma
 * <p/>
 * Organization: Airtel [A1UGPQS2]
 * Dated: 4/20/2018.
 */
public class SecureSharedPreferences implements SharedPreferences {

    private static final Gson GSON = new Gson();
    private static SecureSharedPreferences mInstance;

    private final SharedPreferences mPreferences;

    private SecureSharedPreferences(Context context) {
        this.mPreferences = context.getSharedPreferences(BuildConfig.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getInstance(Context context) {
        if (mInstance == null) mInstance = new SecureSharedPreferences(context);
        return mInstance;
    }

    private <T> String getEncryptedVal(T rawVal) {
        String encryptable = String.valueOf(rawVal);
//        return AesUtil.getInstance().encrypt(encryptable);
        return"";
    }

    private String getDecryptedVal(String encVal) {
//        return AesUtil.getInstance().decrypt(encVal);
        return"";
    }

    private String getUsableValFromKey(String key, String defValue) {
        String encryptedKey = getEncryptedVal(key);
        String encryptedDefVal = getEncryptedVal(defValue);
        String encryptedVal = mPreferences.getString(encryptedKey, encryptedDefVal);
        return getDecryptedVal(encryptedVal);
    }

    @Override
    public Map<String, ?> getAll() {
        Map<String, String> usableAll = new LinkedTreeMap<>();
        Map<String, ?> all = mPreferences.getAll();
        for (Map.Entry<String, ?> entry : all.entrySet())
            usableAll.put(getDecryptedVal(entry.getKey()), getDecryptedVal(String.valueOf(entry.getValue())));
        return usableAll;
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return getUsableValFromKey(key, defValue);
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        String usableValFromKey = getUsableValFromKey(key, GSON.toJson(defValues));
        return GSON.fromJson(usableValFromKey, new TypeToken<TreeSet<String>>() {
        }.getType());
    }

    @Override
    public int getInt(String key, int defValue) {
        return toInt(getUsableValFromKey(key, String.valueOf(defValue)), defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return toLong(getUsableValFromKey(key, String.valueOf(defValue)), defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return toFloat(getUsableValFromKey(key, String.valueOf(defValue)), defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return toBoolean(getUsableValFromKey(key, String.valueOf(defValue)), defValue);
    }

    @Override
    public boolean contains(String key) {
        return mPreferences.contains(getEncryptedVal(key));
    }

    @Override
    public Editor edit() {
        return new SecureEditor(mPreferences);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mInstance.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        mInstance.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private int toInt(String val, int defVal) {
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defVal;
        }
    }

    private long toLong(String val, long defVal) {
        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defVal;
        }
    }

    private double toDouble(String val, long defVal) {
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defVal;
        }
    }

    private float toFloat(String val, float defVal) {
        try {
            return Float.parseFloat(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defVal;
        }
    }

    private boolean toBoolean(String val, boolean defVal) {
        try {
            return Boolean.parseBoolean(val);
        } catch (Exception e) {
            e.printStackTrace();
            return defVal;
        }
    }

    private class SecureEditor implements Editor {

        private final Editor mEditor;

        @SuppressLint("CommitPrefEdits")
        SecureEditor(SharedPreferences preferences) {
            this.mEditor = preferences.edit();
        }

        @Override
        public Editor putString(String key, @Nullable String value) {
            return mEditor.putString(getEncryptedVal(key), getEncryptedVal(value));
        }

        @Override
        public Editor putStringSet(String key, @Nullable Set<String> values) {
            return putString(key, GSON.toJson(values));
        }

        @Override
        public Editor putInt(String key, int value) {
            return putString(key, String.valueOf(value));
        }

        @Override
        public Editor putLong(String key, long value) {
            return putString(key, String.valueOf(value));
        }

        @Override
        public Editor putFloat(String key, float value) {
            return putString(key, String.valueOf(value));
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            return putString(key, String.valueOf(value));
        }

        @Override
        public Editor remove(String key) {
            return mEditor.remove(key);
        }

        @Override
        public Editor clear() {
            return mEditor.clear();
        }

        @Override
        public boolean commit() {
            return mEditor.commit();
        }

        @Override
        public void apply() {
            mEditor.apply();
        }
    }
}

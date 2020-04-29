package com.example.vintec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "JApps_Session";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_FIRSTTIME_CALL="IsFirsttimeCall";
    private static final String IS_FIRSTTIME_CAMERA="IsFirsttimeCamera";
//	private static final String IS_FIRSTTIME_STORAGE="IsFirsttimeStorage";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_UPGRADE="upgrade";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DIVN= "DIVN";
    public static final String KEY_DIVNM = "DIVNM";
    public static final String KEY_LOCCD = "LOCCD";
    public static final String KEY_LOCNM= "LOCNM";
    public static final String KEY_DEPTCD = "DEPTCD";
    public static final String KEY_DEPTNM = "DEPTNM";
    public static final String KEY_LOGIN_NAME="LOGNAME";
    public static final String KEY_SCHEMA="SCHEMA";
    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }


    public void createCallPermissionSession(boolean flag)
    {
        editor.putBoolean(IS_FIRSTTIME_CALL,flag);
        editor.commit();

    }

    public void createCameraPermissionSession(boolean flag)
    {
        editor.putBoolean(IS_FIRSTTIME_CAMERA,flag);
        editor.commit();

    }

    //	public void createStoragePermissionSession(boolean flag)
//	{
//		editor.putBoolean(IS_FIRSTTIME_STORAGE,flag);
//		editor.commit();
//
//	}
    public void setPersonalInfo(String divn,String divnm,String loccd,String locnm,String deptcd,String deptnm)
    {
        editor.putString(KEY_DIVN,divn);
        editor.putString(KEY_DIVNM,divnm);
        editor.putString(KEY_LOCCD,loccd);
        editor.putString(KEY_LOCNM,locnm);
        editor.putString(KEY_DEPTCD,deptcd);
        editor.putString(KEY_DEPTNM,deptnm);
        editor.commit();


    }

    public void setLoginName(String name)
    {

        editor.putString(KEY_LOGIN_NAME,name);
        editor.commit();
    }

    public void setSchema(String schema)
    {
        editor.putString(KEY_SCHEMA,schema);
        editor.commit();
    }
    //

    public void upgradeSession(String isupgrade)
    {

        editor.putString(KEY_UPGRADE,isupgrade);
        editor.commit();


    }

    public String getUpgradeDetail()
    {
        return pref.getString(KEY_UPGRADE,"NA");

    }

    /**
     * Check login method wil check user login status If false it will redirect
     * user to login page Else won't do anything
     * */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_DIVN, pref.getString(KEY_DIVN, null));
        user.put(KEY_DIVNM, pref.getString(KEY_DIVNM, null));
        user.put(KEY_LOCCD, pref.getString(KEY_LOCCD, null));
        user.put(KEY_LOCNM, pref.getString(KEY_LOCNM, null));
        user.put(KEY_DEPTCD, pref.getString(KEY_DEPTCD, null));
        user.put(KEY_DEPTNM, pref.getString(KEY_DEPTNM, null));

        // return user
        return user;
    }

    public String getLoginName()
    {

        return	pref.getString(KEY_LOGIN_NAME,null);
    }

    public String getSchema()
    {

        return	pref.getString(KEY_SCHEMA,null);
    }
    public String getLoginId()
    {
        return pref.getString(KEY_NAME,null);
    }
    /**
     * Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
        );

        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
        //				| IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isFirstTimeCall()
    {

        return pref.getBoolean(IS_FIRSTTIME_CALL,true);
    }

    public boolean isFirstTimeCamera()
    {

        return pref.getBoolean(IS_FIRSTTIME_CAMERA,true);
    }

//	public boolean isFirstTimeStorage()
//	{
//
//		return pref.getBoolean(IS_FIRSTTIME_STORAGE,true);
//	}
}


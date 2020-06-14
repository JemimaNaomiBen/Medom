package api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private final String INTRO = "intro";
    private  final String Email = "email";
    private final String PassWord = "password";
    private SharedPreferences app_prefs;
    private Context context;
    public PreferenceHelper(Context context){
        app_prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        this.context =context;
    }
    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }
    public void PutEmail(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Email,loginorout);
        edit.commit();
    }
    public String getEmail(){
        return app_prefs.getString(Email, "");
    }
    public void PutPassword(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PassWord,loginorout);
        edit.commit();
    }
    public String getPassword(){
        return app_prefs.getString(PassWord, "");
    }
}

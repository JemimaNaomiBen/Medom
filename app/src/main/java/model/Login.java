package model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    String password;


    public Login (String email,String password) {
        this.email = email;
        this.password = password;

    }


}

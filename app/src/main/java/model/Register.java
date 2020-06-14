package model;

import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("firstname")
    String firstname;
    @SerializedName("lastname")
    String lastname;
    @SerializedName("othername")
    String othername;

    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public  Register(String firstname,String lastname,String othername,String email,String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.othername = othername;
        this.email = email;
        this.password = password;
    }
}

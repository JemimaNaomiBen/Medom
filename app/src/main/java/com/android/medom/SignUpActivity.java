package com.android.medom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import api.ApiClient;
import api.LoginApiService;
import api.PreferenceHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText etftname,etltname,etothername, etemail, etpassword;
    private Button btnregister;

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        preferenceHelper = new PreferenceHelper(this);

        if (preferenceHelper.getIsLogin()) {
            Intent intent = new Intent(SignUpActivity.this, LandingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }
        etftname = findViewById(R.id.etfirstname);
        etltname = findViewById(R.id.etlastname);
        etothername = findViewById(R.id.etothername);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);

        btnregister = findViewById(R.id.sign_up_button);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerMe();
            }
        });

    }

        private void registerMe(){

            final String firstname = etftname.toString();
            final String lastname = etltname.toString();
            final String othername = etothername.toString();
            final String email = etemail.toString();
            final String password = etpassword.toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginApiService api = retrofit.create(LoginApiService.class);

            Call<String> call = api.createUser(firstname,lastname,othername,email,password);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.i("Responsestring", response.body().toString());
                    //Toast.makeText()
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Log.i("onSuccess", response.body().toString());

                            String jsonresponse = response.body().toString();
                            try {
                                parseRegData(jsonresponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

        private void parseRegData(String response) throws JSONException {

            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("status").equals("true")){

                saveInfo(response);

                Toast.makeText(SignUpActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this,LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }else {

                Toast.makeText(SignUpActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        }

        private void saveInfo(String response){

            preferenceHelper.putIsLogin(true);
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("status").equals("true")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject dataobj = dataArray.getJSONObject(i);
                        preferenceHelper.PutEmail(dataobj.getString("email"));
                        preferenceHelper.PutPassword(dataobj.getString("password"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

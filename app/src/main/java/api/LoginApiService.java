package api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface LoginApiService {

    @POST("/api/auth/login")
    Call <String> UserLogin (
            @Field("email") String email,
            @Field("password") String password );

    @POST("/api/auth/user/Register")
    Call<String> createUser(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("othername") String othername,
            @Field("email") String email,
            @Field("password") String password
    );
}

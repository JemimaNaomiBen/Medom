package api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.POST;

public class ApiClient {
    public  static  final  String Base_URL = "https://medomapi.herokuapp.com/";
 public static Retrofit retrofit = null;
  public  static  Retrofit getClient (){
      if (retrofit == null){
          retrofit  = new  Retrofit.Builder()
                  .baseUrl(Base_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
      }
      return  retrofit;
  }


}

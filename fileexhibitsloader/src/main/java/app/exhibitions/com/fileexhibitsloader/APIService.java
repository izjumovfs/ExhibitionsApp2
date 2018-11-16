package app.exhibitions.com.fileexhibitsloader;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("ios.challenge.json")
    Call<JsonObject> getJson();
}

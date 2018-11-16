package app.exhibitions.com.fileexhibitsloader;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.exhibitions.com.model.Exhibit;
import app.exhibitions.com.model.IExhibitsLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FileExhibitsLoader implements IExhibitsLoader {

    private static String FILENAME = "exhibits.json";
    private Context mContext;

    public FileExhibitsLoader(Context context){
        mContext = context;
    }


    @Override
    public List<Exhibit> getExhibitList() {
        File exhibits = new File(mContext.getCacheDir() + File.separator + FILENAME);
        if (exhibits.exists()){
           return parseJSON(exhibits);
        }
        else{
             return downloadAndSaveJSON(exhibits);
        }
    }

    private ArrayList<Exhibit> downloadAndSaveJSON(File fileToSave){
        ArrayList <Exhibit> exhibits = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/u-android/41ade05b6ae1133e7e86e9dfd55f1794/raw/bab1c383b0384d1a4c889b399ad7b13ae05634fa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<JsonObject> jsonCall = service.getJson();
        try{
            JsonObject obj = jsonCall.execute().body();
            try {
                Writer output = null;
                fileToSave.createNewFile();
                output = new BufferedWriter(new FileWriter(fileToSave));
                output.write(obj.toString());
                output.close();
                return parseJSON(fileToSave);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return exhibits;


    }


    private ArrayList<Exhibit> parseJSON(File json){
        try {
            JsonReader reader = new JsonReader(new FileReader(json.getAbsolutePath()));
            Gson gson = new Gson();
            ExhibitionsHolder exhibitionsHolder = gson.fromJson(reader, ExhibitionsHolder.class);
            return exhibitionsHolder.exhibits;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

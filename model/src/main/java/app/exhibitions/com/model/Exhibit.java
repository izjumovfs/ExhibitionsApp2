package app.exhibitions.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Exhibit {

    @SerializedName("title")
    private String title;
    @SerializedName("images")
    private List<String> images;

    public Exhibit(){

    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setImages(List<String> images){
        this.images = images;
    }


    public String getTitle(){
        return title;
    }

    public ArrayList<String> getImages(){
        return (ArrayList)images;
    }

}

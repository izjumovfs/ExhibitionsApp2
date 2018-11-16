package app.exhibitions.com.fileexhibitsloader;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import app.exhibitions.com.model.Exhibit;

public class ExhibitionsHolder {
    @SerializedName("list")
    public ArrayList<Exhibit> exhibits;
}

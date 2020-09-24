package com.example.optativa1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListPublications {

    @SerializedName("publications")
    @Expose
    private ArrayList<ModelPublications> modelPublications = null;

    public ArrayList<ModelPublications> getModelPublications() {
        return modelPublications;
    }
}

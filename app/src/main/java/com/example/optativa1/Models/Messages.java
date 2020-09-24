package com.example.optativa1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }
}

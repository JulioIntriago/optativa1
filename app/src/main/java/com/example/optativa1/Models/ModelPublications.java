package com.example.optativa1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  ModelPublications {

    @SerializedName("placa")
    @Expose
    private String placa;

    @SerializedName("marca")
    @Expose
    private String marca;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("ano")
    @Expose
    private String ano;

    @SerializedName("motor")
    @Expose
    private String motor;

    @SerializedName("combustible")
    @Expose
    private String combustible;

    @SerializedName("color")
    @Expose
    private String color;





    public String getPlaca() {
        return placa;
    }

    public String getImg() {
        return img;
    }

    public String getAno() {
        return ano;
    }

    public String getMotor() {
        return motor;
    }
    public String getMarca() {
        return marca;
    }
    public String  getColor() {
        return color;
    }
    public String getCombustible() { return combustible;
    }
}

package com.example.optativa1.ConnectDb;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connect {
    //confiracion de conecion
    private static String BASE_URL = "https://vehiculosserver.herokuapp.com/";
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL).client(getClient()).
                addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builderClientHttp = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(69,TimeUnit.SECONDS);
        // Show HTTPS logs in dev mode
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(LEVEL_LOG);
            builderClientHttp.addInterceptor(interceptor);
        }
        return builderClientHttp.build();
    }

    public static Router getApiService(){
        return getRetrofitInstance().create(Router.class);
    }


}

package com.example.optativa1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optativa1.Adapter.AdapterRecycler;
import com.example.optativa1.ConnectDb.Connect;
import com.example.optativa1.ConnectDb.Router;
import com.example.optativa1.Models.ListPublications;
import com.example.optativa1.Models.ModelPublications;
import com.example.optativa1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRecycler adapterRecycler;
    private ArrayList<ModelPublications> modelPublications;
    private Router router;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        router = Connect.getApiService();
        Call<ListPublications>listPublicationsCall= router.obtenerDatos();
        listPublicationsCall.enqueue(new Callback<ListPublications>() {
            @Override
            public void onResponse(Call<ListPublications> call, Response<ListPublications> response) {

                recyclerView = findViewById(R.id.recyclerViewHome);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                modelPublications = response.body().getModelPublications();
                adapterRecycler = new AdapterRecycler(modelPublications, MainActivity.this);
                recyclerView.setAdapter(adapterRecycler);
                recyclerView.setHasFixedSize(true);
            }



            @Override
            public void onFailure(Call<ListPublications> call, Throwable t) {

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflar en menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bookmark:
                startActivity(new Intent(MainActivity.this, RegisterMainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
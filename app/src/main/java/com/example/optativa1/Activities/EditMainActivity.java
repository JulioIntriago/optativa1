package com.example.optativa1.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.optativa1.ConnectDb.Connect;
import com.example.optativa1.ConnectDb.Router;
import com.example.optativa1.Models.CreatePublications;
import com.example.optativa1.Models.Messages;
import com.example.optativa1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditMainActivity extends AppCompatActivity {

    private ImageView perfilImage;
    private EditText descrip, os, tamano;
    private Button btnActualizar;
    private Router router;
    private String img = "https://i.ytimg.com/vi/gTKiau_ic9Q/maxresdefault.jpg";
    private String model ;
    private String ediDes ;
    private String imgPubli ;
    private String editos ;
    private String editTamano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);
        perfilImage = findViewById(R.id.perfilImage);
        descrip = findViewById(R.id.editTextMarca);
        os = findViewById(R.id.color);
        tamano = findViewById(R.id.editdescrip);
        btnActualizar = findViewById(R.id.btnUpdate);
        router = Connect.getApiService();
        //cargar los datos a la pantalla
        obtener();
        Glide.with(this).load(imgPubli).into(perfilImage);
        descrip.setText(ediDes);
        os.setText(editos);
        tamano.setText(editTamano);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (descrip.getText().toString().isEmpty() ||
                        os.getText().toString().isEmpty() ||
                        tamano.getText().toString().isEmpty()){
                    //validar campos
                    Toast.makeText(EditMainActivity.this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
                }else {
                    //enviar los datos
                    CreatePublications createPublications = new CreatePublications(
                            model , descrip.getText().toString().trim(), imgPubli,
                            os.getText().toString());
                    Call<Messages> createPublicationsCall = router.CREATE_PUBLICATIONS_CAL(createPublications);
                    createPublicationsCall.enqueue(new Callback<Messages>() {
                        @Override
                        public void onResponse(Call<Messages> call, Response<Messages> response) {
                            if (response.isSuccessful()){
                                String message = response.body().getMessage();
                                Toast.makeText(EditMainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Messages> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }

    private void obtener(){
        try {
            img = "https://i.ytimg.com/vi/gTKiau_ic9Q/maxresdefault.jpg";
            model = getIntent().getExtras().getString("model");
            ediDes = getIntent().getExtras().getString("description");
            imgPubli = getIntent().getExtras().getString("img");
            editos = getIntent().getExtras().getString("os");
            editTamano = getIntent().getExtras().getString("tamano");
        }catch (Exception e){

        }
    }
}
package com.example.optativa1.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.optativa1.ConnectDb.Connect;
import com.example.optativa1.ConnectDb.Router;
import com.example.optativa1.Models.CreatePublications;
import com.example.optativa1.Models.Messages;
import com.example.optativa1.Models.ModelPublications;
import com.example.optativa1.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMainActivity extends AppCompatActivity {

    private EditText modelo, placa, img, ano, color;
    private ModelPublications modelPublications;
    private Button btnEnviar;
    private Router router;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        modelo = findViewById(R.id.modelo);
        placa = findViewById(R.id.placa);
        ano = findViewById(R.id.tamano2);
        color = findViewById(R.id.color);
        btnEnviar = findViewById(R.id.button);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                router = Connect.getApiService();
                String img = "";
                if (modelo.getText().toString().trim().isEmpty() || placa.getText().toString().trim().isEmpty() ||
                        ano.getText().toString().trim().isEmpty()  || color.getText().toString().trim().isEmpty()){
                    //validar campos
                    Toast.makeText(RegisterMainActivity.this, "not", Toast.LENGTH_SHORT).show();
                }else {
                    //enviar los datos
                    CreatePublications createPublications = new CreatePublications(placa.getText().toString(),modelo.getText().toString(),color.getText().toString(),ano.getText().toString());
                    Call<Messages> createPublicationsCall = router.CREATE_PUBLICATIONS_CAL(createPublications);
                    createPublicationsCall.enqueue(new Callback<Messages>() {
                        @Override
                        public void onResponse(Call<Messages> call, Response<Messages> response) {
                            if (response.isSuccessful()){
                                String message = response.body().getMessage();
                                Toast.makeText(RegisterMainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                modelo.setText("");
                                placa.setText("");
                                ano.setText("");
                                color.setText("");
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
}
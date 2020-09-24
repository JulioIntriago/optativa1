package com.example.optativa1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.optativa1.Activities.EditMainActivity;
import com.example.optativa1.ConnectDb.Connect;
import com.example.optativa1.ConnectDb.Router;
import com.example.optativa1.Models.Messages;
import com.example.optativa1.Models.ModelPublications;
import com.example.optativa1.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    private ArrayList<ModelPublications> publications;
    private Context context;
    private Router router;

    public AdapterRecycler(ArrayList<ModelPublications> publications, Context context) {
        this.publications = publications;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publications, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dim(publications.get(position));
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }


    class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, correo, modelo;
        private ImageView perfil, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            modelo = itemView.findViewById(R.id.modelo);
            nombre = itemView.findViewById(R.id.descrip);
            correo = itemView.findViewById(R.id.os);
            perfil = itemView.findViewById(R.id.profile_image);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //enviar datos a la otra pantalla de editRegister
                    Intent intent = new Intent(context, EditMainActivity.class);
                    intent.putExtra("model", publications.get(position).getAno());
                    intent.putExtra("img",publications.get(position).getImg());
                    intent.putExtra("description",publications.get(position).getMotor());
                    intent.putExtra("os",publications.get(position).getMarca());
                    intent.putExtra("tamano",publications.get(position).getCombustible());
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    router = Connect.getApiService();
                    Toast.makeText(context, "" + publications.get(getAdapterPosition()).getMarca(), Toast.LENGTH_SHORT).show();
                    Call<Messages> messagesCall = router.eliminarPublications(publications.get(getAdapterPosition()).getMarca());
                    messagesCall.enqueue(new Callback<Messages>() {
                        @Override
                        public void onResponse(Call<Messages> call, Response<Messages> response) {
                            Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Messages> call, Throwable t) {

                        }
                    });
                }
            });
        }
        public void dim(ModelPublications modelPublications){
            this.nombre.setText(modelPublications.getAno());
            this.correo.setText(modelPublications.getColor());
            this.modelo.setText((modelPublications.getPlaca()));
            Glide.with(context).load(modelPublications.getImg()).into(perfil);
        }
    }
}

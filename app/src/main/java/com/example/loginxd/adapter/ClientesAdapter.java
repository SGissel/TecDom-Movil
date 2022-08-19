package com.example.loginxd.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginxd.Agregar;
import com.example.loginxd.Editar_registros;
import com.example.loginxd.R;
import com.example.loginxd.model.Clientes;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClientesAdapter extends FirestoreRecyclerAdapter<Clientes,ClientesAdapter.ViewHolder> {
    Activity activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ClientesAdapter(@NonNull FirestoreRecyclerOptions<Clientes> options,Activity activity) {
        super(options);
        this.activity = activity;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Clientes Clientes) {

        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAbsoluteAdapterPosition());
        String id = documentSnapshot.getId();

        viewHolder.Nombre.setText(Clientes.getNombre());
        viewHolder.Modelo.setText(Clientes.getModelo());
        viewHolder.IP.setText(Clientes.getIP());
        viewHolder.Fecha.setText(Clientes.getFecha());
        viewHolder.Telefono.setText(Clientes.getTelefono());

        viewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, Editar_registros.class);
                i.putExtra("id_registros",id);
                activity.startActivity(i);
            }
        });

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_clientes,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Modelo,IP,Fecha,Telefono;
        ImageView editar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NombreCliente);
            Modelo =itemView.findViewById(R.id.ModeloCliente);
            IP = itemView.findViewById(R.id.IPCliente);
            Fecha = itemView.findViewById(R.id.FechaCliente);
            Telefono = itemView.findViewById(R.id.TelefonoCliente);
            editar = itemView.findViewById(R.id.editar);

        }
    }
}
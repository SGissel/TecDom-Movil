package com.example.loginxd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginxd.R;
import com.example.loginxd.model.Clientes;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ClientesAdapter extends FirestoreRecyclerAdapter<Clientes,ClientesAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ClientesAdapter(@NonNull FirestoreRecyclerOptions<Clientes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Clientes Clientes) {
        viewHolder.Nombre.setText(Clientes.getNombre());
        viewHolder.Modelo.setText(Clientes.getModelo());
        viewHolder.IP.setText(Clientes.getIP());
        viewHolder.Fecha.setText(Clientes.getFecha());
        viewHolder.Telefono.setText(Clientes.getTelefono());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_clientes,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Modelo,IP,Fecha,Telefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NombreCliente);
            Modelo =itemView.findViewById(R.id.ModeloCliente);
            IP = itemView.findViewById(R.id.IPCliente);
            Fecha = itemView.findViewById(R.id.FechaCliente);
            Telefono = itemView.findViewById(R.id.TelefonoCliente);
        }
    }
}
package com.example.loginxd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginxd.adapter.ClientesAdapter;
import com.example.loginxd.model.Clientes;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    RecyclerView mRecycler;
    ClientesAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.listaClientes);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("RClientes");
        FirestoreRecyclerOptions<Clientes> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Clientes>().setQuery(query, Clientes.class).build();
        mAdapter = new ClientesAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    public void clickPerfil(View view) {
        startActivity(new Intent(MainActivity2.this, perfil_admin.class));
    }

    public void clickAgregar(View view) {
        startActivity(new Intent(MainActivity2.this, Agregar.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
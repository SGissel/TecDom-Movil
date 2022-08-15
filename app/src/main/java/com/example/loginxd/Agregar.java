package com.example.loginxd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Agregar extends AppCompatActivity {
    Button btnInsertar;
    EditText nombre, modelo, ip, fecha, telefono;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho =  medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.9));

        mFirestore = FirebaseFirestore.getInstance();

        nombre = findViewById(R.id.nombre);
        modelo = findViewById(R.id.modelo);
        ip = findViewById(R.id.ip);
        fecha = findViewById(R.id.fecha);
        telefono = findViewById(R.id.telefono);
        btnInsertar = findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCliente = nombre.getText().toString().trim();
                String modeloCliente = modelo.getText().toString().trim();
                String ipCliente = ip.getText().toString().trim();
                String fechaCliente = fecha.getText().toString().trim();
                String telefonoCliente = telefono.getText().toString().trim();

                if(nombreCliente.isEmpty() && modeloCliente.isEmpty() && ipCliente.isEmpty() && fechaCliente.isEmpty() && telefonoCliente.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los datos",Toast.LENGTH_SHORT).show();
                }else{
                    postName(nombreCliente,modeloCliente,ipCliente,fechaCliente,telefonoCliente);
                }
            }

            private void postName(String nombreCliente, String modeloCliente, String ipCliente, String fechaCliente, String telefonoCliente) {
                Map<String,Object> map = new HashMap<>();
                map.put("Nombre",nombreCliente);
                map.put("Modelo",modeloCliente);
                map.put("IP",ipCliente);
                map.put("Fecha",fechaCliente);
                map.put("Telefono",telefonoCliente);

                mFirestore.collection("RClientes").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Datos insertados exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al insertar los datos", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
package com.example.loginxd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Editar_registros extends AppCompatActivity {
    EditText nombre_e,telefono_e,ip_e;
    Button Actualizar;
    ImageView foto, Eliminar;
    private String registrosID;
    private FirebaseFirestore mFire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_registros);
        nombre_e = findViewById(R.id.nombre_e);
        telefono_e = findViewById(R.id.telefono_e);
        ip_e = findViewById(R.id.ip_e);
        foto = findViewById(R.id.foto);
        Eliminar = findViewById(R.id.btnEliminar);
        Actualizar = findViewById(R.id.btnActualizar);

        mFire = FirebaseFirestore.getInstance();
        registrosID = getIntent().getStringExtra("id_registros");

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombre_e.getText().toString();
                nombre_e.setText("");
                DeleteData(nombre);
            }
        });

        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
        obtener_datos();
    }

    private void DeleteData(String nombre) {
        mFire.collection("RClientes").whereEqualTo("Nombre",nombre).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String document = documentSnapshot.getId();
                    mFire.collection("RClientes").document(registrosID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Editar_registros.this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Editar_registros.this, "Error al eliminar los datos", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(Editar_registros.this, "ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void obtener_datos(){
        mFire.collection("RClientes").document(registrosID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String nombre = documentSnapshot.getString("Nombre");
                    String telefono = documentSnapshot.getString("Telefono");
                    String ip = documentSnapshot.getString("IP");

                    nombre_e.setText(nombre);
                    telefono_e.setText(telefono);
                    ip_e.setText(ip);
                }
            }
        });
    }
    private void actualizar(){
        String nombre = nombre_e.getText().toString();
        String telefono = telefono_e.getText().toString();
        String ip = ip_e.getText().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("Nombre",nombre);
        map.put("Telefono",telefono);
        map.put("IP",ip);
        mFire.collection("RClientes").document(registrosID).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Editar_registros.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Editar_registros.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Regresar(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity2.class));//
        finish();
    }
}
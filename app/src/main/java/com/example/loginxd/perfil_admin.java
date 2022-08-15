package com.example.loginxd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class perfil_admin extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_admin);
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho =  medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.6));
        initViews();
    }
    private void initViews(){
        mAuth = FirebaseAuth.getInstance();
    }


    public void clickSalir(View view) {
        mAuth.signOut();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
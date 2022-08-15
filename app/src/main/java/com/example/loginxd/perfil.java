package com.example.loginxd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class perfil extends AppCompatActivity {
    TextView email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        DisplayMetrics medidasVentanas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentanas);

        int ancho =  medidasVentanas.widthPixels;
        int alto = medidasVentanas.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto * 0.5));
        initViews();
    }
    private void initViews(){
        mAuth = FirebaseAuth.getInstance();
        email = (TextView)findViewById(R.id.txtemail);
        FirebaseUser user = mAuth.getCurrentUser();
        email.setText(user.getEmail());

    }
}
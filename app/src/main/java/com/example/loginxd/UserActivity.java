package com.example.loginxd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initViews();
    }
    private void initViews(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void clickCerrar(View view) {
        mAuth.signOut();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

    public void clickPerfil(View view) {
        startActivity(new Intent(UserActivity.this, perfil.class));

    }

    public void basic(View view) {
        startActivity(new Intent(UserActivity.this, Basic.class));
    }

    public void multi(View view) {
        startActivity(new Intent(UserActivity.this, Multi.class));
    }

    public void Luxury(View view) {
        startActivity(new Intent(UserActivity.this, Luxury.class));
    }

    public void Otros(View view) {
        startActivity(new Intent(UserActivity.this, Otros.class));
    }
}
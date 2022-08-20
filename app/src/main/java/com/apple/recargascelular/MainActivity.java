package com.apple.recargascelular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.apple.recargascelular.databinding.ActivityMainBinding;
import com.apple.recargascelular.ui.Personas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static ArrayList<Personas> personas =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        goSignUp();
        goLogin();
    }
    public void goLogin(){
        binding.btnLogin.setOnClickListener(view -> {
            Intent i=new Intent(this, LoginActivity.class);
            startActivity(i);
        });
    }
    public void goSignUp(){
        binding.btnSignUp.setOnClickListener(view -> {
            Intent i=new Intent(this,SignUpActivity.class);
            startActivity(i);
        });
    }
}
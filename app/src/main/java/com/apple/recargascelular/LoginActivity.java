package com.apple.recargascelular;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.apple.recargascelular.databinding.ActivityLoginBinding;
import com.apple.recargascelular.ui.Personas;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private  String mail;
    private  String pass;
    private ArrayList<Personas> people;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        goRecargas();
    }

    private void goRecargas() {
        binding.btnLogin.setOnClickListener(view -> {
            mail=binding.editTextTextEmailAddress.getText().toString();
            pass=binding.editTextTextPassword.getText().toString();
            people=MainActivity.personas;
            int pos=buscarP(mail);
            if (mail.equals("") || pass.equals("")) {
                Toast.makeText(this,"Por favor ingresar datos",Toast.LENGTH_SHORT).show();
                binding.editTextTextEmailAddress.requestFocus();
            }
            else if (people.size()==0) {
                Toast.makeText(this,"Ningun usuario registrado, por favor registre uno",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,SignUpActivity.class);
                startActivity(i);
            }
            else if (mail.equals(people.get(pos).getMail()) && pass.equals(people.get(pos).getPass())){
                Intent i = new Intent(this,RecargasActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this,"Usuario o contraseña incorrectas ",Toast.LENGTH_SHORT).show();
                binding.editTextTextEmailAddress.requestFocus();
            }
        });
    }
    private int buscarP(String id) {
        int pos = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getMail().equals(id)) {
                pos=i;
                break;
            }
        }
        return pos;
    }

}
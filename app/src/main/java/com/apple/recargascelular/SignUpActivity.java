package com.apple.recargascelular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.apple.recargascelular.databinding.ActivitySignUpBinding;
import com.apple.recargascelular.ui.Personas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private String name;
    private String mail;
    private String pass;
    private ArrayList<Personas> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        people=MainActivity.personas;
        binding.btnSignUp.setOnClickListener(view -> verficarMail());

    }

    private void verficarMail() {
        if (people.size() != 0) {
            mail=binding.editMail.getText().toString();
            int pos=buscarP(mail);
            if (mail.equals(people.get(pos).getMail())){
                Toast.makeText(this,"Usuario Registrado, por favor inicie sesion",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            else{
                goLogin();
            }
        }
        else {
            goLogin();
        }
    }
    private int buscarP(String id) {
        int pos = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getMail().equals(mail)) {
                pos=i;
                break;
            }
        }
        return pos;
    }

    public void goLogin(){
        name=binding.editTextName.getText().toString();
        mail=binding.editMail.getText().toString();
        pass=binding.editPass.getText().toString();
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mail);
        if (name.equals("") || mail.equals("") || pass.equals("")) {
            Toast.makeText(this,"Por favor ingresar datos",Toast.LENGTH_SHORT).show();
            binding.editTextName.requestFocus();
        }
        else if (pass.length()<0) {
            Toast.makeText(this,"Por favor ingrese una contraseña mayor a 10",Toast.LENGTH_SHORT).show();
            binding.editPass.requestFocus();
        }
        else if (mather.find()==false){
            Toast.makeText(this,"Ingrese un correo valido, vuelva a intentar",Toast.LENGTH_SHORT).show();
            binding.editMail.requestFocus();
        }
        else {
            Personas persona = new Personas();
            persona.setName(name);
            persona.setMail(mail);
            persona.setPass(pass);
            MainActivity.personas.add(persona);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}
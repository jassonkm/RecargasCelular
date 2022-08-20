package com.apple.recargascelular;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.apple.recargascelular.databinding.ActivityRecargasBinding;
import com.apple.recargascelular.ui.Personas;


import java.util.ArrayList;

public class RecargasActivity extends AppCompatActivity {
    private ActivityRecargasBinding binding;
    private Integer monto;
    private String numero;
    private String numero1;
    private Integer monto2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRecargasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        spinnerView();
        recargarCel();
    }

    private void spinnerView() {
        ArrayList<String> services =new ArrayList<>();
        services.add("CLARO");
        services.add("MOVISTAR");
        services.add("WOM");
        services.add("TIGO");
        services.add("MOVIL EXITO");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,services);
        binding.spinner.setAdapter(adapter);
    }
    public void recargarCel(){
        binding.btnRecargar.setOnClickListener(view -> {
            monto=Integer.valueOf(binding.editValor.getText().toString());
            monto2=Integer.valueOf(binding.editValorConf.getText().toString());
            numero=binding.editCel.getText().toString();
            numero1=binding.editCelConf.getText().toString();
            if (numero.length()!=10){
                Toast.makeText(this, "Por favor ingresar numero valido", Toast.LENGTH_SHORT).show();
                binding.editCel.requestFocus();
            }
            else if (monto<1000){
                Toast.makeText(this, "Por favor ingresa un monto mayor a 1000", Toast.LENGTH_SHORT).show();
                binding.editValor.requestFocus();
            }
            else if (!monto.equals(monto2)){
                Toast.makeText(this, "Por favor ingrese el mismo monto en las dos opciones", Toast.LENGTH_SHORT).show();
                binding.editValor.requestFocus();
            }
            else if (monto.equals("") && numero.equals("")){
                Toast.makeText(this, "Por favor rellene todas las casillas", Toast.LENGTH_SHORT).show();
                binding.editValor.requestFocus();
            }
            else if (!numero.equals(numero1)) {
                Toast.makeText(this, "Por favor verifique el numero ingresado", Toast.LENGTH_SHORT).show();
                binding.editCel.requestFocus();
            }
            else{
                dialogoSaldo().show();
            }
        });
    }
    private AlertDialog.Builder dialogoSaldo() {
        return  new AlertDialog
                .Builder(this)
                .setTitle("Transaccion finalizada").setMessage("Recarga exitosa al numero "+numero+" por un valor de "+String.valueOf(monto))
                .setPositiveButton(R.string.mostrar, (dialogInterface, i) -> salir());
    }
    public void salir(){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
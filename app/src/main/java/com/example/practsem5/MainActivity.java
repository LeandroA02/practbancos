package com.example.practsem5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity
        extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Enviar(View view) {
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this , MainActivity2.class);
//Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
//Añadimos la información al intent
        intent.putExtras(b);
// Iniciamos la nueva actividad
        startActivity(intent);

    }
    public void clicklogin(View v){
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        EditText txtusur = findViewById(R.id.txtUsuario);
        EditText txtclave = findViewById(R.id.txtClave);
        WebService ws= new WebService(
                "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtusur.getText().toString() +
                        "&pass=" + txtclave.getText().toString(),
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");//

    }

    @Override
    public void processFinish(String result) throws JSONException {
        Intent intent = new Intent(this, MainActivity.class);
        TextView txtrespuesta = findViewById(R.id.txtRespuesta);
        if (result.equals("Login Correcto")) {
        }
        else {
            startActivity(intent);
        }

    }
}


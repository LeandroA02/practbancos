package com.example.practsem5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity2 extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, MainActivity2.this, MainActivity2.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
        //Localizar los controles
        TextView txtlista = (TextView)findViewById(R.id.txtBancos);
//Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
//Construimos el mensaje a mostrar
        txtlista.setText("Hola!, Bienvenido \n " +
                bundle.getString("NOMBRE"));

    }
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtBancos);
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("name").toString();
        }
        txtBancos.setText("Respuesta WS Lista de Bancos" + lstBancos);
    }
}
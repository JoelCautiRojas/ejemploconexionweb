package com.example.edutools_011.ejemploconexionweb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText entrada_user;
    EditText entrada_pass;
    TextView respuesta;
    Button bt_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada_user = findViewById(R.id.editText3);
        entrada_pass = findViewById(R.id.editText4);
        respuesta =  findViewById(R.id.textView);
        bt_enviar = findViewById(R.id.button);
        bt_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient cliente = new AsyncHttpClient();
                Context c = getApplicationContext();
                String url = "http://www.programadoresperuanos.com/login.php";
                RequestParams parametros = new RequestParams();
                parametros.put("usuario",entrada_user.getText().toString());
                parametros.put("clave",entrada_pass.getText().toString());
                cliente.post(c, url, parametros, new AsyncHttpResponseHandler() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode == 200){
                            String response_body = new String(responseBody);
                            respuesta.setText(String.valueOf(statusCode)+"\r\n"+response_body);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"status desconocido",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}

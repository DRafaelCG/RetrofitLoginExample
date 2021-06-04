package com.dim.retrofitloginexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dim.retrofitloginexample.R;
import com.dim.retrofitloginexample.model.LoginResponse;

public class MainActivity extends AppCompatActivity {
    LoginResponse loginResponseData;
    EditText etNombre, etPass;
    Button bttnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayoutElements();
        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaVacios(etNombre) && validaVacios(etPass)){
                    iniciarSesion();
                }
            }
        });
    }

    private void setupLayoutElements(){
        etNombre = (EditText) findViewById(R.id.etUsuario);
        etPass = (EditText)findViewById(R.id.etPassword);
        bttnLogin = (Button)findViewById(R.id.bttonLogin);
    }

    private boolean validaVacios(EditText etCampo){
        if(etCampo.getText().toString().trim().length() > 0) {
            return true;
        }
        etCampo.setError("Ingrese datos por favor");
        etCampo.requestFocus();
        return false;
    }

    private void iniciarSesion(){
        //Desplegamos un dialogo de progreso
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Espere por favor...");
        pDialog.show();

        API.getCliente().registrar(etNombre.getText().toString().trim(),
                etPass.getText().toString().trim(), "pass", new Callback<LoginResponse>(){

                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        pDialog.dismiss();
                        loginResponseData = loginResponse;
                        muestraToast(loginResponse.getMensaje());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        muestraToast(error.toString());
                        pDialog.dismiss();
                    }
                });
    }

    private void muestraToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
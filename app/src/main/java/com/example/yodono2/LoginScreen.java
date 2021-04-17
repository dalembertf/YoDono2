package com.example.yodono2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.yodono2.Entidades.Donantes;

public class LoginScreen extends AppCompatActivity {

    //DonanteDao db;
    //AppDatabase dataBase;

    Button boton_iniciar_sesion;

    private AppBarConfiguration mAppBarConfiguration;

    private YoDonoViewModel yoDonoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        yoDonoViewModel = new ViewModelProvider( this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getApplication()))
                .get(YoDonoViewModel.class);


        //dataBase = AppDatabase.getInstance( LoginScreen.this );
        //db = dataBase.getDonanteDao();

        boton_iniciar_sesion = (Button)findViewById(R.id.boton_inicio_sesion);

        boton_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text_cedula = (EditText) findViewById(R.id.text_login_cedula);
                EditText text_contrasena = (EditText) findViewById(R.id.text_login_contrasena);

                String cedula = text_cedula.getText().toString();
                String contrasena = text_contrasena.getText().toString();

                Donantes donante = yoDonoViewModel.buscarDonante( cedula, contrasena );
                if ( donante != null )
                {
                    Intent i = new Intent(LoginScreen.this, MainActivity.class);
                    i.putExtra( "Donante", donante );
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginScreen.this, "Usuario incorrecto.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };
}

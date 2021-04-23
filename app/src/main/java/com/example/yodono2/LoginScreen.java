package com.example.yodono2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Solicitudes;

public class LoginScreen extends AppCompatActivity {

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
                    Toast.makeText(LoginScreen.this, "Bienvenido " + donante.getNombre() , Toast.LENGTH_SHORT).show();
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

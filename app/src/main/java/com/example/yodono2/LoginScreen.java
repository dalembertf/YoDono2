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

    //DonanteDao db;
    //AppDatabase dataBase;

    Button boton_iniciar_sesion;

    private AppBarConfiguration mAppBarConfiguration;

    private YoDonoViewModel yoDonoViewModel;

    private int  CHANNEL_ID = 1;
    private String  channelID = "channelID";
    private Solicitudes solicitud;

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        yoDonoViewModel = new ViewModelProvider( this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getApplication()))
                .get(YoDonoViewModel.class);

        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Intent intent = new Intent(this, SolicitudIndividual.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Bundle bundle = intent.getExtras();
        solicitud =  (Solicitudes) bundle.get("Solicitud");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.yodono_favicon)
                .setContentTitle("Notificacion de Donante")
                .setContentText("")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Su solicituds ha sido completada"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


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
                    notificationManager.notify(CHANNEL_ID, builder.build());
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

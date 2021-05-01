package com.example.yodono2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Notificaciones;
import com.example.yodono2.Entidades.Solicitudes;
import com.example.yodono2.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Donantes donante_logueado;
    TextView bienvenida;
    List<Notificaciones> ListaNotifaciones;

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    private YoDonoViewModel yoDonoViewModel;
    private int  CHANNEL_ID = 1;
    private String  channelID = "channelID";

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.perfil, R.id.informacion, R.id.solicitudNueva, R.id.donantesFiltros, R.id.misSolicitudes)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        donante_logueado = (Donantes)bd.get("Donante");
        String nombre_donante = donante_logueado.getNombre();

        // obtengo el view correspondiente a nav_header_main.xml
        View headerLayout = navigationView.getHeaderView(0);

        // seteo nombre de donante en menú
        bienvenida = (TextView) headerLayout.findViewById(R.id.textView);
        bienvenida.setText( bienvenida.getText() + " " + nombre_donante );


        yoDonoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getApplication()))
                .get(YoDonoViewModel.class);

        ListaNotifaciones = yoDonoViewModel.getLista_Notificaciones(donante_logueado.getCedula());

        if (ListaNotifaciones != null) {
            Log.v("noti","algo");
            for (Notificaciones notificacion : ListaNotifaciones){
                createNotificationChannel();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                Integer id = notificacion.getIdSolicitud();
                Solicitudes solicitudCompletada = yoDonoViewModel.getSolicitud(id);

                Intent intentNotificacion = new Intent(this, SolicitudIndividual.class);
                intentNotificacion.putExtra("Solicitud", solicitudCompletada);
                intentNotificacion.putExtra( "Donante", donante_logueado );
                intentNotificacion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intentNotificacion, PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                        .setSmallIcon(R.drawable.yodono_favicon)
                        .setContentTitle("Notificacion de Donante")
                        .setContentText("")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Su solicitud #" + id.toString()+ " ha sido completada." ))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);


                notificationManager.notify(CHANNEL_ID, builder.build());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
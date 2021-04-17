package com.example.yodono2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Welcome extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    Button boton_login, boton_registrar;

    // Para cargar el carrusel bienvenida
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.welcome_dona, R.drawable.welcome_pedi, R.drawable.welcome_encontra };

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        boton_login = (Button)findViewById(R.id.boton_inicio_sesion);

        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome.this,LoginScreen.class);
                startActivity(i);
            }
        });

        boton_registrar = (Button)findViewById(R.id.boton_registro);

        boton_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome.this, RegistroPaso_1.class);
                startActivity(i);
            }
        });
    }

}
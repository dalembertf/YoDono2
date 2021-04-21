package com.example.yodono2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapaCentros extends Fragment {
    private final static int MY_PERMISSIONS_FINE_LOCATION = 101;
    private GoogleMap googleMap;
    LocationManager locationManager;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng myPosition;

            LatLng hmaciel = new LatLng(-34.90780231337366, -56.211993544449804);
            LatLng hitaliano = new LatLng(-34.89575289225348, -56.16434796000596);
            LatLng sanatoriobse = new LatLng(-34.90451471333267, -56.19453257782534);
            LatLng circatolico = new LatLng(-34.90691372574798, -56.17917895050145);
            LatLng sanatorioamericano = new LatLng(-34.899045093029414, -56.16002757560362);
            LatLng medicauruguaya = new LatLng(-34.89298608002212, -56.16275947374849);
            LatLng hbritanico = new LatLng(-34.89423567424288, -56.16293113513198);
            LatLng sns = new LatLng(-34.88893246873414, -56.159651131421604);
            LatLng hclinicas = new LatLng(-34.891134985799056, -56.151711888086986);
            LatLng hevangelico = new LatLng(-34.88086155277598, -56.14685294862298);
            LatLng hcasmu = new LatLng(-34.88037747848752, -56.14930985217412);
            LatLng hpasteur = new LatLng(-34.87501416481297, -56.138384544912604);
            LatLng fuerzasarmadas = new LatLng(-34.88326236027206, -56.15421090072999);
            LatLng hembro = new LatLng(-34.88348085866578, -56.090378390949994);
            LatLng hpereira = new LatLng(-34.89853029605554, -56.16294070072943);
            LatLng casadegalicia = new LatLng(-34.84211590388489, -56.20820107375006);
            LatLng crami = new LatLng(-34.72489228254979, -56.21963677560918);
            LatLng caamepa = new LatLng(-34.72380370032099, -55.961357044917456);
            LatLng gremeda = new LatLng(-30.395352214540903, -56.46144017203103);
            LatLng hcanelones = new LatLng(-34.72578882264625, -56.219530131426865);
            LatLng comeca = new LatLng(-34.532057064387075, -56.28253895841435);
            LatLng hrosario = new LatLng(-34.317254600624736, -57.356857946785865);
            LatLng cnuevapalmira = new LatLng(-33.86752127797546, -58.40356134308975);
            LatLng camec = new LatLng(-34.47118556110052, -57.844560846781015);
            LatLng camoc = new LatLng(-33.99446439638678, -58.28503825843155);
            LatLng hdurazno = new LatLng(-33.38598685475749, -56.51502830733294);
            LatLng camedur = new LatLng(-33.37426823362331, -56.52440901916898);
            LatLng cmmflorida = new LatLng(-34.097663944859356, -56.214925767429605);
            LatLng comef = new LatLng(-34.099468513035475, -56.21227897191871);
            LatLng camdel = new LatLng(-34.37623038028314, -55.244257627727606);
            LatLng hemocentro = new LatLng(-34.90608250253256, -54.96643923142095);
            LatLng hpaysandu = new LatLng(-32.25080851426827, -58.09995850371393);
            LatLng hyoung = new LatLng(-32.60221518639772, -57.60561814048387);
            LatLng hrivera = new LatLng(-30.90810709039497, -55.54453078448755);
            LatLng hsalto = new LatLng(-31.39088876355348, -57.961184635893474);
            LatLng hsanjose = new LatLng(-34.34083814046555, -56.70565747376634);
            LatLng hmercedes = new LatLng(-33.25304141176941, -58.04059744310891);
            LatLng htacuarembo = new LatLng(-31.720031446903867, -55.98469739104858);
            LatLng iac = new LatLng(-33.232792174012, -54.386173082825856);

            googleMap.addMarker(new MarkerOptions().position(hmaciel).title("Hospital Maciel").snippet("25 de mayo 0174"));
            googleMap.addMarker(new MarkerOptions().position(hitaliano).title("Hospital Italiano").snippet("Bulevar General Artigas 1632"));
            googleMap.addMarker(new MarkerOptions().position(sanatoriobse).title("Sanatorio BSE").snippet("Mercedes 1004"));
            googleMap.addMarker(new MarkerOptions().position(circatolico).title("Círculo Católico").snippet("Calle Minas 1250"));
            googleMap.addMarker(new MarkerOptions().position(sanatorioamericano).title("Sanatorio Americano").snippet("Dr. Isabelino Bosch 2466"));
            googleMap.addMarker(new MarkerOptions().position(medicauruguaya).title("Medica Uruguaya").snippet("Av. 8 de Octubre 2492"));
            googleMap.addMarker(new MarkerOptions().position(hbritanico).title("Hospital Británico").snippet("Av. Italia 2420"));
            googleMap.addMarker(new MarkerOptions().position(sns).title("Servicio Nacional de Sangre").snippet("Av. 8 de Octubre 2720"));
            googleMap.addMarker(new MarkerOptions().position(hclinicas).title("Hospital de Clínicas").snippet("Av. Italia"));
            googleMap.addMarker(new MarkerOptions().position(hevangelico).title("Hospital Evangélico").snippet("Mateo Vidal"));
            googleMap.addMarker(new MarkerOptions().position(hcasmu).title("Casmu").snippet("Av. 8 de Octubre"));
            googleMap.addMarker(new MarkerOptions().position(hpasteur).title("Hospital Pasteur").snippet("José Antonio Cabrera"));
            googleMap.addMarker(new MarkerOptions().position(fuerzasarmadas).title("Dirección Nacional de Sanidad de las Fuerzas Armadas").snippet("Av. 8 de Octubre 3060"));
            googleMap.addMarker(new MarkerOptions().position(hembro).title("Hembro").snippet("Avenida Italia 5286"));
            googleMap.addMarker(new MarkerOptions().position(hpereira).title("Centro Hospitalario Pereira Rossell").snippet("Bulevar General Artigas 1550"));
            googleMap.addMarker(new MarkerOptions().position(casadegalicia).title("Casa De Galícia").snippet("Av. Millán 4486"));
            googleMap.addMarker(new MarkerOptions().position(crami).title("Crami").snippet("Las Piedras"));
            googleMap.addMarker(new MarkerOptions().position(caamepa).title("CAAMEPA Pando").snippet("Baltasar Brum 1224, Canelones"));
            googleMap.addMarker(new MarkerOptions().position(gremeda).title("Gremeda").snippet("Av. 18 de Julio 253, Artigas"));
            googleMap.addMarker(new MarkerOptions().position(hcanelones).title("Dirección Departamental de Salud de Canelones").snippet("Pilar Cabrera 564 , Canelones"));
            googleMap.addMarker(new MarkerOptions().position(comeca).title("COMECA").snippet("Treinta y Tres 136, Canelones"));
            googleMap.addMarker(new MarkerOptions().position(hrosario).title("Hospital de Rosario").snippet("Jose M. Garat, Colonia"));
            googleMap.addMarker(new MarkerOptions().position(cnuevapalmira).title("Centro Auxiliar Nueva Palmira").snippet("Jose Pedro Varela 1506, Colonia"));
            googleMap.addMarker(new MarkerOptions().position(camec).title("Camec").snippet("Alberto Mendez, Colonia"));
            googleMap.addMarker(new MarkerOptions().position(camoc).title("CAMOC").snippet("Zorrilla de San Martin 683, Colonia"));
            googleMap.addMarker(new MarkerOptions().position(hdurazno).title("Hospital de Durazno").snippet("Maciel, Durazno"));
            googleMap.addMarker(new MarkerOptions().position(camedur).title("CAMEDUR").snippet("19 de Abril 384, Durazno"));
            googleMap.addMarker(new MarkerOptions().position(cmmflorida).title("Centro Médico Municipal Florida").snippet("Luis Alberto de Herrera, Florida"));
            googleMap.addMarker(new MarkerOptions().position(comef).title("COMEF IAMPP").snippet("Antonio Maria Fernandez 492, Florida"));
            googleMap.addMarker(new MarkerOptions().position(camdel).title("Camdel").snippet("Juan Farina 352, Lavalleja"));
            googleMap.addMarker(new MarkerOptions().position(hemocentro).title("Hemocentro").snippet("Hospital de Maldonado"));
            googleMap.addMarker(new MarkerOptions().position(hpaysandu).title("Hospital Departamental De Paysandú").snippet("Montecaseros 520, Paysandú"));
            googleMap.addMarker(new MarkerOptions().position(hyoung).title("Hospital de Young").snippet("Lavalleja 1903, Río Negro"));
            googleMap.addMarker(new MarkerOptions().position(hrivera).title("Hospital Departamental de Rivera").snippet("Pdte. Feliciano Viera, Rivera"));
            googleMap.addMarker(new MarkerOptions().position(hsalto).title("Hospital Regional Salto").snippet("18 de Julio, Salto"));
            googleMap.addMarker(new MarkerOptions().position(hsanjose).title("Hospital de San José de Mayo").snippet("Dámaso Larrañaga esquina Monseñor R. Di Martino, San José"));
            googleMap.addMarker(new MarkerOptions().position(hmercedes).title("Hospital de Mercedes").snippet("Florencio Sanchez, Soriano"));
            googleMap.addMarker(new MarkerOptions().position(htacuarembo).title("Hospital Regional de Tacuarembó").snippet("Treinta y Tres Orientales 444, Flores"));
            googleMap.addMarker(new MarkerOptions().position(iac).title("Sanatorio IAC").snippet("Cap. Basilio Araújo, Treinta y Tres"));


            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                googleMap.setMyLocationEnabled(true);
            } else {
                requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_FINE_LOCATION);
            }

            LocationManager locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                myPosition = new LatLng(latitude, longitude);


                LatLng coordinate = new LatLng(latitude, longitude);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
                googleMap.animateCamera(yourLocation);
            }




        }

        public void onRequestPermissionResult (int RequestCode, @NonNull String[] permissions, @NonNull int [] grantResults){
            mapaCentros.super.onRequestPermissionsResult(RequestCode, permissions, grantResults);
            switch (RequestCode) {
                case MY_PERMISSIONS_FINE_LOCATION:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            googleMap.setMyLocationEnabled(true);}
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "This app requieres location permissions to be granted", Toast.LENGTH_LONG).show();

                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa_centros, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
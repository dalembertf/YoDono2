package com.example.yodono2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yodono2.Entidades.Donantes;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    private YoDonoViewModel yoDonoViewModel;
    Donantes donante_logueado;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bd = getActivity().getIntent().getExtras();
        donante_logueado = (Donantes) bd.get("Donante");

        yoDonoViewModel = new ViewModelProvider( this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.getActivity().getApplication()))
                .get(YoDonoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cedulaPerfil = (TextView)view.findViewById(R.id.PerfilCedula);
        cedulaPerfil.setText(donante_logueado.getCedula());

        TextView nombrePerfil = (TextView)view.findViewById(R.id.PerfilNombre);
        nombrePerfil.setText(donante_logueado.getNombre());

        TextView apellidoPerfil = (TextView)view.findViewById(R.id.PerfilApellido);
        apellidoPerfil.setText(donante_logueado.getApellido());

        TextView departamentoPerfil = (TextView)view.findViewById(R.id.PerfilDepartamento);
        departamentoPerfil.setText(donante_logueado.getDepartamento());

        TextView grupoPerfil = (TextView)view.findViewById(R.id.PerfilGrupo);
        grupoPerfil.setText(donante_logueado.getGrupo_Sanguineo());

        TextView emailPerfil = (TextView)view.findViewById(R.id.PerfilEmail);
        emailPerfil.setText(donante_logueado.getEmail());

        TextView telefonoPerfil = (TextView)view.findViewById(R.id.PerfilTelefono);
        telefonoPerfil.setText(donante_logueado.getTelefono());




        //Button btnEditar = view.findViewById((R.id.boton_perfil_editar));

        final NavController navController = Navigation.findNavController(view);

        //btnEditar.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        navController.navigate(R.id.perfilEditar);
        //    }
        //});

    }
}
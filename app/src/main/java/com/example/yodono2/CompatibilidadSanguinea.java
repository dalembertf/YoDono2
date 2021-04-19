package com.example.yodono2;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class CompatibilidadSanguinea {

    public CompatibilidadSanguinea(){};

    public List<String> getGruposCompatibles( String grupoSanguineoReceptor ) {
        List<String> gruposSanguineosDonante = null;

        switch (grupoSanguineoReceptor) {
            case "A+" :
                gruposSanguineosDonante = Arrays.asList( "A+", "A-", "O+", "O-" );
                break;
            case "A-" :
                gruposSanguineosDonante = Arrays.asList( "A-", "O-" );
                break;
            case "B+" :
                gruposSanguineosDonante = Arrays.asList( "B+", "B-", "O+", "O-" );
                break;
            case "B-" :
                gruposSanguineosDonante = Arrays.asList( "B-", "O-" );
                break;
            case "AB+" :
                gruposSanguineosDonante = Arrays.asList( "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" );
                break;
            case "AB-" :
                gruposSanguineosDonante = Arrays.asList( "AB-", "A-", "B-", "O-" );
                break;
            case "O+" :
                gruposSanguineosDonante = Arrays.asList( "O+", "O-" );
                break;
            case "O-" :
                gruposSanguineosDonante = Arrays.asList( "O-" );
                break;
        }
        return gruposSanguineosDonante;
    }
}

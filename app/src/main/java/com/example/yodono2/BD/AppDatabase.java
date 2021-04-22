package com.example.yodono2.BD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.yodono2.Entidades.Converters;
import com.example.yodono2.Entidades.Donantes;
import com.example.yodono2.Entidades.Notificaciones;
import com.example.yodono2.Entidades.Relaciones.SolicitudConDonantes;
import com.example.yodono2.Entidades.Solicitudes;
import com.example.yodono2.daos.DonanteDao;
import com.example.yodono2.daos.SolicitudConDonantesDao;
import com.example.yodono2.daos.SolicitudesDao;


@Database(entities = {Donantes.class, Solicitudes.class, Notificaciones.class, SolicitudConDonantes.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "YoDono.db";
    private static volatile AppDatabase instance;

    public abstract DonanteDao getDonanteDao();
    public abstract SolicitudesDao getSolicitudesDao();
    public abstract SolicitudConDonantesDao getSolicitudesConDonantesDao();

    // la DB es un singleton
    public static synchronized AppDatabase getInstance(Context context ) {
        if ( instance == null ) {
            instance = create( context );
        }
        return instance;
    }

    //private AppDatabase() {};

    private static AppDatabase create( final Context context ) {

        return Room.databaseBuilder( context, AppDatabase.class, DB_NAME ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

}

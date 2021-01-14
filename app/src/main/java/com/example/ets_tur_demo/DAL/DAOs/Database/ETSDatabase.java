package com.example.ets_tur_demo.DAL.DAOs.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ets_tur_demo.DAL.DAOs.PersonDAO;
import com.example.ets_tur_demo.DAL.Entites.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class ETSDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();

    private static volatile ETSDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ETSDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ETSDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ETSDatabase.class, "ets_database")
                            .build();
                }
            }

        }
        return INSTANCE;
    }



}
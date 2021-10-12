package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Products.class,Provider.class}, version = 1,exportSchema = true)
public abstract class NoteAppDatabase extends RoomDatabase   {

    public abstract ProductsDao productsDao();
    public abstract ProviderDao providerDao();
    private static NoteAppDatabase INSTANCE;

    public static NoteAppDatabase getINSTANCE( Context context ) {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteAppDatabase.class , "DB_NAME" )
                    .allowMainThreadQueries()
                    .build();
            return INSTANCE;
        }
        return INSTANCE;
    }
}

package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProviderWithProducts {
    @Embedded public Provider provider;
    @Relation(
            parentColumn = "provider_name",
            entityColumn = "provider_name"
    )
    public List<Products> products;
}

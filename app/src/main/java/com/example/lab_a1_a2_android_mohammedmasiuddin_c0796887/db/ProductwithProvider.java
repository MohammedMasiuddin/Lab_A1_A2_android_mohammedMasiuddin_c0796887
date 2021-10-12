package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;


import androidx.room.Embedded;
import androidx.room.Relation;

public class ProductwithProvider {
    @Embedded
    public Products products;
    @Relation(
            parentColumn = "provider_name",
            entityColumn = "provider_name"
    )
    public Provider provider;
}
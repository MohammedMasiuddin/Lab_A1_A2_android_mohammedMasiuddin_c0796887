package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ProviderDao {

    @Insert
    void insertProviders(Provider... providers);

    @Query("Select * from provider")
    List<Provider> getAllProviders();


    @Query("Select provider_name from provider")
    List<String> getAllProvidersnames();


    @Query("Select * from provider where provider_name = :provider")
    List<Provider> getProviders(String provider);

    @Transaction
    @Query("Select * from Provider")
    public List<ProviderWithProducts> getProviderwithProducts();


    @Transaction
    @Query("Select * from provider where provider_name = :provider_name")
    public List<ProviderWithProducts> getProviderwithProductsforproductid(String provider_name);


    @Query("update provider \n" +
            "set provider_name=:providername,provider_email_address=:provideremai,provider_location=:location,provider_phonenumber=:providerphone where provider_name =:providername ")
    void updateProviders(String providername,String provideremai, String providerphone, String location);

}

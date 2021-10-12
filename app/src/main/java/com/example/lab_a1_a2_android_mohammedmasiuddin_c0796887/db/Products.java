package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Products {

    @PrimaryKey(autoGenerate = true)
    public int product_id;

    @ColumnInfo(name = "product_name")
    public String product_name;



    @ColumnInfo(name = "product_description")
    public String product_description;

    @ColumnInfo(name = "product_price")
    public int product_price;

    @NonNull
    @ColumnInfo(name = "provider_name")
    public  String provider_name;

    public Products( String product_name, String product_description, int product_price, String provider_name) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.provider_name = provider_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }
}

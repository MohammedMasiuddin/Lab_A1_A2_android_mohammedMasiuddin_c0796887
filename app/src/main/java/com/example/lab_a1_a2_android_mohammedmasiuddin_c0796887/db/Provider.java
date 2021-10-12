package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Provider {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "provider_name")
    public String provider_name;

    @ColumnInfo(name = "provider_email_address")
    public String provider_email_address;

    @ColumnInfo(name = "provider_phonenumber")
    public String provider_phonenumber;

    @ColumnInfo(name = "provider_location")
    public String provider_location;

    public Provider(String provider_name, String provider_email_address, String provider_phonenumber, String provider_location) {
        this.provider_name = provider_name;
        this.provider_email_address = provider_email_address;
        this.provider_phonenumber = provider_phonenumber;
        this.provider_location = provider_location;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_email_address() {
        return provider_email_address;
    }

    public void setProvider_email_address(String provider_email_address) {
        this.provider_email_address = provider_email_address;
    }

    public String getProvider_phonenumber() {
        return provider_phonenumber;
    }

    public void setProvider_phonenumber(String provider_phonenumber) {
        this.provider_phonenumber = provider_phonenumber;
    }

    public String getProvider_location() {
        return provider_location;
    }

    public void setProvider_location(String provider_location) {
        this.provider_location = provider_location;
    }
}

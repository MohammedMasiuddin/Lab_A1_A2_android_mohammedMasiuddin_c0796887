package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ProductsDao {

    @Query("Select * from products")
    List<Products> getAllProducts();

    @Query("Select * from products where product_name like '%'||:s||'%' or product_description like '%'||:s||'%'")
    List<Products> getFilterProducts(String s);

    @Insert()
    void insertProduct(Products... products);

    @Delete()
    void deleteProduct(Products... products);

    @Query("Delete from products")
    void deleteAllProducts();

    @Transaction
    @Query("Select * from products")
    public List<ProductwithProvider> getproductswithprovider();

    @Transaction
    @Query("Select * from products where product_id=:product_id")
    public ProductwithProvider getproductswithproviderforproduct_id(int product_id);
}

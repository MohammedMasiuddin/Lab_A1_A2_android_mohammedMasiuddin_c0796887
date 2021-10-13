package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductsDao {

    @Query("Select * from products")
    List<Products> getAllProducts();

    @Query("Select * from products where product_name like '%'||:s||'%' or product_description like '%'||:s||'%'")
    List<Products> getFilterProducts(String s);

    @Query("Select product_name from products where provider_name = :provider ")
    List<String> getAllProductsforprovidername( String provider );

    @Insert()
    void insertProduct(Products... products);

    @Query("update products \n" +
            "set provider_name=:providername,product_name=:product_name,product_description=:product_description,product_price=:product_price where product_id =:id ")
    void updateProduct(String product_name,String product_description, int product_price, String providername, int id);

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

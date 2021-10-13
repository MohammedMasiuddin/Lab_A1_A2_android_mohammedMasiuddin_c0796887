package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.NoteAppDatabase;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Provider;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.ProviderWithProducts;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductsListAdapter.ProductitemlistClicked, ProviderListAdapter.ProvideritemlistClicked {

    public static String TAG = " In Main Activity: ";
    public static final String My_Pref_File_Name = "com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.Preferences";

    TabLayout tabLayout;
    TabItem tabItemProducts, tabItemProviders;
    ViewPager2 viewPager;
    List<Products> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(My_Pref_File_Name,MODE_PRIVATE);
        Boolean isFirstRun = preferences.getBoolean("isFirstRun",true);

        Log.d(TAG, "onCreate: "+isFirstRun);

        if(isFirstRun){
            insertproducts();
            SharedPreferences.Editor edit = getSharedPreferences(My_Pref_File_Name,MODE_PRIVATE).edit();
            edit.putBoolean("isFirstRun",false);
            edit.commit();

        }


        tabLayout = findViewById(R.id.tabLayout);
        tabItemProducts = findViewById(R.id.tabProducts);
        tabItemProviders = findViewById(R.id.tabProviders);
        viewPager = findViewById(R.id.viewPager);

        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(this.getApplicationContext());

        this.products  = app.productsDao().getAllProducts();

        PagerAdapterMain pam = new PagerAdapterMain(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pam);

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition(),true);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );

        viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        tabLayout.selectTab(tabLayout.getTabAt(position));
                    }
                }
        );
    }

    public void refreshProducts(){
        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(getApplicationContext());
        products  = app.productsDao().getAllProducts();
    }

    public List<Products> getProducts() {

        return products;

    }

    private void insertproducts() {
        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(this.getApplicationContext());
        Products p1 = new Products("Laptop", "A dell laptop", 300, "walmart");
        Products p2 = new Products("mobile", "A samsung mobile", 500, "walmart");
        Products p3 = new Products("lamp", "A halogen lamp", 50, "ikea");
        Products p4 = new Products("tv", "A samsung tv", 500, "bestbuy");
        Products p5 = new Products("sofa", "A italian sofa", 200, "ikea");
        Products p6 = new Products("microwave", "A toshiba microwave", 500, "amazon");
        Products p7 = new Products("xbox", "A microsoft xbox for games", 500, "bestbuy");
        Products p8 = new Products("speaker", "A jbl speaker", 500, "walmart");
        Products p9 = new Products("headphones", "A samsung headphones", 500, "walmart");
        Products p10 = new Products("hard disk", "A hp hard disk", 500, "walmart");

        app.productsDao().insertProduct(p1, p2,p3,p4,p5,p6,p7,p8,p9,p10);

        Provider pr1 = new Provider("walmart","contact@walmart.com",
                "1-800-328-0402","Wal-Mart Canada Corp.\n" +
                "1940 Argentia Road\n" +
                "Mississauga, ON L5N 1P9");

        Provider pr2 = new Provider("ikea","contact@ikea.com",
                "1-866-866-4532","1065 Plains Road East\n" +
                "Burlington ON\n" +
                "L7T 4K1");

        Provider pr3 = new Provider("bestbuy","contact@bestbuy.com",
                "1-866-237-8289"," Richfield, Minnesota, United States");

        Provider pr4 = new Provider("amazon","contact@amazon.com",
                "1-877-586-3230","Seattle, Washington, United States");

        app.providerDao().insertProviders(pr1,pr2,pr3,pr4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("enter product");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: ");
                products = NoteAppDatabase.getINSTANCE(getApplicationContext()).productsDao().getFilterProducts(s);
                Log.d("TAg", ""+products);
                PagerAdapterMain pam = new PagerAdapterMain(getSupportFragmentManager(), getLifecycle());
                viewPager.setAdapter(pam);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                Log.d(TAG, "onQueryTextChange: ");
                return false;
            }
        });
        
        int searchCloseButtonId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) searchView.findViewById(searchCloseButtonId);
// Set on click listener
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manage this event.

                refreshProducts();
                PagerAdapterMain pam = new PagerAdapterMain(getSupportFragmentManager(), getLifecycle());
                viewPager.setAdapter(pam);
                searchView.setQuery("",false);
                searchView.setIconified(true);
                Log.d(TAG, "onClick: eet");
            }
        });

        MenuItem menuItem2 = menu.findItem(R.id.addProducts);
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.d(TAG, "onMenuItemClick: ");
                getSupportFragmentManager().beginTransaction().replace(R.id.producttablayout,ProductsDetailsFragment.newAddProductInstance())
                        .addToBackStack(null).commit();
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void productitemClicked(int product_id, String provider_name) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.producttablayout,ProductsDetailsFragment.newInstance(product_id,provider_name))
//                .addToBackStack(null).commit();

//        getSupportFragmentManager().beginTransaction().replace(R.id.producttablayout,ProductsDetailsFragment.newInstance(product_id,provider_name))
//                .addToBackStack(null).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.producttablayout,ProductsDetailsFragment.newInstance(product_id,provider_name))
                .addToBackStack(null).commit();
    }

    @Override
    public void provideritemClicked(String provider_name) {
        getSupportFragmentManager().beginTransaction().replace(R.id.providerTab, ProviderProductsFragment.newInstance(provider_name))
                .addToBackStack(null).commit();
    }

    @Override
    public void calltoprovider(ProviderWithProducts p) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+p.provider.provider_phonenumber));
        startActivity(intent);
    }

    @Override
    public void emailtoprovider(ProviderWithProducts p) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+p.provider.provider_email_address));
        startActivity(intent);
    }
}
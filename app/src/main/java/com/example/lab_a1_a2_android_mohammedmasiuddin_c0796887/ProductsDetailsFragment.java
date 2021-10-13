package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.NoteAppDatabase;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.ProductwithProvider;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Provider;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.ProviderWithProducts;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "product_id";
    private static final String ARG_PARAM2 = "provider_name";

    // TODO: Rename and change types of parameters
    private int mParam1 = -1;
    private String mParam2;
    public Context context;

    public ProductsDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2
     * @return A new instance of fragment ProductsDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsDetailsFragment newInstance(int param1, String param2) {
        ProductsDetailsFragment fragment = new ProductsDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductsDetailsFragment newAddProductInstance() {
        ProductsDetailsFragment fragment = new ProductsDetailsFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM1, -1);
        args.putString(ARG_PARAM2, "");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("TAG", "onCreateView:procduct detail " + mParam1);
        View view = inflater.inflate(R.layout.fragment_products_details, container, false);

        EditText productname = view.findViewById(R.id.ProductName);
        EditText product_des = view.findViewById(R.id.product_description);
        EditText product_price = view.findViewById(R.id.price);
        EditText provider_name = view.findViewById(R.id.providername);
        EditText provider_email = view.findViewById(R.id.provider_emailaddress);
        EditText provider_phone = view.findViewById(R.id.provider_phonenumber);
        EditText provider_address = view.findViewById(R.id.provider_address);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewProvider);
        Switch aSwitch = view.findViewById(R.id.newproviderswitch);

        Button button = view.findViewById(R.id.product_details_button);

        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(context.getApplicationContext());
//        List<ProviderWithProducts> pwp = app.providerDao().getProviderwithProducts();

        if (mParam1 != -1) {
            ProductwithProvider pwpforproduct = app.productsDao().getproductswithproviderforproduct_id(mParam1);

            productname.setText(pwpforproduct.products.product_name);
            product_des.setText(pwpforproduct.products.product_description);
            product_price.setText("" + pwpforproduct.products.product_price);
            provider_name.setText(pwpforproduct.provider.provider_name);
            provider_email.setText(pwpforproduct.provider.provider_email_address);
            provider_phone.setText(pwpforproduct.provider.provider_phonenumber);
            provider_address.setText(pwpforproduct.provider.provider_location);
            Log.d("TAG", "onCreateView: ");
            autoCompleteTextView.setVisibility(View.INVISIBLE);
            aSwitch.setVisibility(View.INVISIBLE);

        }
        Log.d("TAG", "onCreateView: ");
        if (mParam1 == -1 && mParam2 == "") {
            TextView text = view.findViewById(R.id.textViewProductDetail);
            text.setText("Add Products or Provider");
            provider_email.setVisibility(View.GONE);
            provider_address.setVisibility(View.GONE);
            provider_name.setVisibility(View.GONE);
            provider_phone.setVisibility(View.GONE);

            List<String> providernames = app.providerDao().getAllProvidersnames();
            autoCompleteTextView.setVisibility(View.VISIBLE);
            ArrayAdapter myadapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, providernames);
            autoCompleteTextView.setAdapter(myadapter);
            autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    autoCompleteTextView.showDropDown();
                }
            });

            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    autoCompleteTextView.setVisibility(View.GONE);
                    provider_email.setVisibility(View.VISIBLE);
                    provider_address.setVisibility(View.VISIBLE);
                    provider_name.setVisibility(View.VISIBLE);
                    provider_phone.setVisibility(View.VISIBLE);


                }
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(productname.getText().toString() == "" || product_des.getText().toString() == "" || product_price.getText().toString()+"" == "" || autoCompleteTextView.getText().toString() == ""){
                        Toast.makeText(getActivity().getApplicationContext(),"Enter details properly", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    int price = Integer.parseInt(String.valueOf(product_price.getText()));
                    Products p1 = new Products(productname.getText().toString(), product_des.getText().toString(), price, autoCompleteTextView.getText().toString());
                    app.productsDao().insertProduct(p1);
                    if(aSwitch.isChecked()){
                        if(provider_name.getText().toString() == "" || provider_email.getText().toString() == "" || provider_phone.getText().toString() == "" || provider_address.getText().toString() == ""){
                            Toast.makeText(getActivity().getApplicationContext(),"Enter details properly", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Provider pr1 = new Provider(provider_name.getText().toString(),
                                provider_email.getText().toString(),
                                provider_phone.getText().toString(),provider_address.getText().toString());

                        app.providerDao().insertProviders(pr1);
                        Toast.makeText(getActivity().getApplicationContext(),"Provider has added successfully",Toast.LENGTH_SHORT).show();


                    }


                    Toast.makeText(getActivity().getApplicationContext(),"Product has added successfully",Toast.LENGTH_SHORT).show();

                    MainActivity activity = (MainActivity) getActivity();
                    activity.refreshProducts();

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.producttablayout, new ProductsListFragment())
                            .commit();


                }
            });
//


        }


        return view;
    }
}
package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.NoteAppDatabase;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    Context context;

    public ProductsListFragment() {
        // Required empty public constructor

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsListFragment newInstance() {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_products_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.productList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        MainActivity activity = (MainActivity) getActivity();
//        List<Products> i = activity.getProducts();


        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(context.getApplicationContext());

//        List<Products> products = app.productsDao().getAllProducts();

        List<Products> products = activity.getProducts();
        Log.d("TAG", "onCreateView: reload ");



        myadapter = new ProductsListAdapter(context, products);
        recyclerView.setAdapter(myadapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback( (ProductsListAdapter) myadapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return rootView;
    }
}
package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.NoteAppDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderProductsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProviderProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * .
     * @return A new instance of fragment ProviderProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderProductsFragment newInstance(String param1) {
        ProviderProductsFragment fragment = new ProviderProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_provider_products, container, false);
        ListView listView = view.findViewById(R.id.Listofproductforprovider);


        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(getContext().getApplicationContext());
        List<String> pwp = app.productsDao().getAllProductsforprovidername(mParam1);
        ArrayAdapter myadapter2 = new ArrayAdapter<String>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                pwp);
        listView.setAdapter(myadapter2);

        return view;
    }
}
package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.NoteAppDatabase;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.ProviderWithProducts;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderListFragment extends Fragment {

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

    public ProviderListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProviderListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderListFragment newInstance(String param1, String param2) {
        ProviderListFragment fragment = new ProviderListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View rootView = inflater.inflate(R.layout.fragment_provider_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.providerlist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        MainActivity activity = (MainActivity) getActivity();


        NoteAppDatabase app = NoteAppDatabase.getINSTANCE(getContext().getApplicationContext());

        List<ProviderWithProducts> providers = app.providerDao().getProviderwithProducts();

        Log.d("tag","heinsdkdf"+providers.get(1).provider.provider_name);


        myadapter = new ProviderListAdapter(providers,activity);
        recyclerView.setAdapter(myadapter);

        return  rootView;
    }


}
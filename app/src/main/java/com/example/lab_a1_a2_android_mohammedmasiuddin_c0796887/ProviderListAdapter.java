package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Provider;
import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.ProviderWithProducts;

import java.util.List;

public class ProviderListAdapter extends RecyclerView.Adapter<ProviderListAdapter.ViewHolder1> {


    private List<ProviderWithProducts> providers;
    public ProvideritemlistClicked activity;


    public interface ProvideritemlistClicked {
        void provideritemClicked(String provider_name);
    }

    public ProviderListAdapter(List<ProviderWithProducts> providers, ProvideritemlistClicked activity) {
        this.providers = providers;
        this.activity = activity;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView providernames;
        TextView productcount;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            providernames = itemView.findViewById(R.id.Providernamelistitem);
            productcount = itemView.findViewById(R.id.productcountlistitem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ProviderWithProducts p = providers.get(providers.indexOf( (ProviderWithProducts) view.getTag()));

            activity.provideritemClicked(p.provider.getProvider_name());
        }
    }


    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_list_item,parent,false);

        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        holder.itemView.setTag(providers.get(position));
        Log.d("TAG", "onBindViewHolder: "+providers.get(position).products.size());
        holder.providernames.setText(providers.get(position).provider.provider_name);
        holder.productcount.setText("Products Count :"+providers.get(position).products.size());
    }



    @Override
    public int getItemCount() {
        return providers.size();
    }
}

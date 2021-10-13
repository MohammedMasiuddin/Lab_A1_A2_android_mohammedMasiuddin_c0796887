package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887.db.Products;

import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private List<Products> productslist;
    public ProductitemlistClicked activity;


    public interface ProductitemlistClicked {
        void productitemClicked(int product_id, String p);
    }

    public ProductsListAdapter(Context context, List<Products> list) {
        activity = (ProductitemlistClicked) context ;
        productslist = list;
        Log.d("TAG", "ProductsListAdapter: "+context);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TextView providertextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listTextViewProductName);
            providertextView = itemView.findViewById(R.id.providerNametextView);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Products p = productslist.get(productslist.indexOf( (Products) view.getTag()));

            activity.productitemClicked(p.getProduct_id(),p.provider_name);
        }
    }

    @NonNull
    @Override
    public ProductsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(productslist.get(position));
        holder.textView.setText(productslist.get(position).getProduct_name());
        holder.providertextView.setText(productslist.get(position).getProvider_name());
    }


    @Override
    public int getItemCount() {
        return productslist.size();
    }
}

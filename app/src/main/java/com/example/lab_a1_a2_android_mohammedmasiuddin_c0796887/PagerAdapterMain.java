package com.example.lab_a1_a2_android_mohammedmasiuddin_c0796887;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapterMain extends FragmentStateAdapter {
    public PagerAdapterMain(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new ProviderListFragment();
            default:
                return new ProductTabFragment();
        }

    }



    @Override
    public int getItemCount() {
        return 2;
    }
}

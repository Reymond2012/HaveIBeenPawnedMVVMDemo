package com.example.haveibeenpwnedmvvmdemo.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haveibeenpwnedmvvmdemo.R;
import com.example.haveibeenpwnedmvvmdemo.data.DomainResponse;

import java.util.ArrayList;
import java.util.List;

import static com.example.haveibeenpwnedmvvmdemo.R.*;

public class HaveIBeenPawnedAdapter extends RecyclerView.Adapter<HaveIBeenPawnedAdapter.itemViewHolder> {

    private final List<DomainResponse> results = new ArrayList<>();


    public void setData(List<DomainResponse> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext())
                .inflate(layout.domain_list, viewGroup, false);

        return new itemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull HaveIBeenPawnedAdapter.itemViewHolder itemViewHolder, int postion) {
        DomainResponse domainResponse = results.get(postion);
        itemViewHolder.tvDescription.setText(domainResponse.getDescription());
        if(domainResponse.getIsVerified()){
            itemViewHolder.tvIsVerified.setText("Verified");
        }else {
            itemViewHolder.tvIsVerified.setText("Not Verified");
        }


    }



    @Override
    public int getItemCount() {
        return results.size();
    }

    static class itemViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription;
        TextView tvIsVerified;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(id.tvDescription);
            tvIsVerified = itemView.findViewById(id.tvIsVerified);
        }


    }
}

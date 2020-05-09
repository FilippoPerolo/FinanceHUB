package com.example.meetfinance;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.meetfinance.presentation.model.Details;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Details> detailsList;

    public MyAdapter(List<Details> detailsList) {
        this.detailsList = detailsList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        TextView txt3;
        TextView txt4;
        View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.tv_companyName);
            txtFooter = (TextView) v.findViewById(R.id.tv_description);
            txt3 = (TextView) v.findViewById(R.id.tv_sector);
            txt4 = (TextView) v.findViewById(R.id.tv_industry);
        }
    }


    public void add(int position, Details item) {
        detailsList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        detailsList.remove(position);
        notifyItemRemoved(position);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        //   LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //   View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(itemView);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtHeader.setText(detailsList.get(position).getCompanyName());
        holder.txtFooter.setText(detailsList.get(position).getIndustry());
        holder.itemView.setBackgroundResource(R.drawable.details_drawable);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return detailsList.size();
    }

}


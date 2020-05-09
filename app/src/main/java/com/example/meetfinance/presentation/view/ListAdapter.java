package com.example.meetfinance.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.meetfinance.R;
import com.example.meetfinance.presentation.model.Symbol;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Symbol> symbolsList;
    public OnItemClickListener listener;

    public ListAdapter(List<Symbol> symbolsList, OnItemClickListener listener) {
        this.symbolsList = symbolsList;
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        TextView txtFooter;
        TextView txt3;
        TextView txt4;
        View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txt3 = (TextView) v.findViewById(R.id.tv_price);
            txt4 = (TextView) v.findViewById(R.id.tv_exchange);
        }
    }

    public void add(int position, Symbol item) {
        symbolsList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        symbolsList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Nouvelle view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtHeader.setText(symbolsList.get(position).getName());
        holder.txtFooter.setText(symbolsList.get(position).getSymbol());
        holder.itemView.setBackgroundResource(R.drawable.details_drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(symbolsList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return symbolsList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Symbol symbolsList);
    }

    public void filterList(List<Symbol> filteredList) {
        symbolsList = filteredList;
        notifyDataSetChanged();
    }

}


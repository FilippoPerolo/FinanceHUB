package com.example.meetfinance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Symbol> symbols;

    public MyAdapter(Context context, List<Symbol> symbols) {
        this.context = context;
        this.symbols = symbols;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.match_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Symbol oneSymbol = symbols.get(position);
    }

    @Override
    public int getItemCount() {
        return symbols.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View iconView;
        ImageView imageViewSymbol;
        TextView Title;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.iconView);
            imageViewSymbol = (ImageView) itemView.findViewById(R.id.imageViewSymbol);
            Title = (TextView) itemView.findViewById(R.id.Title);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}

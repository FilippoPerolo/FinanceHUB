package com.example.meetfinance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Symbol> values;
    private Context context;



    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        View layout;
        Button button;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }



    public void add(int position, Symbol item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }



    // constructor
    public ListAdapter(List<Symbol> myDataset) {
        values = myDataset;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
     //   LayoutInflater inflater = LayoutInflater.from(parent.getContext());
     //   View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(itemView); //v à la place
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Symbol currentSymbol = values.get(position);
        holder.txtHeader.setText(currentSymbol.getName());
        holder.txtFooter.setText(currentSymbol.getSymbol());
        holder.itemView.setBackgroundResource(R.drawable.details_drawable);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThirdActivity.class);
                intent.putExtra("ONE_COMPANY", currentSymbol);
                v.getContext().startActivity(intent);
            }
        });
      //   setAnimation(holder.layout);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    private void setAnimation (View animation){
        this.context = context;
        Animation animation1 = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        animation1.setDuration(1500);
        animation.startAnimation(animation1);
    }

}


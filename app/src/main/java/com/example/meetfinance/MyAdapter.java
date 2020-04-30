package com.example.meetfinance;

/*
public class MyAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Symbol> symbolsList;

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


    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.txtHeader.setText(symbolsList.get(position).getName());
        holder.txtFooter.setText(symbolsList.get(position).getSymbol());
        holder.itemView.setBackgroundResource(R.drawable.details_drawable);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return symbolsList.size();
    }

} */


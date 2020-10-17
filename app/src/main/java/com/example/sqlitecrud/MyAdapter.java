package com.example.sqlitecrud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<CustomerModel> mDataset;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    // Provide a suitable constructor (depends on the kind of dataset)

    public MyAdapter(List<CustomerModel> myDataset,Context context) {
        this.mDataset = myDataset;
        this.context=context;

    }
    // Create new views (invoked by the layout manager)

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        // create a new view

        View v =  LayoutInflater.from(context)
                .inflate(R.layout.row_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d("SQLiteCrud", "onBindViewHolder: "+mDataset.get(position));
        holder.etr_id.setText(mDataset.get(position).toString());

    }
    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView etr_id;
        TextView etr_name;
        TextView etr_age;
        TextView etr_switch;
        public MyViewHolder(View v) {
            super(v);
            etr_id=v.findViewById(R.id.ert_id);
//            etr_name=v.findViewById(R.id.ert_id);
//            etr_age=v.findViewById(R.id.ert_id);
//            etr_switch=v.findViewById(R.id.ert_id);
        }
    }
}

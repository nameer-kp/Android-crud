package com.example.sqlitecrud;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

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

        MyViewHolder vh = new MyViewHolder(v,this);
        //setting view on click listener as viewholder(it implements onclicklistener)
        v.setOnClickListener(vh);
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
    //this method invokes when an row is clicked
    public void removeItem(int position) {
        Log.d("SQLite", "removeItem: pressed on item "+position);

        //TODO::connect to databasehelper to remove object from database
        //TODO:; you can initialize the DatabaseHelper in your onCreate() method to your activity and then using interface pass the data from adapter to Activity or fragment whenever you perform a click on an Item in, and after getting data to your activity or fragment you should update the data to your database

        mDataset.remove(position);
        notifyItemRemoved(position);

    }
        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView etr_id;
            TextView etr_name;
            TextView etr_age;
            TextView etr_switch;
            private MyAdapter myAdapter;

            //we need to pass adapter ..to view holder inorder to get the item position
            public MyViewHolder(View v, MyAdapter adapter) {
                super(v);
                this.myAdapter = adapter;

                etr_id = v.findViewById(R.id.ert_id);

//            etr_name=v.findViewById(R.id.ert_id);
//            etr_age=v.findViewById(R.id.ert_id);
//            etr_switch=v.findViewById(R.id.ert_id);
            }
            //creating a onclick method on viewholder
            public void onClick(View view) {
                myAdapter.removeItem(getAdapterPosition());
            }
        }

    }

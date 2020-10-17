package com.example.sqlitecrud;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private String TAG="SQLite CRUD";
    //reference for all elements in the layout
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText et_name,et_age;
    Button btn_add,btn_viewAll;
    Switch sw_active;
    RecyclerView rv_customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing elements;
        btn_add =findViewById(R.id.btn_add);
        btn_viewAll =findViewById(R.id.btn_viewAll);
        sw_active =findViewById(R.id.sw_active);
        et_age =findViewById(R.id.et_age);
        et_name =findViewById(R.id.et_name);

//        //recyler view specifics
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);

        //button listeners for buttons

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    CustomerModel customerModel = new CustomerModel(-1, et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_active.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: Exception Catched Number Format Exception");
                }
            }

        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button View All Pressed",5).show();
            }
        });
    }




    }

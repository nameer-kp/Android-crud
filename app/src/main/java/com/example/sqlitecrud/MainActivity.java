package com.example.sqlitecrud;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG="SQLite CRUD";
    //reference for all elements in the layout
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private DataBaseHelper dataBaseHelper;

    public void setRemoveCustomerModel(CustomerModel removeCustomerModel) {
        this.removeCustomerModel = removeCustomerModel;
    }

    private CustomerModel removeCustomerModel;

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
        recyclerView = findViewById(R.id.rv_customerList);
//      //getting database helper
        dataBaseHelper= new DataBaseHelper(MainActivity.this);
        viewOnRecycler(dataBaseHelper);
//

        //button listeners for buttons

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                     customerModel = new CustomerModel(-1, et_name.getText().toString(),Integer.parseInt(et_age.getText().toString()),sw_active.isChecked());

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: Exception Catched Number Format Exception");
                    customerModel = new CustomerModel(-1,"error",0,false);
                }
                //calling addOne to Insert it into datebase
                boolean isSuccess=dataBaseHelper.addOne(customerModel);
                if(isSuccess){
                    viewOnRecycler(dataBaseHelper);
                }
                Log.d(TAG, "onClick: Adding to database"+isSuccess);
            }

        });
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 viewOnRecycler(dataBaseHelper);
                // Toast.makeText(MainActivity.this,list.toString(),5).show();


//                 use this setting to improve performance if you know that changes
                }
        });
    }

    private void viewOnRecycler(DataBaseHelper dataBaseHelper) {
        List<CustomerModel> list= dataBaseHelper.getList();
        myAdapter= new MyAdapter(list,MainActivity.this);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager



        // specify an adapter (see also next example)
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }




}

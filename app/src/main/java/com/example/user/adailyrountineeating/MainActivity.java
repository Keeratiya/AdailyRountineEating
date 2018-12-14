package com.example.user.adailyrountineeating;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper EatingRountineDB;

    Button btnAddData,btnViewData;

    EditText  etDate,etB , etLau ,etD;



    @Override
    protected  void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EatingRountineDB = new DatabaseHelper(this);

        etDate = (EditText) findViewById(R.id.etDate);
        etB = (EditText) findViewById(R.id.etBreak);
        etLau = (EditText) findViewById(R.id.etL);
        etD = (EditText) findViewById(R.id.etDin);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData =(Button) findViewById(R.id.btnViewData);

        AddData();
        ViewData();

    }

    public  void AddData(){

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = etDate.getText().toString();
                String breakfast = etB.getText().toString();
                String launch = etLau.getText().toString();
                String dinner = etD.getText().toString();

                boolean insertData = EatingRountineDB.addData(date,breakfast,launch,dinner);

                if (insertData == true){

                    Toast.makeText(MainActivity.this, "Data SuccessFully Inserted!",Toast.LENGTH_LONG).show();


                }else {

                    Toast.makeText(MainActivity.this,"Somethings went wrong :(",Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    public  void  ViewData(){

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor data = EatingRountineDB.showData();

                if(data.getCount() == 0){


                }

                StringBuffer buffer = new StringBuffer();

                while (data.moveToNext()){
                    buffer.append("\n"+"Note : "+ data.getString(0)+"\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
                    buffer.append("\n"+"Date : "+ data.getString(1)+"\n");
                    buffer.append("Breakfast : "+ data.getString(2)+"\n");
                    buffer.append("Launch : "+ data.getString(3)+"\n");
                    buffer.append("Dinner : "+ data.getString(4)+"\n");

                    display("My Daily Eating", buffer.toString());

                }
            }
        });


    }
    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}

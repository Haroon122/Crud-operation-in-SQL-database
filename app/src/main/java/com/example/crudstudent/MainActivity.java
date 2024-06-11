package com.example.crudstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText Sname, Sclass, Sroll;
    Button insertBtn, checkBtn, updateBtn,delBtn;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Sname = findViewById(R.id.editStudent);
        Sclass = findViewById(R.id.editClass);
        Sroll = findViewById(R.id.editroll);
        insertBtn = findViewById(R.id.insertBtn);
        checkBtn = findViewById(R.id.checktBtn);
        updateBtn = findViewById(R.id.updateBtn);
        delBtn=findViewById(R.id.DeleteBtn);
        helper = new DbHelper(this);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, showdata.class));
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = Sname.getText().toString().trim();
                String sclass = Sclass.getText().toString().trim();
                String sroll = Sroll.getText().toString().trim();

                if (sname.isEmpty() || sclass.isEmpty() || sroll.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insertSuccess = helper.insert_record(sname, sclass, sroll);
                    if (insertSuccess) {
                        Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                        Sname.setText("");
                        Sclass.setText("");
                        Sroll.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = Sname.getText().toString().trim();
                String sclass = Sclass.getText().toString().trim();
                String sroll = Sroll.getText().toString().trim();

                if (sname.isEmpty() || sclass.isEmpty() || sroll.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    StudentModel student = new StudentModel(sname, sclass, sroll);
                    boolean updateSuccess = helper.updateRecord(student);
                    if (updateSuccess) {
                        Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        Sname.setText("");
                        Sclass.setText("");
                        Sroll.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname = Sname.getText().toString();
                if (!sname.isEmpty()) {
                    boolean deleteSuccess = helper.deleteRecord(sname);
                    if (deleteSuccess) {
                        Toast.makeText(MainActivity.this, "Data deleted Successfully", Toast.LENGTH_SHORT).show();
                        Sname.setText("");
                        Sclass.setText("");
                        Sroll.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to delete data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

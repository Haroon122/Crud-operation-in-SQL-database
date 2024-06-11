package com.example.crudstudent;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class showdata extends AppCompatActivity {

    TableLayout tableLayout;
    ArrayList<StudentModel> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showdata);

        tableLayout = findViewById(R.id.tableLayout);
        DbHelper helper = new DbHelper(this);

        // Fetch data from the database
        studentList = helper.getAllRecords();

        if (studentList.isEmpty()) {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        } else {
            // Populate TableLayout
            for (StudentModel student : studentList) {
                TableRow row = new TableRow(this);

                TextView nameTextView = new TextView(this);
                nameTextView.setText(student.getName());
                nameTextView.setPadding(12, 12, 20, 12); // Adding padding for spacing
                nameTextView.setGravity(android.view.Gravity.CENTER); // Center the text
                TableRow.LayoutParams nameParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                nameTextView.setLayoutParams(nameParams);
                row.addView(nameTextView);

                TextView classTextView = new TextView(this);
                classTextView.setText(student.getStudentClass());
                classTextView.setPadding(12, 12, 20, 12); // Adding padding for spacing
                classTextView.setGravity(android.view.Gravity.CENTER); // Center the text
                TableRow.LayoutParams classParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                classTextView.setLayoutParams(classParams);
                row.addView(classTextView);

                TextView rollTextView = new TextView(this);
                rollTextView.setText(student.getRollNo());
                rollTextView.setPadding(12, 12, 20, 12); // Adding padding for spacing
                rollTextView.setGravity(android.view.Gravity.CENTER); // Center the text
                TableRow.LayoutParams rollParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                rollTextView.setLayoutParams(rollParams);
                row.addView(rollTextView);

                tableLayout.addView(row);
            }
        }
    }
}

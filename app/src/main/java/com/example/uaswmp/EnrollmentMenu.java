package com.example.uaswmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EnrollmentMenu extends AppCompatActivity {

    private Button buttonSelectSubjects, buttonViewSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_menu);

        // Initialize UI components
        buttonSelectSubjects = findViewById(R.id.buttonSelectSubjects);
        buttonViewSummary = findViewById(R.id.buttonViewSummary);

        // Go to SelectSubjects Activity
        buttonSelectSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnrollmentMenu.this, SelectSubjects.class);
                startActivity(intent);
            }
        });

        // Go to EnrollmentSummary Activity
        buttonViewSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnrollmentMenu.this, EnrollmentSummary.class);
                startActivity(intent);
            }
        });
    }
}

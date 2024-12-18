package com.example.uaswmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;

public class SelectSubjects extends AppCompatActivity {

    private CheckBox checkBoxMath, checkBoxPhysics, checkBoxChemistry, checkBoxBiology, checkBoxCS, checkBoxGeo, checkBoxEco, checkBoxEnglish;
    private Button buttonSubmit;
    private TextView textViewSummary;

    private final int mathCredits = 3;
    private final int physicsCredits = 4;
    private final int chemistryCredits = 2;
    private final int biologyCredits = 3;
    private final int csCredits = 4;
    private final int geoCredits = 4;
    private final int ecoCredits = 4;
    private final int englishCredits = 4;

    private final int maxCredits = 24; // Maximum allowable credits

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subjects);

        // Initialize UI components
        checkBoxMath = findViewById(R.id.checkBoxMath);
        checkBoxPhysics = findViewById(R.id.checkBoxPhysics);
        checkBoxChemistry = findViewById(R.id.checkBoxChemistry);
        checkBoxBiology = findViewById(R.id.checkBoxBiology);
        checkBoxCS = findViewById(R.id.checkBoxCS);
        checkBoxGeo =  findViewById(R.id.checkBoxGeo);
        checkBoxEco =  findViewById(R.id.checkBoxEco);
        checkBoxEnglish =  findViewById(R.id.checkBoxEnglish);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        // Button click listener for subject selection
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitSubjects();
            }
        });
    }

    private void submitSubjects() {
        int totalCredits = 0; // Track total credits
        ArrayList<String> selectedSubjects = new ArrayList<>(); // Track selected subjects

        // Check which subjects are selected and calculate total credits
        if (checkBoxMath.isChecked()) {
            totalCredits += mathCredits;
            selectedSubjects.add("Math (3 Credits)");
        }
        if (checkBoxPhysics.isChecked()) {
            totalCredits += physicsCredits;
            selectedSubjects.add("Physics (4 Credits)");
        }
        if (checkBoxChemistry.isChecked()) {
            totalCredits += chemistryCredits;
            selectedSubjects.add("Chemistry (2 Credits)");
        }
        if (checkBoxBiology.isChecked()) {
            totalCredits += biologyCredits;
            selectedSubjects.add("Biology (3 Credits)");
        }
        if (checkBoxCS.isChecked()) {
            totalCredits += csCredits;
            selectedSubjects.add("Computer Science (4 Credits)");
        }
        if (checkBoxGeo.isChecked()) {
            totalCredits += geoCredits;
            selectedSubjects.add("Geography (4 Credits)");
        }
        if (checkBoxEco.isChecked()) {
            totalCredits += ecoCredits;
            selectedSubjects.add("Economy (4 Credits)");
        }
        if (checkBoxEnglish.isChecked()) {
            totalCredits += englishCredits;
            selectedSubjects.add("English (4 Credits)");
        }

        // Check credit limit
        if (totalCredits > maxCredits) {
            Toast.makeText(this, "Total credits exceed the maximum limit of " + maxCredits, Toast.LENGTH_LONG).show();
            textViewSummary.setText("Please select fewer subjects to meet the credit limit.");
        } else if (totalCredits == 0) {
            Toast.makeText(this, "No subjects selected. Please choose at least one subject.", Toast.LENGTH_SHORT).show();
        } else {
            // Create a summary string
            StringBuilder summary = new StringBuilder("Selected Subjects:\n");
            for (String subject : selectedSubjects) {
                summary.append(subject).append("\n");
            }
            summary.append("\nTotal Credits: ").append(totalCredits);

            // Save data to Firestore
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            CollectionReference enrollmentsCollection = firestore.collection("enrollments");

            // You can store the data with the userId as the document ID
            String userId = "user123"; // Replace with a dynamic user ID
            enrollmentsCollection.document(userId).set(new EnrollmentData(summary.toString()));

            // Pass summary to EnrollmentSummary activity
            Intent intent = new Intent(SelectSubjects.this, EnrollmentSummary.class);
            intent.putExtra("summary", summary.toString());
            startActivity(intent);
        }
    }
}

package com.example.uaswmp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class EnrollmentSummary extends AppCompatActivity {

    private TextView textViewSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_summary);

        textViewSummary = findViewById(R.id.textViewEnrollmentSummary);

        // Initialize Firestore
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = "user123"; // Replace with actual user ID

        // Fetch the data from Firestore
        firestore.collection("enrollments")
                .document(userId) // Retrieve the user's enrollment data
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Get the enrollment summary
                            String summary = document.getString("summary");
                            if (summary != null && !summary.isEmpty()) {
                                textViewSummary.setText(summary);
                            } else {
                                textViewSummary.setText("No subjects selected. Please go back and select your subjects.");
                            }
                        } else {
                            textViewSummary.setText("No subjects found for this user.");
                        }
                    } else {
                        Toast.makeText(EnrollmentSummary.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
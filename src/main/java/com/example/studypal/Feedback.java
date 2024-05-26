package com.example.studypal;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Feedback extends AppCompatActivity {

    private RatingBar ratingEffectiveness, ratingDesign, ratingOverall;
    private EditText etFeedback;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        ratingEffectiveness = findViewById(R.id.rating_effectiveness);
        ratingDesign = findViewById(R.id.rating_design);
        ratingOverall = findViewById(R.id.rating_overall);
        etFeedback = findViewById(R.id.et_feedback);
        btnSubmit = findViewById(R.id.btn_submit);

        // Set click listener for submit button
        btnSubmit.setOnClickListener(v -> submitFeedback());
    }

    private void submitFeedback() {
        float effectivenessRating = ratingEffectiveness.getRating();
        float designRating = ratingDesign.getRating();
        float overallRating = ratingOverall.getRating();
        String feedbackText = etFeedback.getText().toString();

        if (effectivenessRating == 0 || designRating == 0 || overallRating == 0) {
            Toast.makeText(this, "Please provide ratings for all categories.", Toast.LENGTH_SHORT).show();
            return; // Exit the method if ratings are missing
        }

        // Show confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Submit Feedback")
                .setMessage("Are you sure you want to submit this feedback?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Handle positive button click
                    // Here you can add the code to submit feedback
                    // For demonstration, we'll just show a Toast message
                    Toast.makeText(this, "Feedback submitted! Thank you!", Toast.LENGTH_SHORT).show();

                    // Optionally, you can clear the form after submission
                    ratingEffectiveness.setRating(0);
                    ratingDesign.setRating(0);
                    ratingOverall.setRating(0);
                    etFeedback.setText("");
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}

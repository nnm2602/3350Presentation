package com.example.comp3350.softwaresavants.presentation_it2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Get a reference to the "Get Started" button
        Button getStartedButton = findViewById(R.id.getStarted_button);

        // Set an OnClickListener to handle button clicks
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the other activity
                Intent intent = new Intent(SplashScreenActivity.this, IngredientSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}

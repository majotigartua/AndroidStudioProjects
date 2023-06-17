package com.example.greetings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nameEditText = findViewById(R.id.name_edit_text);
        TextView greetingsTextView = findViewById(R.id.greetings_text_view);
        Button greetingsButton = findViewById(R.id.greetings_button);
        greetingsButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            if (!name.isEmpty()) {
                String greetings = getString(R.string.welcome_label, name);
                greetingsTextView.setText(greetings);
            } else {
                Toast.makeText(this, R.string.empty_fields_label, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
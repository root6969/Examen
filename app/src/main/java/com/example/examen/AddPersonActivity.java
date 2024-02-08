package com.example.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText bankAccountEditText;

    private UserDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        lastNameEditText = findViewById(R.id.editTextLastName);
        firstNameEditText = findViewById(R.id.editTextFirstName);
        bankAccountEditText = findViewById(R.id.editTextBankAccount);

        databaseHelper = new UserDatabaseHelper(this);

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    private void saveUser() {
        String lastName = lastNameEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String bankAccount = bankAccountEditText.getText().toString();

        long newRowId = databaseHelper.saveUser(lastName, firstName, bankAccount);

        if (newRowId != -1) {
            Toast.makeText(this, "User saved successfully!", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to save user.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        lastNameEditText.setText("");
        firstNameEditText.setText("");
        bankAccountEditText.setText("");
    }
}

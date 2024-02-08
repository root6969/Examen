package com.example.examen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Display user information when the activity is created
        displayUserInfo();
    }

    private void displayUserInfo() {
        // Create a new instance of the DatabaseHelper
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(this);

        // Get readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define columns to retrieve
        String[] projection = {
                UserDatabaseHelper.COLUMN_LAST_NAME,
                UserDatabaseHelper.COLUMN_FIRST_NAME,
                UserDatabaseHelper.COLUMN_BANK_ACCOUNT
        };

        // Query the database
        Cursor cursor = db.query(
                UserDatabaseHelper.TABLE_USERS,  // Table name
                projection,                  // Columns to retrieve
                null,                        // Columns for the WHERE clause
                null,                        // Values for the WHERE clause
                null,                        // Don't group the rows
                null,                        // Don't filter by row groups
                null                         // The sort order
        );

        if (cursor.moveToFirst()) {
            // Retrieve user information from the cursor
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_LAST_NAME));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_FIRST_NAME));
            String bankAccount = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_BANK_ACCOUNT));

            // Display user information in TextView
            TextView userDetailsTextView = findViewById(R.id.textViewUserDetails);
            userDetailsTextView.setText("Nume: " + lastName + "\nPrenume: " + firstName + "\nCont bancar: " + bankAccount);
        }

        // Close the cursor and database
        cursor.close();
        db.close();
    }
}

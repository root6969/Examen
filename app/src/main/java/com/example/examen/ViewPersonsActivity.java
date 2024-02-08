package com.example.examen;// ViewPersonsActivity.java

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPersonsActivity extends AppCompatActivity {

    private TextView userDetailsTextView;
    private UserDatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_persons);

        userDetailsTextView = findViewById(R.id.textViewUserDetails);
        databaseHelper = new UserDatabaseHelper(this);

        displayUsers();
        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the current activity and go back
            }
        });
    }

    private void displayUsers() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                UserDatabaseHelper.COLUMN_LAST_NAME,
                UserDatabaseHelper.COLUMN_FIRST_NAME,
                UserDatabaseHelper.COLUMN_BANK_ACCOUNT
        };

        Cursor cursor = db.query(
                UserDatabaseHelper.TABLE_USERS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        StringBuilder userDetails = new StringBuilder();
        while (cursor.moveToNext()) {
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_LAST_NAME));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_FIRST_NAME));
            String bankAccount = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_BANK_ACCOUNT));

            userDetails.append("Nume: ").append(lastName).append("\nPrenume: ").append(firstName)
                    .append("\nCont bancar: ").append(bankAccount).append("\n\n");
        }

        userDetailsTextView.setText(userDetails.toString());

        cursor.close();
        db.close();
    }
}

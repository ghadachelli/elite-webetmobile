package com.example.projetintegration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetintegration.WelcomeActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, emailInput, birthDateInput, passwordInput, confirmPasswordInput;
    private Spinner roleSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        birthDateInput = findViewById(R.id.birthDateInput);
        roleSpinner = findViewById(R.id.roleSpinner);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerButton = findViewById(R.id.registerButton);

        // Configuration du sélecteur de date pour le champ de date de naissance
        birthDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                         MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                                birthDateInput.setText(date);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        // Gestion du clic sur le bouton d'inscription
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    private void validateForm() {
        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String birthDate = birthDateInput.getText().toString().trim();
        String password = passwordInput.getText().toString();
        String confirmPassword = confirmPasswordInput.getText().toString();

        StringBuilder errors = new StringBuilder();

        if (firstName.isEmpty()) errors.append("First name is required\n");
        if (lastName.isEmpty()) errors.append("Last name is required\n");
        if (email.isEmpty()) errors.append("Email is required\n");
        if (birthDate.isEmpty()) errors.append("Birth date is required\n");
        if (password.isEmpty()) errors.append("Password is required\n");
        if (!password.equals(confirmPassword)) errors.append("Passwords do not match\n");

        if (errors.length() == 0) {
            // If validation is successful, redirect to WelcomeActivity
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("FIRST_NAME", firstName);
            startActivity(intent);
        } else {
            // Display errors
            Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
        }
    }}
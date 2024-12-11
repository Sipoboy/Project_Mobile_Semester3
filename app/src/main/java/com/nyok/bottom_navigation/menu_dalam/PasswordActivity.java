package com.nyok.bottom_navigation.menu_dalam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.ApiService;
import com.nyok.bottom_navigation.service.ApiService;
import com.nyok.bottom_navigation.ModelApi.UpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private ImageButton togglePasswordVisibility;
    private Button editButton;
    private boolean isPasswordVisible = false;
    private static final String SHARED_PREF_NAME = "myPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_password);

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);
        togglePasswordVisibility = findViewById(R.id.toggle_password_visibility);
        editButton = findViewById(R.id.edit_button); // Tombol untuk menyimpan data

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Guest");
        String password = sharedPreferences.getString("password", "User");

        usernameField.setText(username);
        passwordField.setText(password);

        togglePasswordVisibility.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.ichidepw);
            } else {
                passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.ichidepw);
            }
            passwordField.setSelection(passwordField.getText().length());
            isPasswordVisible = !isPasswordVisible;
        });

        editButton.setOnClickListener(v -> updateUser());
    }

    private void updateUser() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // Logging untuk debug
        Log.d("PasswordActivity", "Update User Called");
        Log.d("PasswordActivity", "Username: " + username + ", Password: " + password);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        UpdateRequest request = new UpdateRequest(username, password);

        apiService.updateUser(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("PasswordActivity", "Response Code: " + response.code());
                Log.d("PasswordActivity", "Response Message: " + response.message());

                if (response.isSuccessful()) {
                    // Simpan data ke SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(PasswordActivity.this, "Update berhasil!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PasswordActivity.this, "Gagal update: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("PasswordActivity", "API Error: " + t.getMessage());
                Toast.makeText(PasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

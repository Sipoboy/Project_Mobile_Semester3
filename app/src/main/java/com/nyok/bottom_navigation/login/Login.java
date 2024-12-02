package com.nyok.bottom_navigation.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nyok.bottom_navigation.ModelApi.LoginResponse;
import com.nyok.bottom_navigation.menu_dalam.MainActivity;
import com.nyok.bottom_navigation.R;
import com.nyok.bottom_navigation.service.ApiClient;
import com.nyok.bottom_navigation.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private TextView btn_edittext;
    private EditText Username, Password;
    private Button btnlogin;

    // SharedPreferences untuk status login
    public static final String SHARED_PREF_NAME = "myPref";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mengatur tampilan edge-to-edge secara manual
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Cek apakah user sudah login sebelumnya
//        boolean sudahMasuk = sharedPreferences.getBoolean("masuk", false);
//        if (sudahMasuk) {
//            // Jika sudah login, langsung ke MainActivity
//            Intent intent = new Intent(Login.this, MainActivity.class);
//            startActivity(intent);
//            finish();  // Mengakhiri activity login
//            return;
//        }

        // Inisialisasi view jika belum login
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.passwordedittext);
        btnlogin = findViewById(R.id.btnlogin);
        btn_edittext = findViewById(R.id.textView7);

        btn_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.newInstance().show(getSupportFragmentManager(), Register.TAG);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = Username.getText().toString().trim();
                String getPassword = Password.getText().toString().trim();

                if (getUsername.isEmpty() || getPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username atau password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(getUsername, getPassword);
                }
            }
        });
    }

    private void loginUser(String emailOrUser, String password) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.login(emailOrUser, password, "true"); // Kirim "true" untuk is_api

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if ("success".equals(loginResponse.getStatus())) {
                        Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        // Simpan status login di SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("masuk", true);
                        editor.putString("role", loginResponse.getRole()); // Simpan role
                        editor.putString("username", loginResponse.getUsername()); // Simpan username
                        editor.apply();
                        Log.d("Login", "Username yang disimpan: " + loginResponse.getUsername());
                        Log.d("LoginResponse", "Response: " + response.body());

                        // Pindah ke MainActivity
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                } else {
                        Toast.makeText(Login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Login gagal: Respons tidak valid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
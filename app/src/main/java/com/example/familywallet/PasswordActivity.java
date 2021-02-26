package com.example.familywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.familywallet.R;

public class PasswordActivity extends AppCompatActivity {
    EditText editText;
    Button button;

    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ввод пароля");
        setContentView(R.layout.activity_password);

        SharedPreferences settings = getSharedPreferences("PRFS", 0);
        password = settings.getString("password", "");

        editText = (EditText) findViewById(R.id.editTextNumberPassword);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if (text.equals(password)) {
                    Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                    intent.putExtra("passwordK", text);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PasswordActivity.this, "Неправильный пароль!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
package com.example.familywallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.familywallet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private LottieAnimationView progressbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // взятие экземпляра FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // инициализируем все представления через идентификатор, определенный выше
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        progressbar = findViewById(R.id.progressBar);

        // Установить прослушиватель при нажатии на кнопку входа
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }


    private void loginUserAccount() {
        // показать видимость индикатора выполнения, чтобы показать загрузку
        progressbar.setVisibility(View.VISIBLE);
        progressbar.playAnimation();

        // Взять значение двух редактируемых текстов в строках
        final String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // проверки правильности ввода адреса электронной почты и пароля
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Не введен Email",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Не введен пароль",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // вход существующего пользователя
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(
                                            @NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Добро пожаловать, " + email + ".",
                                                    Toast.LENGTH_LONG)
                                                    .show();

                                            // скрываем индикатор выполнения
                                            //progressbar.setVisibility(View.GONE); ++++++++

                                            // если вход выполнен успешно
                                            // намерение заниматься дома
                                            Intent intent
                                                    = new Intent(LoginActivity.this,
                                                    MainScreen.class);
                                            startActivity(intent);
                                        } else {
                                            // Ошибка входа
                                            Toast.makeText(getApplicationContext(),
                                                    "Неизвестная ошибка",
                                                    Toast.LENGTH_LONG)
                                                    .show();

                                            // скрываем индикатор выполнения
                                            progressbar.setVisibility(View.GONE);
                                        }
                                    }
                                });
            }
        },1500);

    }

}
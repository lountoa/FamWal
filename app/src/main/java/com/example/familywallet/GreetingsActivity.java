package com.example.familywallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.familywallet.MakePasswordActivity;
import com.example.familywallet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



public class GreetingsActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private LottieAnimationView progressbar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);

        // взятие экземпляра FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // инициализируем все представления через идентификатор, определенный выше
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.passwd);
        Btn = findViewById(R.id.btnregister);
        progressbar = findViewById(R.id.progressBar);

        progressbar.setVisibility(View.VISIBLE);
        progressbar.playAnimation();


        // Установить на слушателя щелчка на кнопке регистрации
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        // показать видимость индикатора выполнения, чтобы показать загрузку

        // Взять значение двух редактируемых текстов в строках
        final String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();



        // Проверка правильности ввода адреса электронной почты и пароля
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

        // создать нового пользователя или зарегистрировать нового пользователя
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Вы зарегестрированы!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // скрываем индикатор выполнения
                            //progressbar.setVisibility(View.GONE);

                            // если пользователь создал намерение авторизоваться
                            Intent intent
                                    = new Intent(GreetingsActivity.this,
                                    MakePasswordActivity.class);
                            intent.putExtra("LOGF",email);
                            intent.putExtra("PASF",password);
                            startActivity(intent);
                        }
                        else {
                            // Регистрация не удалась
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Неизвестная ошибка!"
                                            + "Попробуйте еще раз.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // скрываем индикатор выполнения
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}
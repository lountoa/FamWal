package com.example.familywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakePasswordActivity extends AppCompatActivity {
    EditText enterpas,repeatpas;
    Button accept;
    TextView text2, text3;
    Typeface myfont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Установка пароля");
        setContentView(R.layout.activity_make_password);

        text2 = (TextView)findViewById(R.id.textView5);
        text3 = (TextView)findViewById(R.id.textView6);
        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/poppinssemibold.ttf");
        text2.setTypeface(myfont);
        text3.setTypeface(myfont);


        enterpas = (EditText) findViewById(R.id.editTextNumberPassword3);
        repeatpas = (EditText) findViewById(R.id.editTextNumberPassword2);
        accept = (Button) findViewById(R.id.button15);

        Intent intent = getIntent();
        final String LOGF1 = intent.getStringExtra("LOGF");
        final String PASF1 = intent.getStringExtra("PASF");


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = enterpas.getText().toString();
                String text2 = repeatpas.getText().toString();

                if (text1.equals("") || text2.equals("")) {
                    Toast.makeText(MakePasswordActivity.this, "Не введен пароль!", Toast.LENGTH_SHORT).show();
                } else if (text1.equals(text2)) {
                    SharedPreferences settings = getSharedPreferences("PRFS", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("password", text1);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                    intent.putExtra("LOGFB",LOGF1);
                    intent.putExtra("PASFB",PASF1);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MakePasswordActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();;
                }

            }
        });

    }
}
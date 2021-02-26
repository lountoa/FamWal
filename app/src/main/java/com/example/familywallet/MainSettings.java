package com.example.familywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.familywallet.MakePasswordActivity;
import com.example.familywallet.R;

public class MainSettings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);
        setTitle("Настройки");

        Button setup = (Button) findViewById(R.id.turnPAs);
        Button edit = (Button) findViewById(R.id.edPas);
        Button del = (Button) findViewById(R.id.delPas);

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSettings.this, MakePasswordActivity.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSettings.this, MakePasswordActivity.class);
                startActivity(intent);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("PRFS", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("password", "");
                editor.apply();
            }
        });
    }

}

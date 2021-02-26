package com.example.familywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familywallet.MainSettings;
import com.example.familywallet.R;
import com.example.familywallet.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainScreen extends AppCompatActivity {

    TextView expences, wallet, marketsC, cafesC, transportC, paymentsC, pocketMC;

    SharedPreferences sPref;
    private static final String SHARED_PREF_NAME = "mypref";


    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setTitle("Главный экран MonBud");

        init();
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    private void init() {
        sPref = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String walC = sPref.getString(KEY_NAME,null);

        ImageView set = (ImageView) findViewById(R.id.setting);
        ImageView refilB = (ImageView) findViewById(R.id.refill);
        Button safe = (Button) findViewById(R.id.safeBut);

        TextView markets = (TextView) findViewById(R.id.textView14);
        TextView cafes = (TextView) findViewById(R.id.textView16);
        TextView transport = (TextView) findViewById(R.id.textView19);
        TextView payments = (TextView) findViewById(R.id.textView22);
        TextView pocketM = (TextView) findViewById(R.id.textView24);

        marketsC = (TextView) findViewById(R.id.textView23);
        cafesC = (TextView) findViewById(R.id.textView25);
        transportC = (TextView) findViewById(R.id.textView21);
        paymentsC = (TextView) findViewById(R.id.textView17);
        pocketMC = (TextView) findViewById(R.id.textView15);


        expences = findViewById(R.id.tvExValue);
        wallet = findViewById(R.id.tvBalValue);

        Intent intent = getIntent();
        final String password = intent.getStringExtra("passwordK");

        String zero = "0";
        char c = zero.charAt(0);

        if (walC == null) {
            wallet.setText("0");
        } else if (walC.length() > 1 && walC.charAt(0) == c && walC.charAt(1) == c){
            wallet.setText("0");
        } else {
            wallet.setText(walC);
        }
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, MainSettings.class);
                startActivity(intent);
            }
        });

        refilB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, MainMoney.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(getDeviceName());

        markets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp = expences.getText().toString();

                String market = marketsC.getText().toString();
                String cafe = cafesC.getText().toString();
                String transpor = transportC.getText().toString();
                String payment = paymentsC.getText().toString();
                String pocket = pocketMC.getText().toString();
                User newUser = new User(market, cafe, transpor, payment,
                        pocket, password, walC, exp);
                myRef.removeValue();
                myRef.push().setValue(newUser);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Saved successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}

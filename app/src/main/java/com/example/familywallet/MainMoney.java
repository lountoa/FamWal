package com.example.familywallet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.familywallet.R;

import java.util.ArrayList;

public class MainMoney extends Activity {

    /**Переменная текстбокса*/
    EditText calcDialogDisplay;

    SharedPreferences sPref;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    /**Переменные кнопок*/
    TextView allClear;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView division;
    TextView four;
    TextView five;
    TextView six;
    TextView multiply;
    TextView one;
    TextView two;
    TextView three;
    TextView subtract;
    TextView zero;
    TextView equals;
    TextView addition;

    TextView sign;

    Typeface myfont;

    ImageButton denyWal;
    ImageButton acceptWal;

    /**Результат который заносится в масив для обработки*/
    ArrayList<Float> result = new ArrayList<Float>();

    /**Первое введенное число*/
    float number1;

    /**Второе введенное число*/
    float number2;

    int currentOperation = 0;
    int nextOperation;

    /**Прибавление*/
    final static int ADD = 1;

    /**Вычитание*/
    final static int SUBTRACT = 2;

    /**Умножение*/
    final static int MULTIPLY = 3;

    /**Деление*/
    final static int DIVISION = 4;

    /**Равно*/
    final static int EQUALS = 5;


    final static int CLEAR = 1;
    final static int DONT_CLEAR = 0;
    int clearCalcDisplay = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_money);

        calcDialogDisplay = (EditText) findViewById(R.id.editText1);
        allClear = (TextView) findViewById(R.id.button22);
        seven = (TextView) findViewById(R.id.button11);
        eight = (TextView) findViewById(R.id.button12);
        nine = (TextView) findViewById(R.id.button13);
        division =(TextView) findViewById(R.id.button9);
        four = (TextView) findViewById(R.id.button6);
        five = (TextView) findViewById(R.id.button7);
        six =(TextView) findViewById(R.id.button8);
        multiply = (TextView) findViewById(R.id.button10);
        one = (TextView) findViewById(R.id.button1);
        two = (TextView) findViewById(R.id.button2);
        three = (TextView) findViewById(R.id.button3);
        subtract = (TextView) findViewById(R.id.button5);
        zero = (TextView) findViewById(R.id.button20);
        equals = (TextView) findViewById(R.id.button14);
        addition = (TextView) findViewById(R.id.button4);

        sign = (TextView) findViewById(R.id.signmoney);

        myfont = Typeface.createFromAsset(this.getAssets(), "fonts/oswaldmedium.ttf");
        calcDialogDisplay.setTypeface(myfont);

        denyWal = (ImageButton) findViewById(R.id.denyWallet);
        acceptWal = (ImageButton) findViewById(R.id.acceptWallet);

        calcDialogDisplay.setKeyListener(DigitsKeyListener.getInstance(true,true));
        registerListeners();

    }

    /*Обработка нажатия на экран*/
    public void registerListeners () {

        sPref = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        denyWal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMoney.this, MainScreen.class);
                startActivity(intent);
            }
        });

        acceptWal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sPref.edit();
                editor.putString(KEY_NAME, calcDialogDisplay.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainMoney.this, MainScreen.class);
                startActivity(intent);
            }
        });


        seven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("9");

            }
        });

        division.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(DIVISION);
                sign.setText("/");
            }
        });

        allClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcDialogDisplay.setText("");
                number1 = 0;
                number2 = 0;
                result.removeAll(result);
                currentOperation = 0;
                nextOperation = 0;
            }
        });

        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("4");

            }
        });

        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("5");

            }
        });

        six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("6");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("0");
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(ADD);
                sign.setText("+");
            }
        });


        multiply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sign.setText("*");
                calcLogic(MULTIPLY);
            }
        });

        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("1");

            }
        });

        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("2");

            }
        });

        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearCalcDisplay == CLEAR) {
                    calcDialogDisplay.setText("");
                }
                clearCalcDisplay = DONT_CLEAR;
                calcDialogDisplay.append("3");

            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(SUBTRACT);
                sign.setText("-");
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(EQUALS);
                sign.setText("=");
            }
        });

    }


    /*Функция расчета введенных значений*/
    private void calcLogic(int operator) {

        result.add(Float.parseFloat(calcDialogDisplay.getText().toString()));

        if (operator != EQUALS) {
            nextOperation = operator;
        }else if (operator == EQUALS){
            nextOperation = 0;
        }

        switch (currentOperation) {

            /*Прибавление*/
            case ADD:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 + number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

            /*Вычитание*/
            case SUBTRACT:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 - number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

            /*Умножение*/
            case MULTIPLY:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 * number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;
            /*Деление*/
            case DIVISION:
                number1 = result.get(0);
                number2 = result.get(1);

                result.removeAll(result);

                result.add(number1 / number2);

                calcDialogDisplay.setText(String.format("%.0f", result.get(0)));
                break;

        }

        clearCalcDisplay = CLEAR;
        currentOperation = nextOperation;
        if (operator == EQUALS) {
            number1 = 0;
            number2 = 0;
            result.removeAll(result);
        }
    }

}
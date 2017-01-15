package com.valmurs.manumurali.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    //private Double operand2 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.editText);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttondot = (Button) findViewById(R.id.buttondot);

        Button buttondiv = (Button) findViewById(R.id.buttondiv);
        Button buttonmul = (Button) findViewById(R.id.buttonmul);
        Button buttonadd = (Button) findViewById(R.id.buttonadd);
        Button buttonsub = (Button) findViewById(R.id.buttonsub);
        Button buttonequ = (Button) findViewById(R.id.buttonequ);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);


        View.OnClickListener oplistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue=Double.valueOf(value);
                    performOperation(doubleValue, op);
                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);

            }
        };

        buttonadd.setOnClickListener(oplistener);
        buttonsub.setOnClickListener(oplistener);
        buttonmul.setOnClickListener(oplistener);
        buttondiv.setOnClickListener(oplistener);
        buttonequ.setOnClickListener(oplistener);

        Button buttonNeg=(Button) findViewById(R.id.buttonNeg);
        buttonNeg.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                String value=newNumber.getText().toString();
                if (value.length()==0){
                    newNumber.setText("-");
                }else {
                    try {
                        Double doubleValue=Double.valueOf(value);
                        doubleValue *=-1;
                        newNumber.setText(doubleValue.toString());
                    }catch (NumberFormatException e){
                        newNumber.setText("");
                    }
                }

            }
        });

    }

    private void performOperation(Double value, String operation) {

        if (null == operand1) {
            operand1 = value;
        } else {

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {

                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "-":
                    operand1 -= value;
                    break;

            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}

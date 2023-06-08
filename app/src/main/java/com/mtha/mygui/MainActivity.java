package com.mtha.mygui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtResult;
    EditText edInput;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnMul, btnDiv;
    Button btnCE, btnC, btnDelete, btnDot;
    Button btnResutl;

    double so1 = Double.NaN;
    double so2;
    String ACTION;
    TableLayout tbl_cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
    }

    //viet phuong thuc ghep cac doi tuong view voi cac view trong layout
    private void getViews() {
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        btnCE = findViewById(R.id.btnCE);
        btnC = findViewById(R.id.btnC);
        btnDelete = findViewById(R.id.btnDelete);
        btnDot = findViewById(R.id.btnDot);

        btnResutl = findViewById(R.id.btnResult);

        txtResult = findViewById(R.id.txtResult);
        edInput = findViewById(R.id.edInput);
        tbl_cal = findViewById(R.id.tbl_cal);
        //dang ky xu ly su kien cho cac nut lenh
        for (int i = 2; i < tbl_cal.getChildCount(); i++) {
            TableRow row = (TableRow) tbl_cal.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(this);

            }
        }

    }


    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (v.getId()) {
            case R.id.btnCE:
                edInput.setText(null);
                break;
            case R.id.btnC:
                so1 = Double.NaN;
                txtResult.setText(null);
                edInput.setText(null);
                break;
            case R.id.btnDelete:
                String num = edInput.getText().toString();
                if (num != null && num.length() > 0) {
                    num = num.substring(0, num.length() - 1);
                }
                edInput.setText(num);
                edInput.setSelection(edInput.getText().length());
                break;
            case R.id.btn9:
            case R.id.btn8:
            case R.id.btn7:
            case R.id.btn6:
            case R.id.btn5:
            case R.id.btn4:
            case R.id.btn3:
            case R.id.btn2:
            case R.id.btn1:
            case R.id.btn0:
                getNumber(b);
                break;
            case R.id.btnAdd:
                setAction(b);
                operation(ACTION);
                break;
            case R.id.btnSub:
                setAction(b);
                operation(ACTION);
                break;
            case R.id.btnMul:
                setAction(b);
                operation(ACTION);
                break;
            case R.id.btnDiv:
                setAction(b);
                operation(ACTION);
                break;
            case R.id.btnDot:
                break;
            case R.id.btnResult:
                result();
                break;

        }
    }

    private void getNumber(Button button) {
        edInput.setText(edInput.getText() + button.getText().toString());
        edInput.setSelection(edInput.getText().length());
    }

    private void setAction(Button button) {
        ACTION = button.getText().toString();
    }

    private void operation(String action) {

        if (action.equals("+")) {
            if (!Double.isNaN(so1)) {
                so2 = Double.parseDouble(edInput.getText().toString());
                so1 = so1 + so2;
            } else {
                so1 = Double.parseDouble(edInput.getText().toString());
            }

        }
        if (action.equals("-")) {
            if (!Double.isNaN(so1)) {
                so2 = Double.parseDouble(edInput.getText().toString());
                so1 = so1 - so2;
            } else {
                so1 = Double.parseDouble(edInput.getText().toString());
            }


        }
        if (action.equals("*")) {
            if (!Double.isNaN(so1)) {
                so2 = Double.parseDouble(edInput.getText().toString());
                so1 = so1 * so2;
            } else {
                so1 = Double.parseDouble(edInput.getText().toString());
            }

        }
        if (action.equals("/")) {
            if (!Double.isNaN(so1)) {
                so2 = Double.parseDouble(edInput.getText().toString());
                so1 = so1 / so2;
            } else {
                so1 = Double.parseDouble(edInput.getText().toString());
            }

        }
        txtResult.setText(so1 + action);
        edInput.setText(null);
    }

    private void result() {
        double kq = 0;
        if (ACTION != null && ACTION.equals("+")) {
            kq = so1 + Double.parseDouble(edInput.getText().toString());
        } else if (ACTION != null && ACTION.equals("-")) {
            kq = so1 - Double.parseDouble(edInput.getText().toString());
        } else if (ACTION != null && ACTION.equals("*")) {
            kq = so1 * Double.parseDouble(edInput.getText().toString());
        } else if (ACTION != null && ACTION.equals("/")) {
            kq = so1 / Double.parseDouble(edInput.getText().toString());
        }
        txtResult.setText(null);
        edInput.setText(String.valueOf(kq));
    }
}
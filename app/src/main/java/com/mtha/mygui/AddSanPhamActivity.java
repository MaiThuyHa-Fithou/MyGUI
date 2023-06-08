package com.mtha.mygui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSanPhamActivity extends AppCompatActivity {

    EditText etMaSP, etTenSP, etGiaSP;
    Button btnLuu, btnDong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);
        getViews();
        //khai bao doi tuong intent de nhan gia tri truyen tu ListSPAct
        //thong qua phuong thuc getIntent
        Intent intentData = getIntent();
        String message =intentData.getStringExtra("msg");
        Toast.makeText(AddSanPhamActivity.this,"message [" + message+"]",
                Toast.LENGTH_LONG).show();
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dong activity hien tai va quay ve activity truoc
                finish();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay ra doi tuong SanPham va truyen ve cho ListSPAct
                SanPham sp = getSanPham();
                //khai bao mot doi tuong intent de chua kq tra ve
                Intent data = new Intent();
                //Khai bao doi tuong bundle de chua du lieu dang object
                Bundle bundle = new Bundle();
                bundle.putSerializable("sp", sp);
                //put doi tuong bundle vao intent de truyen data ve
                data.putExtras(bundle);
                //thuc hien setResult tra ve cho ListSPActi
                setResult(RESULT_OK,data);
                //dong activity nay lai
                finish();

            }
        });
    }

    private void getViews(){
        etMaSP = findViewById(R.id.etMaSP);
        etTenSP = findViewById(R.id.etTenSP);
        etGiaSP = findViewById(R.id.etGiaSP);
        btnLuu = findViewById(R.id.btnLuu);
        btnDong = findViewById(R.id.btnDong);
    }
    private SanPham getSanPham(){
        String maSP = etMaSP.getText().toString();
        String tenSP = etTenSP.getText().toString();
        double giaSP = Double.parseDouble(etGiaSP.getText().toString());
        return new SanPham(maSP, tenSP, giaSP);
    }
}
package com.mtha.mygui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListSanPhamActivity extends AppCompatActivity {

    FloatingActionButton fab_add;
    ListView lvSanPham;
    ArrayList<SanPham> lsSP = new ArrayList<SanPham>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        fab_add = findViewById(R.id.fab_add);
        //xu ly su kien click len add
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mo activity moi o day
                //khai bao 1 doi tuong intent
                Intent intent = new Intent(ListSanPhamActivity.this,
                        AddSanPhamActivity.class);
                //truyen du lieu tu ListSPAct sang AddSPAct
                intent.putExtra("msg","Xin chao cac ban!!");
                //mo activity moi thong qua phuong thuc startActivity
                //startActivity(intent);
                //startActivity va co nhan ket qua tra ve tu activity con lai
                addSanPham.launch(intent);
            }
        });

        lvSanPham=findViewById(R.id.lvSanPham);
        //khoi tao adapter
        adapter = new ArrayAdapter(ListSanPhamActivity.this,
                android.R.layout.simple_list_item_1,lsSP);
        lvSanPham.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> addSanPham = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //xu ly ket qua tra ve tu AddSPAct o day
                    if(result.getResultCode()==RESULT_OK){
                        //thi lay du lieu truyen ve de xu ly them lsSP
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        SanPham sp =(SanPham) bundle.get("sp");
                        lsSP.add(sp);
                        //load lai du lieu moi cho adapter
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }
}
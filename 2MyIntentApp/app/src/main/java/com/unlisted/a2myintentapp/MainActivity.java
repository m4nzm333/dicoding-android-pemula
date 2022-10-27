package com.unlisted.a2myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnMoveActivity;
    private Button btnMoveWithDataActivity;
    private Button btnDialPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = (Button)findViewById(R.id.btn_move_activity);                 // Button Pindah Act tanpa data
        btnMoveActivity.setOnClickListener(this);

        btnMoveWithDataActivity = (Button)findViewById(R.id.btn_move_activity_data);    // Btn Pindah Act dgn data
        btnMoveWithDataActivity.setOnClickListener(this);

        btnDialPhone = (Button)findViewById(R.id.btn_dial_number);                      // Btn Pindah Act ke luar App
        btnDialPhone.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Irman Mashuri");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_dial_number:
                String phoneNumber = "085397392260";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
        }
    }
}
package com.example.student.phonecallquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity {

    EditText et_number;
    Button btn_call;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("입력받은 번호로 전화걸기");

        et_number = (EditText)findViewById(R.id.et_number);
        btn_call = (Button)findViewById(R.id.btn_call);

        btn_call.setOnClickListener(new BtnListener());

    }

    private class BtnListener implements View.OnClickListener {
        Intent intent = null;
        @Override
        public void onClick(View v) {
            phoneNumber = "tel:" + et_number.getText().toString();
            intent = new Intent(Intent.ACTION_VIEW, parse(phoneNumber));
            startActivity(intent);
        }
    }
}

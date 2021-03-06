package com.example.student.intentdatatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_send;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("인텐트로 데이터 전달");

        btn_send = (Button)findViewById(R.id.btn_send);

        // 데이터를 전달할 인텐트와 전달받을 인텐트를 지정
        intent = new Intent(MainActivity.this, SubActivity.class);

        // 인텐트에 데이터 저장 : 기본 타입, 배열 모두 가능
        // putExtra(String name, ValueType value)
        intent.putExtra("int_value", 1234);
        intent.putExtra("str_value", "my intent");
        intent.putExtra("bool_value", true);
        intent.putExtra("double_value", 3.14d);
        intent.putExtra("float_value", 42.195f);

        int[] int_arr = {1,2,3};
        boolean[] bool_arr = {true, false, true};
        double[] double_arr = {4.4d, 5.5d, 6.6d};
        float[] float_arr = {1.1f, 2.2f, 3.3f};

        intent.putExtra("int_arr_value", int_arr);
        intent.putExtra("bool_arr_value", bool_arr);
        intent.putExtra("double_arr_value", double_arr);
        intent.putExtra("float_arr_value", float_arr);

        btn_send.setOnClickListener(new BtnListener());

    }

    private class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 액티비티 시작
            startActivity(intent);
        }
    }
}

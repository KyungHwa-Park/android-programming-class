package com.example.student.intentdatatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView tv_show;

    // 인텐트를 통해 전달받은 데이터를 저장할 변수 정의
    int val_int; String val_str; boolean val_bool;
    double val_double; float val_float;
    int[] val_int_arr; boolean[] val_bool_arr;
    double[] val_double_arr; float[] val_float_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        setTitle("인텐트로 데이터 받기");

        tv_show = (TextView)findViewById(R.id.tv_show);

        // 인텐트 인스턴스 생성 (get용)
        Intent intent = getIntent();

        if(intent != null) {
            // 기본형 data의 경우에는 getter에서 defaultValue를 지정해 주어야 한다.
            val_int = intent.getIntExtra("int_value", -1);
            val_str = intent.getStringExtra("str_value");// String은 참조타입이므로 defaultValue 필요없음
            val_bool = intent.getBooleanExtra("bool_vlaue", false);
            val_double = intent.getDoubleExtra("double_value", -1);
            val_float = intent.getFloatExtra("float_vlaue", -1);

            // 배열은 참조타입이기 때문에 defaultValue 필요없음
            val_int_arr = intent.getIntArrayExtra("int_arr_value");
            val_bool_arr = intent.getBooleanArrayExtra("bool_arr_value");
            val_double_arr = intent.getDoubleArrayExtra("double_arr_value");
            val_float_arr = intent.getFloatArrayExtra("float_arr_value");
        }


        // setText에서 배열은 인덱스를 이용하여 배열값을 하나씩 삽입
        tv_show.setText("전달받은 int값 : " + val_int + "\n"
                    + "전달받은 String 값 : " + val_str + "\n"
                    + "전달받은 boolean 값 : " + val_bool + "\n"
                    + "전달받은 double 값 : " + val_double + "\n"
                    + "전달받은 float 값 : " + val_float + "\n"+ "\n"

                    + "전달받은 intArray 값 : "
                            + val_int_arr[0] + "\t" + val_int_arr[1] + "\t" + val_int_arr[2] +"\n"
                    + "전달받은 boolArray 값 : "
                            + val_bool_arr[0] + "\t" + val_bool_arr[1] + "\t" + val_bool_arr[2] +"\n"
                    + "전달받은 doubleArray 값 : "
                            + val_double_arr[0] + "\t" + val_double_arr[1] + "\t" + val_double_arr[2] +"\n"
                    + "전달받은 floatArray 값 : "
                            + val_float_arr[0] + "\t"+ + val_float_arr[1] + "\t" + val_float_arr[2] +"\n");

    }
}

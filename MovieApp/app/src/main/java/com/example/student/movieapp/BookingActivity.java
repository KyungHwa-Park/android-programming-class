package com.example.student.movieapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {

    Button btn_date_picker, btn_time_picker, btn_reset_seats;
    Button btn_book_confirm, btn_book_cancel;
    DatePickerListener datePickerListener;
    TimePickerListener timePickerListener;
    SeekBar seekbar_seats;
    TextView tv_book_date, tv_book_time, tv_selected_seat;

    int movie_index;
    public static int selected_seats = 0;
    final static int ERROR = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent intent = getIntent();    // 전달받은 인텐트를 수신
        movie_index = intent.getIntExtra("movie_index", -1);

        if(movie_index != ERROR) {
            // 2. 컴포턴트(버튼)의 객체 만들기
            btn_date_picker = (Button)findViewById(R.id.btn_date_picker);
            btn_time_picker = (Button)findViewById(R.id.btn_time_picker);
            btn_reset_seats = (Button)findViewById(R.id.btn_reset_seats);

            btn_book_confirm = (Button)findViewById(R.id.btn_book_confirm);
            btn_book_cancel = (Button)findViewById(R.id.btn_book_cancel);

            tv_book_date = (TextView)findViewById(R.id.tv_book_date);
            tv_book_time = (TextView)findViewById(R.id.tv_book_time);
            tv_selected_seat = (TextView)findViewById(R.id.tv_selected_seat);

            seekbar_seats = (SeekBar)findViewById(R.id.seekbar_seats);

            // 4. 리스너 객체 만들기
            PickerBtnListener pickerBtnListener = new PickerBtnListener();
            datePickerListener = new DatePickerListener();
            timePickerListener = new TimePickerListener();
            NormalBtnListener normalBtnListener = new NormalBtnListener();
            SeekBarListener seekBarListener = new SeekBarListener();

            // 5. 리스너 객체를 컴포넌트에 등록하기
            btn_date_picker.setOnClickListener(pickerBtnListener);
            btn_time_picker.setOnClickListener(pickerBtnListener);
            btn_reset_seats.setOnClickListener(normalBtnListener);
            btn_book_confirm.setOnClickListener(normalBtnListener);
            btn_book_cancel.setOnClickListener(normalBtnListener);
            seekbar_seats.setOnSeekBarChangeListener(seekBarListener);


       } else {
            Toast.makeText(BookingActivity.this,
                    "동작 중에 오류가 발생하였습니다.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
   }

   // 3. 리스너 클래스 만들기
   class DatePickerListener implements DatePickerDialog.OnDateSetListener {

       @Override
       public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
           String temp = "예매일 : " +i+ "년 "+(i1+1)+"월 "+ i2+"일";
           tv_book_date.setText(temp);
       }
   }

    class TimePickerListener implements  TimePickerDialog.OnTimeSetListener {

        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            String temp = "예매시간 : " + i+"시 "+i1+"분";
            tv_book_time.setText(temp);
        }
    }

    class PickerBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.btn_date_picker:
                    // 6. 데이트피커(타임피커) 리스너 객체를 활용하여 데이터피커(타임피커)
                    // 다이얼로그를 호출
                    new DatePickerDialog(BookingActivity.this, datePickerListener,
                            2018,1,1).show();
                    break;
                case R.id.btn_time_picker:
                    new TimePickerDialog(BookingActivity.this, timePickerListener,
                            11, 43, false).show();
                    break;
            }
        }
    }

    class NormalBtnListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_reset_seats:
                    seekbar_seats.setProgress(0);
                    break;
                case R.id.btn_book_confirm:
                    Toast.makeText(BookingActivity.this,
                            selected_seats + "석 예매가 완료되었습니다.",
                            Toast.LENGTH_SHORT).show();
                    finish();
//                  Intent intent = new Intent(BookActivity.this,
//                           BookedActivity.class);
//                  startActivity(intent);
                    break;
                case R.id.btn_book_cancel:
                    Toast.makeText(BookingActivity.this,
                            "예매가 취소되었습니다.",
                            Toast.LENGTH_LONG).show();
                    finish();
                    break;

            }
        }
    }

    class SeekBarListener implements  SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            tv_selected_seat.setText("선택 좌석 : "+ i + "석");
            selected_seats = i;

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

}

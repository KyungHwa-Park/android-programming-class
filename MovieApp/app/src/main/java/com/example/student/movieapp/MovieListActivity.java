package com.example.student.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.movieapp.R;
import com.example.student.movieapp.adapters.ListViewAdapter;
import com.example.student.movieapp.adapters.ListViewAdapter2;
import com.example.student.movieapp.form.ListViewItem;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-13.
 */

public class MovieListActivity extends AppCompatActivity{

    ListView lv_movieList;
    ArrayAdapter adapter;

    final String[] list = {
            "블랙팬서", "궁합", "리틀포레스트", "월요일이 사라졌다"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // 2. 리스트뷰 객체 만들기
        lv_movieList = (ListView)findViewById(R.id.lv_movieList);

        // Adapter에게 전달할 데이터 구성하기
        ListViewItem item = new ListViewItem(
                "블랙팬서", "2018.03", R.drawable.black);

        ArrayList<ListViewItem> arrayList = new ArrayList<>();

        // 변수를 사용해서 list에 추가하는 방법
        arrayList.add(item);

        // list에 직접 추가하는 방법
        arrayList.add(
                new ListViewItem("궁합", "2018.01", R.drawable.match));

        arrayList.add(
                new ListViewItem("리틀 포레스트", "2018.11", R.drawable.little));

        arrayList.add(
                new ListViewItem("월요일이 사라졌다", "2018.02", R.drawable.monday));


        // 3. 리스트뷰에 Adapter 등록하기
        /**
         * 첫번째 매개변수 : 액티비티 정보(context 객체)
         * 두번째 매개변수 : 리스트뷰 항목의 레이아웃 (안드로이드 제공)
         * 세번째 매개변수 : 표시할 데이터들
         */
        adapter = new ArrayAdapter<String>(MovieListActivity.this,
                android.R.layout.simple_list_item_1, list);

        // 3-1. 새로 만든 Adapter를 등록한다.
        // 한 항목에 영화 하나 Adapter
//        ListViewAdapter listViewAdapter = new ListViewAdapter(
//                MovieListActivity.this, R.layout.listview_item, arrayList);
//
//        lv_movieList.setAdapter(listViewAdapter);

        // 한 항목에 영화 두 개 Adapter
        ListViewAdapter2 listViewAdapter2 = new ListViewAdapter2(
                MovieListActivity.this, R.layout.listview_item2, arrayList);

        lv_movieList.setAdapter(listViewAdapter2);


        // 4. 리스트뷰에 inItemClickListenter 등록하기
        lv_movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MovieListActivity.this, (position+1)+" 선택함", Toast.LENGTH_LONG).show();
            }
        });

    }
}

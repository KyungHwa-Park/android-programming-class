package com.example.student.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.movieapp.R;
import com.example.student.movieapp.form.ListViewItem;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> list;   // 자료를 저장하고 있는 ArrayList
    Context context;
    int item_layout;
    LayoutInflater layoutInflater;

    public ListViewAdapter(
            Context context,
            int item_layout,
            ArrayList<ListViewItem> list) {
        this.context = context;
        this.item_layout = item_layout;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    // list에서 item(항목)을 가져옴. position = list의 index
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    // position = list의 index = list item의 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(item_layout, parent, false);
        }

        ImageView img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
        img_thumb.setImageResource(list.get(pos).getImg_id());
        img_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "그림을 선택했습니다.",
                                Toast.LENGTH_LONG).show();
            }
        });

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        tv_title.setText(list.get(pos).getTitle());

        TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
        tv_date.setText(list.get(pos).getDate());

        // 텍스트뷰로 제목, 날짜 넣어보기
       return convertView;
    }
}

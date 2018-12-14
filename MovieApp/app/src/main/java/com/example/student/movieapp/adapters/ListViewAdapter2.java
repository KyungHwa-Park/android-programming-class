package com.example.student.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.movieapp.InfoActivity;
import com.example.student.movieapp.R;
import com.example.student.movieapp.form.ListViewItem;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-14.
 */

public class ListViewAdapter2 extends BaseAdapter {

    ArrayList<ListViewItem> list = new ArrayList<>();
    Context context;
    int item_layout;
    LayoutInflater layoutInflater;

    public ListViewAdapter2(
            Context context, int item_layout,
            ArrayList<ListViewItem> list) {
        this.list = list;
        this.context = context;
        this.item_layout = item_layout;
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(list.size() % 2 == 0) {
            return list.size()/2;
        } else {
            return list.size()/2 + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos_l = position*2;
        final int pos_r = position*2 + 1;

        if(convertView == null) {
            convertView = layoutInflater.inflate(item_layout, parent, false);
        }

        ImageView img_thumb = (ImageView)convertView.findViewById(R.id.img_thumb);
        img_thumb.setImageResource(list.get(pos_l).getImg_id());

        img_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        list.get(pos_l).getTitle()+"를(을) 선택했습니다.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("movie_index", pos_l);
                intent.putExtra("movie_title", list.get(pos_l).getTitle());
                intent.putExtra("movie_date", list.get(pos_l).getDate());
                intent.putExtra("movie_img_id", list.get(pos_l).getImg_id());
                context.startActivity(intent);
            }
        });

        TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
        tv_title.setText(list.get(pos_l).getTitle());

        TextView tv_date = (TextView)convertView.findViewById(R.id.tv_date);
        tv_date.setText(list.get(pos_l).getDate());

        ImageView iv_thumb2 = (ImageView) convertView.findViewById(R.id.img_thumb2);
        TextView tv_title2 = (TextView)convertView.findViewById(R.id.tv_title2);
        TextView tv_date2 = (TextView)convertView.findViewById(R.id.tv_date2);

        if(pos_r < list.size()) {
            iv_thumb2.setImageResource(list.get(pos_r).getImg_id());
            iv_thumb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,
                            list.get(pos_r).getTitle()+"를(을) 선택했습니다.",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("movie_index", pos_r);
                    intent.putExtra("movie_title", list.get(pos_r).getTitle());
                    intent.putExtra("movie_date", list.get(pos_r).getDate());
                    intent.putExtra("movie_img_id", list.get(pos_r).getImg_id());
                    context.startActivity(intent);
                }
            });

            tv_title2.setText(list.get(pos_r).getTitle());
            tv_date2.setText(list.get(pos_r).getDate());
        } else {
            iv_thumb2.setVisibility(View.GONE);
            tv_title2.setVisibility(View.GONE);
            tv_date2.setVisibility(View.GONE);
        }

        return convertView;
    }
}

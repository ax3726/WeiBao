package com.wb.weibao.utils.Timeline;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wb.weibao.R;
import com.wb.weibao.utils.picker.util.LogUtils;

import java.util.ArrayList;

/**
 * Created by akshaykale on 2017/08/18.
 */

/*
*
MIT License

Copyright (c) 2017 Akshay Kale

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
* */

class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewHolder> {


    ArrayList<TimelineObject> list;
    Context context;



    public VerticalRecyclerViewAdapter(Context context, ArrayList<TimelineObject> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_timeline_layout, parent, false);

        return new VerticalRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VerticalRecyclerViewHolder holder, int position) {

          holder.time.setText(list.get(position).getTimestamp());
          holder.time2.setText(list.get(position).getTimestamp2());

        Log.d("position===",""+position);
        Log.d("list.size()===",""+list.size());
          if(position==list.size()-1)
          {
              holder.timelineindicator_line.setVisibility(View.GONE);
          }else
              {
                  holder.timelineindicator_line.setVisibility(View.VISIBLE);
              }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView time, header,time2, header2;
        RelativeLayout timelineindicator_container;
        TextView timelineindicator_line;

        public VerticalRecyclerViewHolder(View view) {
            super(view);

            time = (TextView) view.findViewById(R.id.tv_timeline_time);
            header = (TextView) view.findViewById(R.id.tv_timeline_header);
            time2 = (TextView) view.findViewById(R.id.tv_timeline_time2);
            header2 = (TextView) view.findViewById(R.id.tv_timeline_header2);


            timelineindicator_container = (RelativeLayout) view.findViewById(R.id.container_timeline_indicator);
            timelineindicator_line = (TextView) view.findViewById(R.id.tv_timeline_indicator_line);




        }
    }


}

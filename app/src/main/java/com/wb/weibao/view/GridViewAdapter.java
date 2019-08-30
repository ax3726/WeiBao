package com.wb.weibao.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wb.weibao.R;

import java.util.List;

/**
 * gridView的adapter
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private int selectorPosition=-1;
    public GridViewAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_gridview, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.ll);
            mViewHolder.mtextView = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mtextView.setText(mList.get(position));
        //如果当前的position等于传过来点击的position,就去改变他的状态
        if (selectorPosition == position) {
            if(selectorPosition==0)
            {
                mViewHolder.mRelativeLayout.setBackgroundResource(R.drawable.grid_shap_two);
                mViewHolder.mtextView.setTextColor(Color.parseColor("#FF6666"));
                Drawable drawable =mContext.getResources().getDrawable(R.drawable.icon_anomaly_02);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mViewHolder.mtextView.setCompoundDrawables(null, drawable, null, null);
            }else
                {
                    mViewHolder.mRelativeLayout.setBackgroundResource(R.drawable.grid_shap_three);
                    mViewHolder.mtextView.setTextColor(Color.parseColor("#08b525"));
                    Drawable drawable =mContext.getResources().getDrawable(R.drawable.icon_normal_02);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mViewHolder.mtextView.setCompoundDrawables(null, drawable, null, null);
                }

        } else {
            //其他的恢复原来的状态
            mViewHolder.mRelativeLayout.setBackgroundResource(R.drawable.grid_shap_one);
            mViewHolder.mtextView.setTextColor(Color.parseColor("#999999"));
            if(position==0)
            {
                Drawable drawable =mContext.getResources().getDrawable(R.drawable.icon_anomaly_01);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mViewHolder.mtextView.setCompoundDrawables(null, drawable, null, null);
            }else
            {
                Drawable drawable =mContext.getResources().getDrawable(R.drawable.icon_normal_01);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mViewHolder.mtextView.setCompoundDrawables(null, drawable, null, null);
            }
        }
        return convertView;
    }


    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView mtextView;
        RelativeLayout mRelativeLayout;
    }
}

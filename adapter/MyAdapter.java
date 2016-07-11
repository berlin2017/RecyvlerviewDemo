package com.berlin.recyvlerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlin.recyvlerviewdemo.R;

import java.util.List;

/**
 * Created by berlin on 2016/6/28 0028.
 */
public class MyAdapter extends RecyclerView.Adapter{

    private List<String>list;
    private Context context;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setListener(View.OnClickListener clickListener, View.OnLongClickListener onLongClickListener){
        this.onClickListener = clickListener;
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).textView.setText(list.get(position));
//        ViewGroup.LayoutParams params = ((MyViewHolder)holder).textView.getLayoutParams();
//        params.height = params.width;
//        ((MyViewHolder)holder).textView.setLayoutParams(params);
        ((MyViewHolder)holder).textView.setTag(position);
        ((MyViewHolder)holder).textView.setOnClickListener(onClickListener);
        ((MyViewHolder)holder).textView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_home_textview);
        }

    }
}

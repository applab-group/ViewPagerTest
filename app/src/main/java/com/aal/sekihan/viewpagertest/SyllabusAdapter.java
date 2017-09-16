package com.aal.sekihan.viewpagertest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by sekihan on 2017/09/16.
 */

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusViewHolder> {
    private ArrayList<SyllabusListItem> data;

    public SyllabusAdapter(ArrayList<SyllabusListItem> data){
        this.data = data;
    }

    @Override
    public SyllabusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new SyllabusViewHolder(v);
    }

    /*
    TextView title;
    TextView tag;
    TextView desc;
    TextView name;
    ImageView icon;
    TextView screen_name;
    TextView text;*/

    @Override
    public void onBindViewHolder(SyllabusViewHolder holder, int position) {
        holder.tag.setText(this.data.get(position).getSubNumber());
        holder.desc.setText(this.data.get(position).getDay());
        holder.name.setText(this.data.get(position).getName());
        holder.screen_name.setText(this.data.get(position).getTeacher());
        holder.text.setText(this.data.get(position).getSubNumber());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

package com.aal.sekihan.viewpagertest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sekihan on 2017/09/16.
 */

public class SyllabusViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView tag;
    TextView desc;
    TextView name;
    ImageView icon;
    TextView screen_name;
    TextView text;

    public SyllabusViewHolder(View itemView){
        super(itemView);
        this.view = itemView;
        this.tag = (TextView) view.findViewById(R.id.tag);
        this.desc = (TextView) view.findViewById(R.id.desc);
        this.name = (TextView) view.findViewById(R.id.name);
        this.icon = (ImageView) view.findViewById(R.id.icon);
        this.screen_name = (TextView) view.findViewById(R.id.screen_name);
        this.text = (TextView) view.findViewById(R.id.text);
    }

}

package com.aal.sekihan.viewpagertest;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sekihan on 2017/06/27.
 */

public class TimeTableFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";
    TextView tv;
    Button bt;

    public static TimeTableFragment newInstance(@ColorRes int IdRes) {
        TimeTableFragment frag = new TimeTableFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        tv = new TextView(getActivity());
        tv.setText("登録されている時間割");
        bt = new Button(getActivity());
        bt.setText("時間割追加");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_main_linearlayout);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        linearLayout.addView(tv);
        linearLayout.addView(bt);

        bt.setOnClickListener(new SampleClickListener());

        return view;

    }

    class SampleClickListener implements View.OnClickListener
    {
        public void onClick(View v){
            tv.setText("登録しました");
        }
    }
}

package com.aal.sekihan.viewpagertest;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by sekihan on 2017/06/27.
 */

public class SyllabusFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";
    ArrayAdapter<String> adapter;
    AsyncNetworkTask task;

    public static SyllabusFragment newInstance(@ColorRes int IdRes){
        SyllabusFragment frag = new SyllabusFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_syllabus, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_main_linearlayout);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        ProgressBar progress = (ProgressBar)view.findViewById(R.id.progress);
        progress.setVisibility(ProgressBar.VISIBLE);

        task = new AsyncNetworkTask(view);
        task.execute("https://gs.okayama-u.ac.jp/campusweb/campussquare.do");


        final ArrayList<String> data = new ArrayList<>();
        data.add("情報数学");
        data.add("言語解析論");
        data.add("コンソメ");
        data.add("胡椒");
        data.add("胡椒");
        data.add("胡椒");
        data.add("胡椒");
        data.add("胡椒");
        

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        ListView list = (ListView) view.findViewById(R.id.syllabusList);
        list.setAdapter(adapter);
        return view;
    }

    private void exec_post() {}
}

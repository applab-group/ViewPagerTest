package com.aal.sekihan.viewpagertest;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sekihan on 2017/06/27.
 */

public class SyllabusFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";
    ArrayAdapter<String> adapter;
    private SyllabusUtil util;

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

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(util.getState()){
                    case BUSY:
                        Toast.makeText(getActivity(),"情報を取得しています。暫くお待ち下さい。",Toast.LENGTH_SHORT).show();
                        break;
                    case SEARCH_READY:
                        util.printParams();
                        util.setSearchParam("jikanwariShozokuCode", "0933");
                        util.search();
                        Toast.makeText(getActivity(), "検索しました", Toast.LENGTH_SHORT).show();
                        break;
                    case SEARCH_FINISHED:

                        RecyclerView rv = (RecyclerView)getActivity().findViewById(R.id.rv);

                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        rv.setLayoutManager(manager);

                        RecyclerView.Adapter adapter = new SyllabusAdapter(util.getTable());
                        rv.setAdapter(adapter);

                        Toast.makeText(getActivity(), "適用しました", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        util = new SyllabusUtil(view);
        util.initialize();

        return view;
    }



}

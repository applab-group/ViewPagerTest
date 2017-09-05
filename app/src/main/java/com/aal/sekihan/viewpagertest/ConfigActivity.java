package com.aal.sekihan.viewpagertest;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by sekihan on 2017/09/04.
 */

public class ConfigActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,new ConfigFragment());
        fragmentTransaction.commit();
    }
}

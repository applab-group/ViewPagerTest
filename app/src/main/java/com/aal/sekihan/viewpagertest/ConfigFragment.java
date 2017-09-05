package com.aal.sekihan.viewpagertest;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by sekihan on 2017/09/04.
 */

public class ConfigFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}

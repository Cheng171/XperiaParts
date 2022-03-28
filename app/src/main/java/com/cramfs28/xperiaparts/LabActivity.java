package com.cramfs28.xperiaparts;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class LabActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.lab);
    }
}

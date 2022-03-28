package com.cramfs28.xperiaparts;
import android.content.*;
import android.os.*;
import android.preference.*;


public class MainActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);
    }
}

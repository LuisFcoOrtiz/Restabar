package com.burguer.manrique.restabar.Preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.burguer.manrique.restabar.R;

/**
 * Created by onbh4 on 21/10/2017.
 */

public class Preferences extends PreferenceActivity {
    private EditTextPreference etIp;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }


}


/*
 * Copyright (C) 2018 Invictrix ROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.armory.settings.ui;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;

import android.support.v7.preference.Preference;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;

import com.android.settings.R;

public class LockScreenSettings extends InvictrixSettingsFragment implements
        Preference.OnPreferenceChangeListener {

	SwitchPreference mDoubleTapToSleepAnywhere;
        SwitchPreference mLockscreenVisualizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getResources().getString(R.string.lockscreen_settings_title);
        addPreferencesFromResource(R.xml.settings_lockscreen);

        mDoubleTapToSleepAnywhere = (SwitchPreference) findPreference("double_tap_sleep_anywhere");
        mDoubleTapToSleepAnywhere.setChecked(Settings.System.getInt(getContentResolver(),
                Settings.System.DOUBLE_TAP_SLEEP_ANYWHERE, 0) == 1);
        mDoubleTapToSleepAnywhere.setOnPreferenceChangeListener(this);

        mLockscreenVisualizer = (SwitchPreference) findPreference("lockscreen_visualizer");
        mLockscreenVisualizer.setChecked(Settings.Secure.getInt(getContentResolver(),
                Settings.Secure.LOCKSCREEN_VISUALIZER_ENABLED, 0) == 1);
        mLockscreenVisualizer.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference.equals(mDoubleTapToSleepAnywhere)) {
            boolean enabled = ((Boolean) newValue).booleanValue();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.DOUBLE_TAP_SLEEP_ANYWHERE, enabled ? 1 : 0);
            return true;
        }
        if (preference.equals(mLockscreenVisualizer)) {
            boolean enabled = ((Boolean) newValue).booleanValue();
            Settings.Secure.putInt(getContentResolver(),
                    Settings.Secure.LOCKSCREEN_VISUALIZER_ENABLED, enabled ? 1 : 0);
            return true;
        }
        return false;
    }
}

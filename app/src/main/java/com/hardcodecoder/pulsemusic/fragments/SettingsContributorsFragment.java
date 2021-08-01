package com.hardcodecoder.pulsemusic.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.hardcodecoder.pulsemusic.GlideApp;
import com.hardcodecoder.pulsemusic.GlideConstantArtifacts;
import com.hardcodecoder.pulsemusic.R;
import com.hardcodecoder.pulsemusic.activities.SettingsActivity;
import com.hardcodecoder.pulsemusic.interfaces.SettingsFragmentsListener;

public class SettingsContributorsFragment extends Fragment {


    public static final String TAG = "SettingsContributorsFragment";

    public static SettingsContributorsFragment getInstance() {
        return new SettingsContributorsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings_contributors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SettingsFragmentsListener mListener = (SettingsFragmentsListener) getActivity();
        if (mListener instanceof SettingsActivity)
            mListener.setToolbarTitle(R.string.contributors);

        view.findViewById(R.id.github_logo).setOnClickListener(v -> openLink("https://github.com/Sharath16113/Pulse-Music_player"));


        GlideApp.with(view)
                .load(getString(R.string.profile_icon_link))
                .error(R.drawable.def_avatar)
                .transform(GlideConstantArtifacts.getCircleCrop())
                .into((ImageView) view.findViewById(R.id.lead_developer_profile_icon));
    }

    private void openLink(@StringRes String linkId) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https:github.com/Sharath16113/Pulse-Music_player"));
        startActivity(i);
    }
}

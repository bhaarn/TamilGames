package com.padhuga.tamil.games.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padhuga.tamil.games.R;


public class HelpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        TextView helpText = rootView.findViewById(R.id.help_text);
        helpText.setText(R.string.help_text);
        return rootView;
    }
}

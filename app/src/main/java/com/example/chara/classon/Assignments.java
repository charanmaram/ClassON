package com.example.chara.classon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by charan on 4/19/2017.
 * <p>
 * Class for submission of the assignment to the professor in Student Interface
 */


public class Assignments extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Sets the title "assignments" to the activity
        getActivity().setTitle("assignments");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Returns the view of the fragment Assignments
        return inflater.inflate(R.layout.assignments, container, false);
    }

    // The method which starts execution first
    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        //This Intent is responsible for changing Assignments fragment to Upload Activity
        Intent uploadIntent = new Intent(getActivity(), uploadActivity.class);
        startActivity(uploadIntent);
    }
}
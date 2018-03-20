package com.example.chara.classon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;

import static com.example.chara.classon.R.id.editName;

/**
 * Created by charan on 4/9/2017.
 * <p>
 * Class for the home page of the student Interface
 */

public class Home extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Sets the name of the activity as Home
        getActivity().setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Returns View for the Fragment home
        View view = inflater.inflate(R.layout.home, container, false);

        final EditText Name = (EditText) view.findViewById(editName);

        // Url of the website to be shown in the home Fragment of Student Home Activity
        String url = "http://cosc5384.us/";
        WebView v = (WebView) view.findViewById(R.id.Wv);
        v.getSettings().setJavaScriptEnabled(true);
        v.loadUrl(url);
        return view;
    }

}

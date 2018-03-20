package com.example.chara.classon;

/**
 * Created by charan on 4/19/2017.
 * <p>
 * Class for Dispalying the feedback to professor from different students in Faculty Interface
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chara.classon.R.id.btnFacFeedback;
import static com.example.chara.classon.R.id.editName;
import static com.example.chara.classon.R.id.spinnerFacFeedback;


public class FacFeedback extends Fragment {


    // Declaration of the different widgets
    Spinner spinner;
    Button btnView;
    RequestQueue requestQueue;
    ArrayAdapter<CharSequence> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fac_feedback, container, false);

        // Initialization of the spinner
        spinner = (Spinner) view.findViewById(spinnerFacFeedback);

        // Initialization of the adapter which is responsible for viewing the options menu in spinner
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.weeks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Initializing the button
        btnView = (Button) view.findViewById(btnFacFeedback);
        requestQueue = Volley.newRequestQueue(getActivity());

        // Setting the onclick listener to view the feedback given by students
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String week = spinner.getSelectedItem().toString();
                String url = "http://charanmaram.com/action.php?action=showFeedTable&week=" + week;
                WebView vq = (WebView) view.findViewById(R.id.wv);
                vq.getSettings().setJavaScriptEnabled(true);
                vq.loadUrl(url);


            }
        });
        return view;
    }
}
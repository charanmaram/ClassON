package com.example.chara.classon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chara.classon.R.id.editComment;
import static com.example.chara.classon.R.id.editName;
import static com.example.chara.classon.R.id.ratingBar;
import static com.example.chara.classon.R.id.spinner;

/**
 * Created by charan on 4/9/2017.
 * <p>
 * Class for collecting the Feedback from Students in Student Interface
 */

public class Feedback extends Fragment {

    // Declaration of different widgets

    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter;
    RatingBar rb;
    EditText comment1;
    Button btnSubmit;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Sets the title of the page as Feedback
        getActivity().setTitle("Feedback");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Returns the view for the fragment feedback
        View view = inflater.inflate(R.layout.feedback, container, false);

        // Declaration of Different widgets
        spinner1 = (Spinner) view.findViewById(spinner);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.weeks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        rb = (RatingBar) view.findViewById(R.id.ratingBar);
        comment1 = (EditText) view.findViewById(R.id.editComment);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);

        // Setting onclick listener to collect the feedback from students
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String week = spinner1.getSelectedItem().toString();
                double rating1 = rb.getRating();
                final String rating = Double.toString(rating1);
                final String comment = comment1.getText().toString();

                // Response from the Database that feedback is successfully received
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getContext(), "Submitted Successfully!", Toast.LENGTH_LONG).show();

                                // Transition from Feedback fargment to home Fragment in Student Interface
                                Fragment fragment = new Home();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.content_frame, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                            } else {
                                // Alert shown when sending of feedback is failed
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("sending feedback failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Creating the instance for FeedbackUP class to add the respective attributes to database
                FeedbackUP feedbackUP = new FeedbackUP(week, rating, comment, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(feedbackUP);
            }
        });

        return view;
    }
}

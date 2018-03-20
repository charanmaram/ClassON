package com.example.chara.classon;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by charan on 4/18/2017.
 * <p>
 * Class to connect with the database for sending feedback
 */


public class FeedbackUP extends StringRequest {

    // Url for the php file for sending feedback
    private static final String REGISTER_REQUEST_URL = "http://charanmaram.com/Feedback.php";
    private Map<String, String> params;

    public FeedbackUP(String week, String rating, String comment, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("week", week);
        params.put("rating", rating);
        params.put("comment", comment);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

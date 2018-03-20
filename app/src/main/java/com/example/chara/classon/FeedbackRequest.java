package com.example.chara.classon;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by charan on 4/20/2017.
 */

/* getting Feedback Data from the database
 */

public class FeedbackRequest extends StringRequest {

    // Url to be called to get feedback into the faculty Interface
    private static final String FEEDBACK_REQUEST_URL = "http://charanmaram.com/action.php?action=showFeed";
    private Map<String, String> params;

    public FeedbackRequest(String week, Response.Listener<String> listener) {
        super(Method.POST, FEEDBACK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("week", week);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}



package com.example.chara.classon;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by charan on 4/9/2017.
 */

public class RegisterRequest extends StringRequest {

    // Establishing the connection between the application and the database via the respective file

    private static final String REGISTER_REQUEST_URL = "http://charanmaram.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

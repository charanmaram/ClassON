package com.example.chara.classon;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.chara.classon.R.id.buttonRegister;

/**
 * Created by charan on 4/9/2017.
 * <p>
 * Class to register the students for using the application
 */


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initializing different widgets for registering the students to the application

        final EditText editName = (EditText) findViewById(R.id.editName);
        final EditText editEmail = (EditText) findViewById(R.id.editEmail);
        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        Button buttonRegister1 = (Button) findViewById(buttonRegister);

        // Setting the On click listener to collect the respective details from the user and send it to the database

        buttonRegister1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name = editName.getText().toString();
                final String email = editEmail.getText().toString();
                final String username = editUsername.getText().toString();
                final String password = editPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                // if registeration is successful the user is automatically navigated to the login activity
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // Initializing the Registerrequest class to connect the registration activity with the database
                RegisterRequest registerRequest = new RegisterRequest(name, email, username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

    }
}

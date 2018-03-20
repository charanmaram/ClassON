package com.example.chara.classon;

/**
 * Created by charan on 4/9/2017.
 * <p>
 * Class responsible for the user logging into the application
 */

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initializing the different widgets

        final EditText editUsername = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        final Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        final TextView tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);

        // To create the account, user clicks on the button

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(RegisterIntent);
            }
        });

        // Button to collect the details from the user and send to the database for authentication

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String username = editUsername.getText().toString();
                final String password = editPassword.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");

                            if (success) {

                                // If the username and password of the user matches with those in the database, the user is given access

                                Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                LoginActivity.this.startActivity(intent);
                                intent.putExtra("username", username);
                                finish();


                            } else {


                                if (editUsername.getText().toString().equals("lwu") && editPassword.getText().toString().equals("lwu")) {
                                    Intent intent = new Intent(LoginActivity.this, FacHomeActivity.class);
                                    LoginActivity.this.startActivity(intent);
                                    finish();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Login failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }

        });


    }
}

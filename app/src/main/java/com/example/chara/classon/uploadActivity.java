package com.example.chara.classon;

/**
 * Created by charan on 4/19/2017.
 * <p>
 * Class to upload the files to database from the Student Interface
 */

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.security.AccessController.getContext;

public class uploadActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        button = (Button) findViewById(R.id.button);

        // Checking wheather there are permissions on the mobile to access the storage

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                return;
            }
        }

        // Calling the enable_button method

        enable_button();

    }

    // Method to pick the files from the storage

    private void enable_button() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(uploadActivity.this)
                        .withRequestCode(10)
                        .start();

            }
        });
    }


    // Requesting for the permission of the user to access the storage

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            enable_button();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    ProgressDialog progress;

    // After picking the files they are uploaded to the database

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {

            progress = new ProgressDialog(uploadActivity.this);
            progress.setTitle("Uploading");
            progress.setMessage("Please wait...");
            progress.show();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    File f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type = getMimeType(f.getPath());

                    String file_path = f.getAbsolutePath();

                    // Using the HTTP client to establish the connection

                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type", content_type)
                            .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                            .build();

                    Request request = new Request.Builder()

                            // Php file save_file is called to store file in the database

                            .url("http://charanmaram.com/save_file.php")
                            .post(request_body)
                            .build();


                    try {
                        Response response = client.newCall(request).execute();

                        if (response.isSuccessful()) {
                            Intent intent = new Intent(uploadActivity.this, StudentHomeActivity.class);
                            uploadActivity.this.startActivity(intent);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    progress.dismiss();


                }
            });

            t.start();


        }


    }


    // Getting the extension of the uploading file

    private String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }


    // The method takes back the user to Home Fragment on pressing of back button

    @Override
    public void onBackPressed() {
        Intent in = new Intent(uploadActivity.this, StudentHomeActivity.class);
        uploadActivity.this.startActivity(in);
        super.onBackPressed();
    }
}


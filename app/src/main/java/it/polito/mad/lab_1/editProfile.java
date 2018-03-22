package it.polito.mad.lab_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

public class editProfile extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 1;
    private String name;
    private String email;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


    }

    public void save_profile(View view){
        EditText editText_name = (EditText) findViewById(R.id.nameEditText);
        EditText editText_email = (EditText) findViewById(R.id.emailEditText);
        EditText editText_bio = (EditText) findViewById(R.id.bioEditText);

        name = editText_name.getText().toString();
        email = editText_email.getText().toString();
        bio = editText_bio.getText().toString();

        Button save_profile = (Button) findViewById(R.id.saveProfile);
        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileOutputStream nameOutputStream;
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("name", name);
                    jsonObject.put("email", email);
                    jsonObject.put("bio", bio);
                } catch (JSONException e) {
                    Log.e("Error while creating JSON", e.getMessage());
                }

                try {
                    nameOutputStream = openFileOutput("profileName.bin", Context.MODE_PRIVATE);
                    nameOutputStream.write(jsonObject.toString().getBytes());
                    nameOutputStream.close();
                }catch (Exception e){
                    Log.e("Output error", e.getMessage());
                }
            }
        });

    }

    public void editImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

}

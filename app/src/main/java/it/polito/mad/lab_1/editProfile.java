package it.polito.mad.lab_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.saveButton) {
            Log.d("Click event", "Button Save Profile clicked");

            EditText editText_name = (EditText) findViewById(R.id.nameEditText);
            EditText editText_email = (EditText) findViewById(R.id.emailEditText);
            EditText editText_bio = (EditText) findViewById(R.id.bioEditText);
            setName(editText_name.getText().toString());
            setEmail(editText_email.getText().toString());
            setBio(editText_bio.getText().toString());
            FileOutputStream nameOutputStream;
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("name", getName());
                jsonObject.put("email", getEmail());
                jsonObject.put("bio", getBio());
            } catch (JSONException e) {
                Log.e("Error while creating JSON", e.getMessage());
            }
            try {
                nameOutputStream = openFileOutput("profileName.bin", Context.MODE_PRIVATE);
                nameOutputStream.write(jsonObject.toString().getBytes());
                nameOutputStream.close();
                Log.d("File written", "File 'profileName.bin' written");
            }catch (Exception e){
                Log.e("Output error", e.getMessage());
            }
            editProfile.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

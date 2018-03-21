package it.polito.mad.lab_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class editProfile extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 1;
    private String name, email, bio;

    public String getName(){
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


    }

    public void save_profile(View view){
        EditText editText_name = (EditText) findViewById(R.id.editText_name);
        EditText editText_email = (EditText) findViewById(R.id.editText_email);
        EditText editText_bio = (EditText) findViewById(R.id.editText_bio);
        Button save_profile = (Button) findViewById(R.id.save_profile);
        //GETTER AND SETTERS!!!!!!!!!!111!!11!!!1!!
        name = editText_name.getText().toString();
        email = editText_email.getText().toString();
        bio = editText_bio.getText().toString();

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getApplicationContext().getFilesDir(), "profileName.bin");
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput("profileName.bin", Context.MODE_PRIVATE);
                    outputStream.write(getName().getBytes());
                    outputStream.close();
                }catch (Exception e){
                    System.err.println("Il file non si cosa");
                    e.printStackTrace();
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

package it.polito.mad.lab_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class showProfile extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView emailView = (TextView) findViewById(R.id.emailView);
        TextView bioView = (TextView) findViewById(R.id.bioView);

        String jsonString = "";
        JSONObject jsonObject;

        try {
            InputStream nameInputStream = getApplicationContext().openFileInput("profileName.bin");
            if (nameInputStream!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(nameInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String buildString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((buildString = bufferedReader.readLine())!=null){
                    stringBuilder.append(buildString);
                }
                nameInputStream.close();
                jsonString = stringBuilder.toString();
            }
        }catch (Exception e){
            Log.e("Input error", e.getMessage());
        }

        try {
            jsonObject = new JSONObject(jsonString);
            nameView.setText(jsonObject.get("name").toString());
            emailView.setText(jsonObject.get("email").toString());
            bioView.setText(jsonObject.get("bio").toString());
        }catch (Exception e){
            Log.e("Error while parsing JSON", e.getMessage());
        }
    }

    public void switchToEdit(View view){
        Intent intent = new Intent(this, editProfile.class);
        startActivity(intent);
    }
}

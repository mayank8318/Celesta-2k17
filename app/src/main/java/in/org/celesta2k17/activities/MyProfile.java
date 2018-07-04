package in.org.celesta2k17.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.org.celesta2k17.R;


/**
 * Created by mayank on 15/7/17.
 */

public class MyProfile extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
    }

    private void setView() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(getString(R.string.login_status), false)) {
            setContentView(R.layout.sign_in);
            Button buttonSignIn = findViewById(R.id.button_signin);
            Button buttonSignUp = findViewById(R.id.button_signup);

            buttonSignIn.setOnClickListener(v -> {
                Intent intent = new Intent(MyProfile.this, SignInActivity.class);
                startActivity(intent);
            });

            buttonSignUp.setOnClickListener(v -> {
                Intent intent = new Intent(MyProfile.this, RegisterActivity.class);
                startActivity(intent);
            });
//            Intent intent = new Intent(MyProfile.this, LoginActivity.class);
//                    startActivity(intent);
        } else {
            setContentView(R.layout.activity_register_signup_or_signin);
            TextView fullNameTextView = findViewById(R.id.fullName);
            TextView nameTextView = findViewById(R.id.nameTextView);
            TextView idTextView = findViewById(R.id.idValue);
            TextView collegeTextView = findViewById(R.id.collegeNameValue);
            TextView eventTextView = findViewById(R.id.eventsParticipatedValue);

            String full_name = sharedPreferences.getString(getString(R.string.full_name), "Mayank Vaidya");

            fullNameTextView.setText(sharedPreferences.getString(getString(R.string.full_name), "Mayank Vaidya"));
            String nameViewText = "" + Character.toUpperCase(full_name.charAt(0)) + Character.toUpperCase(full_name.charAt(full_name.indexOf(' ') + 1));
            nameTextView.setText(nameViewText);
            idTextView.setText(sharedPreferences.getString(getString(R.string.id), "12345"));
            collegeTextView.setText(sharedPreferences.getString(getString(R.string.college_name), "IIT Patna"));
            eventTextView.setText(sharedPreferences.getString(getString(R.string.event_participated), "-"));
            eventTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setView();
    }
}

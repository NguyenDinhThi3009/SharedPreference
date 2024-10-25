package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale;
    private Button btnConfirm;
    private TextView tvResult;
    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "GenderPref";
    private static final String PREF_KEY = "selectedGender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvResult = findViewById(R.id.tvResult);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadGenderFromPreferences();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGenderToPreferences();
            }
        });
    }

    private void saveGenderToPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int selectedId = radioGroupGender.getCheckedRadioButtonId();

        if (selectedId == R.id.radioMale) {
            editor.putString(PREF_KEY, "Nam");
        } else if (selectedId == R.id.radioFemale) {
            editor.putString(PREF_KEY, "Nữ");
        }
        editor.apply();
        loadGenderFromPreferences();
    }
    private void loadGenderFromPreferences() {
        String gender = sharedPreferences.getString(PREF_KEY, "Chưa chọn");
        tvResult.setText("Giới tính đã chọn: " + gender);
    }
}

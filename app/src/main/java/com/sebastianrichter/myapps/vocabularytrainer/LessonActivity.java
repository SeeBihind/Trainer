package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;

public class LessonActivity extends AppCompatActivity {

    private Button btn_okay;
    private Button btn_end;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        btn_okay = (Button) findViewById(R.id.btn_okay);
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLesson();
            }
        });

        btn_end = (Button) findViewById(R.id.btn_end);
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBackToLessonSetting();
            }
        });
    }

    private void openBackToLessonSetting(){
        Intent intent = new Intent(this, StartSettingsActivity.class);
        startActivity(intent);
    }

    private void nextLesson(){

    }

    @Override
    protected void onStop(){
        super.onStop();
        // Datei öffnen mit ZUgriffsrechten;
        SharedPreferences mySPR = getSharedPreferences("config" + MainActivity.activeUser,0);
        // Editor Klasse initialisieren
        SharedPreferences.Editor editor = mySPR.edit();
        editor.putString("choosenCategory", Config.choosenCategory);
        editor.putInt("counterQuestions",Config.counterQuestions);
        editor.putInt("choosenLvl",Config.choosenLvl);
        editor.putString("spinnerVocabularySetupNativeLanguage", Config.spinnerVocabularySetupNativeLanguage);
        editor.putBoolean("saveSettings",Config.saveSettings);

        editor.commit();
    }
}
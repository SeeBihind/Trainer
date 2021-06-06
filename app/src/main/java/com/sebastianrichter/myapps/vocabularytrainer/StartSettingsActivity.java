package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StartSettingsActivity extends AppCompatActivity {

    private Button btn_back, btn_startleasson;
    private Spinner sP_LearnMethod, spinner_language;
    private SeekBar seekBarQuestions, seekBarDifficulty;
    private TextView textViewSeekBarQuestions, textViewSeekBarDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startsettings);
        btn_back = (Button) findViewById(R.id.btn_back_main);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        // -------------------------------------------------------------------------------------------------------------------------------
        // Spinner LearnMethod
        sP_LearnMethod = (Spinner) findViewById(R.id.sP_LearnMethod);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_LearnMethod, android.R.layout.simple_selectable_list_item);
        sP_LearnMethod.setAdapter(adapter);

        // -------------------------------------------------------------------------------------------------------------------------------
        // Spinner language
        spinner_language = (Spinner) findViewById(R.id.spinner_language);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, Config.languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_language.setAdapter(adapter2);
        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btn_startleasson.setActivated(true);
                Context context = getApplicationContext();
                if(spinner_language.getSelectedItem().equals(Config.spinnerVocabularySetupNativeLanguage)){
                    Toast toast = Toast.makeText(context,R.string.sP_LanguageStartSettings, Toast.LENGTH_SHORT);
                    toast.show();
                    btn_startleasson.setActivated(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // -------------------------------------------------------------------------------------------------------------------------------
        // Lesson start
        btn_startleasson = (Button) findViewById(R.id.btn_start_leasson);
        btn_startleasson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_startleasson.isActivated()){
                    openLessonActivity();
                }else{
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context,R.string.sP_LanguageStartSettings, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        // -------------------------------------------------------------------------------------------------------------------------------
        //
        textViewSeekBarQuestions = (TextView) findViewById(R.id.textView7);
        seekBarQuestions = (SeekBar)findViewById(R.id.seekBarQuestions);
        seekBarQuestions.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBarQuestions.setText("" + progress + " Questions");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if(Config.saveSettings) {
            seekBarQuestions.setProgress(Config.counterQuestions);
            //TODO
            // Anpassen
            //spinner_category.setSelection(Config.choosenCategory);
            spinner_language.setSelection(Config.choosenLanguage);
        }

        // -------------------------------------------------------------------------------------------------------------------------------
        //
        textViewSeekBarDifficulty = (TextView) findViewById(R.id.textViewStatusSeekbarDifficulty);
        seekBarDifficulty = (SeekBar) findViewById(R.id.seekBarDifficulty);
        seekBarDifficulty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekBarDifficulty.setText("" + progress +"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }



    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //System.exit(0);
    }
    private void openLessonActivity(){
        Intent intent = new Intent(this, LessonActivity.class);
        Config.counterQuestions = seekBarQuestions.getProgress();
        //TODO
        // Anpassen
        //Config.choosenCategory = spinner_category.getSelectedItemPosition();
        Config.choosenLanguage = spinner_language.getSelectedItemPosition();
        startActivity(intent);
    }
}
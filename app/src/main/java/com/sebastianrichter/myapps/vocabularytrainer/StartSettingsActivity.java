package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StartSettingsActivity extends AppCompatActivity {

    private Button btn_back, btn_startleasson;
    private Spinner spinner_category, spinner_lvl;
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
        // Spinner Category
        ArrayList<String> str = new ArrayList<String>();

        spinner_category = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_category, android.R.layout.simple_selectable_list_item);
        spinner_category.setAdapter(adapter);
        
        //spinner_category.setSelection(2);
        // -------------------------------------------------------------------------------------------------------------------------------
        // Spinner lvl
        spinner_lvl = (Spinner) findViewById(R.id.spinner_lvl);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_lvl, android.R.layout.simple_selectable_list_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lvl.setAdapter(adapter2);

        // -------------------------------------------------------------------------------------------------------------------------------
        // Lesson start
        btn_startleasson = (Button) findViewById(R.id.btn_start_leasson);
        btn_startleasson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLessonActivity();
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

        if(Config.saveSettings == true) {
            seekBarQuestions.setProgress(Integer.valueOf(Config.counterQuestions));
            //TODO
            // Anpassen
            //spinner_category.setSelection(Config.choosenCategory);
            spinner_lvl.setSelection(Config.choosenLvl);
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
        Config.choosenLvl = spinner_lvl.getSelectedItemPosition();
        startActivity(intent);
    }
}
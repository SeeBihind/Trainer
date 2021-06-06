package com.sebastianrichter.myapps.vocabularytrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;


public class VocabularySetupActivity extends AppCompatActivity {

    private AutoCompleteTextView /*autoCompleteTextViewVocabulary1, autoCompleteTextViewVocabulary2,*/
            autoCompleteTextViewSampleSentence1, autoCompleteTextViewSampleSentence2;

    private EditText textInputEditVocabulary1, textInputEditVocabulary2,editTextSampleSentence1,editTextSampleSentence2;
    private Spinner spinnerVocabularySetupForeignLanguage, spinnerVocabularySetupCategory;
    private Button btnBack, btnAddVocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_setup);

        spinnerVocabularySetupForeignLanguage = (Spinner) findViewById(R.id.spinnerVocabularySetupLanguage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_selectable_list_item,Config.languages);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_languages, android.R.layout.simple_selectable_list_item );
        spinnerVocabularySetupForeignLanguage.setAdapter(adapter);
        for(int i = 0;i< Config.languages.size(); i++)
            if(Config.languages.get(i).equals(Config.spinnerVocabularySetupForeignLanguage))
                spinnerVocabularySetupForeignLanguage.setSelection(i);
        spinnerVocabularySetupForeignLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Config.spinnerVocabularySetupForeignLanguage = spinnerVocabularySetupForeignLanguage.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerVocabularySetupCategory = (Spinner) findViewById(R.id.spinnerVocabularySetupCategory);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_category, android.R.layout.simple_selectable_list_item);
        spinnerVocabularySetupCategory.setAdapter(adapter2);

        btnBack = (Button) findViewById(R.id.buttonBackFromVocabulary);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Config.saveSettings == true){
                    SharedPreferences mySPR = getSharedPreferences("config"+ MainActivity.activeUser,0);
                    SharedPreferences.Editor editor = mySPR.edit();
                    editor.putString("spinnerVocabularySetupForeignLanguage", Config.spinnerVocabularySetupForeignLanguage);
                    editor.commit();
                }
                openMainActivity();
            }
        });

        textInputEditVocabulary1 = (EditText) findViewById(R.id.editTextVocabulary1);
        textInputEditVocabulary2 = (EditText) findViewById(R.id.editTextVocabulary2);
        editTextSampleSentence1 = (EditText) findViewById(R.id.editTextSampleSentence1);
        editTextSampleSentence2 = (EditText) findViewById(R.id.editTextSampleSentence2);

        btnAddVocabulary = (Button) findViewById(R.id.btnAddVocabulary);
        btnAddVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!textInputEditVocabulary1.getText().toString().equals("")) &&
                        (!textInputEditVocabulary2.getText().toString().equals(""))){
                    VokabelNeu.allVocabularies.add(new VokabelNeu(
                            textInputEditVocabulary1.getText().toString(),
                            textInputEditVocabulary2.getText().toString(),
                            editTextSampleSentence1.getText().toString(),
                            editTextSampleSentence2.getText().toString(),
                            spinnerVocabularySetupForeignLanguage.getSelectedItem().toString(),
                            Config.spinnerVocabularySetupNativeLanguage,
                            spinnerVocabularySetupCategory.getSelectedItem().toString(),
                            5
                            ));
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, getResources().getString(R.string.str_Toast_OK),
                            Toast.LENGTH_SHORT);
                    toast.show();
                    refreshEditText();
                    writeVocabulary();
                } else {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, getResources().getString(R.string.str_Toast_Err),
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        Button btn_StartLexicon = (Button) findViewById(R.id.btn_VocabularyLexicon);
        btn_StartLexicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivityLexicon();
            }
        });
    }
    private void openActivityLexicon(){
        Intent intent = new Intent(this, LexiconActivity.class);
        startActivity(intent);
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Refresh alle Textviews & EditTextboxs
    private void refreshEditText(){
        textInputEditVocabulary1.setText("");
        textInputEditVocabulary2.setText("");
        editTextSampleSentence1.setText("");
        editTextSampleSentence2.setText("");
    }

    // Save User Vocabulary
    private void writeVocabulary(){
        SharedPreferences test = getSharedPreferences("testanlage",0);
        SharedPreferences.Editor editor = test.edit();
        Gson gson = new Gson();
        String json = gson.toJson(VokabelNeu.allVocabularies);
        editor.putString(Config.lastUser,json);
        editor.apply();
    }
/*
    // Save User Vocabulary
    private void writeVocabulary(){
        SharedPreferences test = getSharedPreferences("testanlage",0);
        SharedPreferences.Editor editor = test.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Vokabel.allVocabularies);
        editor.putString("liste",json);
        editor.apply();
    }

 */
}
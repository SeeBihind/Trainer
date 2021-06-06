package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;


public class SetupActivity extends AppCompatActivity {

    private Switch switch_saveSettings;
    private Spinner spinnerSetupNativeLanguage, sP_SetupUser;
    private EditText eT_UserInput;

private static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        Button btn_backToMain = (Button) findViewById(R.id.btn_backmain);
        btn_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMain();
            }
        });

        // -----------------------------------------------------------------------------------------
        // Create Samples
        Button btn_createNewSamples = (Button) findViewById(R.id.btn_createsample);
        btn_createNewSamples.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createSample();
            }
        });
        // -----------------------------------------------------------------------------------------
        // Remove all Samples
        Button btn_deleteAllVC = (Button) findViewById(R.id.btn_deletevocabularies);
        btn_deleteAllVC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deleteAllVC();
            }
        });
        // -----------------------------------------------------------------------------------------
        // Save Setting
        switch_saveSettings = (Switch) findViewById(R.id.switch_SaveSetup);
        switch_saveSettings.setChecked(Config.saveSettings);
        switch_saveSettings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch_saveSettings.isChecked())
                    Config.saveSettings = true;
                 else
                    Config.saveSettings = false;
                switch_saveSettings.setChecked(Config.saveSettings);
            }
        });
        // -----------------------------------------------------------------------------------------
        //
        spinnerSetupNativeLanguage =(Spinner) findViewById(R.id.spinner_nativelanguage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_selectable_list_item,Config.languages);
        spinnerSetupNativeLanguage.setAdapter(adapter);
        for(int i =0; i < Config.languages.size();i++)
            if(Config.languages.get(i).equals(Config.spinnerVocabularySetupNativeLanguage))
                spinnerSetupNativeLanguage.setSelection(i);

        spinnerSetupNativeLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Config.spinnerVocabularySetupNativeLanguage = spinnerSetupNativeLanguage.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // -----------------------------------------------------------------------------------------
        // Spinner User
        sP_SetupUser = (Spinner) findViewById(R.id.sP_SetupUser);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>
                (this,  android.R.layout.simple_spinner_item, Config.addedUsers);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sP_SetupUser.setAdapter(adapter3);
        // Set Spinner with last user
        for(int i = 0; i < Config.addedUsers.size(); i++)
            if(Config.addedUsers.get(i).equals(Config.lastUser))
                sP_SetupUser.setSelection(i);
        sP_SetupUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Config.lastUser = sP_SetupUser.getSelectedItem().toString();
                loadConfig();
                for(int i =0;i< Config.languages.size();i++){
                    if(Config.languages.get(i).equals(Config.spinnerVocabularySetupNativeLanguage)){
                        spinnerSetupNativeLanguage.setSelection(i);
                    }
                }




                   if(Config.LOGGING) Log.d(Config.LOGTAG,"Load Vocabularys ... ");
                   SharedPreferences sharedPreferences = getSharedPreferences("testanlage", 0);
                   Gson gson = new Gson();
                   String json = sharedPreferences.getString(Config.lastUser, null);
                   Type type = new TypeToken<ArrayList<VokabelNeu>>() {}.getType();
                   VokabelNeu.allVocabularies = gson.fromJson(json, type);
                   if (VokabelNeu.allVocabularies == null)
                       VokabelNeu.allVocabularies = new ArrayList<>();

                   if(Config.LOGGING)Log.d(Config.LOGTAG,"Load Vocabularys ... DONE");






           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
        });


        // -----------------------------------------------------------------------------------------
        // Spinner User
        eT_UserInput = (EditText) findViewById(R.id.eT_UserInput);

        // -----------------------------------------------------------------------------------------
        // Add User
        Button btn_addUser = (Button) findViewById(R.id.btn_addUser);
        btn_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                sP_SetupUser.setSelection(Config.addedUsers.size());
                adapter3.notifyDataSetChanged();
            }
        });
        // -----------------------------------------------------------------------------------------
        // Delete User
        Button btn_deleteUser = (Button) findViewById(R.id.btn_deleteUser);
        btn_deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
                adapter3.notifyDataSetChanged();
            }
        });
        // -----------------------------------------------------------------------------------------
        // Save Userconfig
        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfig();
            }
        });
    }

    private void addUser(){
        Context context = getApplicationContext();
        String userInput = eT_UserInput.getText().toString();
        if(!userInput.equals("")){
            Toast toast = Toast.makeText(context, getResources()
                    .getString(R.string.str_Toast_IOUserInput), Toast.LENGTH_SHORT);
            toast.show();
            createUserConfig(userInput);
            eT_UserInput.setText("");
        } else {
            Toast toast = Toast.makeText(context, getResources()
                    .getString(R.string.str_Toast_NoUserInput), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void deleteUser(){
        if(sP_SetupUser.getSelectedItemPosition() >= 0){
            for(int i =0;i<Config.addedUsers.size();i++){
                if(Config.addedUsers.get(i).equals(sP_SetupUser.getSelectedItem())){
                    Config.addedUsers.remove(i);
                    UserConfig.list.remove(i);
                    saveConfig();
                }
            }
        }
    }

    private void createUserConfig(String userInput){
        UserConfig.list.add(new UserConfig(0,"",0,"","",true, userInput));
        Config.addedUsers.add(userInput);
    }

    private void lastUser(){
        SharedPreferences mySPR = getSharedPreferences("defaultConfig",0);
        // Editor Klasse initialisieren
        SharedPreferences.Editor editor = mySPR.edit();
        editor.putString("lastUser", Config.lastUser);
        editor.commit();
    }

    private void saveConfig(){
        for(int i=0; i< UserConfig.list.size();i++){
            if(UserConfig.list.get(i).getUserName().equals(Config.lastUser)) {
                Config.spinnerVocabularySetupNativeLanguage = spinnerSetupNativeLanguage.getSelectedItem().toString();
                Config.saveSettings = switch_saveSettings.isChecked();
                Config.userName = UserConfig.list.get(i).getUserName();

                UserConfig.list.get(i).setSpinnerVocabularySetupNativeLanguage(Config.spinnerVocabularySetupNativeLanguage);
                UserConfig.list.get(i).setSaveSettings(Config.saveSettings);
            }
        }
       setSharedPreferences();
    }
    private void setSharedPreferences(){
        SharedPreferences testneu = getSharedPreferences("neueCFG",0);
        SharedPreferences.Editor editor2 = testneu.edit();
        Gson gsons = new Gson();
        String jsons = gsons.toJson(UserConfig.list);
        editor2.putString("neuec",jsons);
        editor2.apply();
    }

    private void loadConfig(){
        for(int i=0; i< UserConfig.list.size();i++){
            if(UserConfig.list.get(i).getUserName().equals(Config.lastUser)) {
                // Set Config
                Config.spinnerVocabularySetupNativeLanguage = UserConfig.list.get(i).getSpinnerVocabularySetupNativeLanguage();
                Config.spinnerVocabularySetupForeignLanguage = UserConfig.list.get(i).getSpinnerVocabularySetupForeignLanguage();
                Config.counterQuestions = UserConfig.list.get(i).getCounterQuestions();
                Config.choosenCategory = UserConfig.list.get(i).getChoosenCategory();
                Config.choosenLvl = UserConfig.list.get(i).getChoosenLvl();
                Config.saveSettings = UserConfig.list.get(i).getSaveSettings();
                Config.userName = UserConfig.list.get(i).getUserName();

                // Set Buttons
                for(int j = 0; j <Config.languages.size();j++)
                    if(Config.languages.get(j).equals(Config.spinnerVocabularySetupNativeLanguage))
                        spinnerSetupNativeLanguage.setSelection(j);
                switch_saveSettings.setChecked(Config.saveSettings);
            }
        }
        loadSharedPreferences();
        lastUser();
    }
    private void loadSharedPreferences(){
        SharedPreferences config = getSharedPreferences("config" + MainActivity.activeUser,0);
        Config.counterQuestions = config.getInt("counterQuestions",0);
        Config.choosenCategory = config.getString("choosenCategory","");
        Config.choosenLvl = config.getInt("choosenLvl",0);
        Config.saveSettings = config.getBoolean("saveSettings",true);
        Config.spinnerVocabularySetupNativeLanguage = config.getString("spinnerVocabularySetupNativeLanguage", "");
        Config.spinnerVocabularySetupForeignLanguage = config.getString("spinnerVocabularySetupForeignLanguage", "");
    }

    private void openActivityMain(){
        saveConfig();
        lastUser();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void deleteAllVC(){
        SharedPreferences sp = getSharedPreferences("testanlage",0);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
        Vokabel.allVocabularies.clear();
    }

    private static void createSample() {
        VokabelNeu.createSamples();
    }



}
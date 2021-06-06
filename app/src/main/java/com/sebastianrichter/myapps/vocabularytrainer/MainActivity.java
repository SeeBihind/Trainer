package com.sebastianrichter.myapps.vocabularytrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn_setup, btn_exit, btn_start, btn_vocabularySetup;
    private ImageView imgView;
    private static int appActiv = 0;
    public static String activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Config.LOGGING)Log.d(Config.LOGTAG,"MainActivity starts ...");

        // ####################
        if(appActiv == 0){

            readConfig();
            loadData();
            // Set Languages
            setLanguages();
        }
        // Nur zu Info Anzahl Vokabeln
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, String.valueOf(VokabelNeu.allVocabularies.size()), Toast.LENGTH_SHORT);
        toast.show();

        // Nur zu Info Anzahl Vokabeln
        Toast toasts = Toast.makeText(context, Config.lastUser, Toast.LENGTH_SHORT);
        toasts.show();

        setContentView(R.layout.activity_main);

        btn_setup = (Button) findViewById(R.id.btn_Setup);
        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySetup();
            }
        });

        btn_exit = (Button) findViewById(R.id.btn_Exit);
        btn_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityExit();
            }
        });

        btn_start = (Button) findViewById(R.id.btn_Start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityStart();
            }
        });

        btn_vocabularySetup = (Button) findViewById(R.id.btn_vocabularySetup);
        btn_vocabularySetup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openActivityVocabularySetup();}
        });

        imgView = (ImageView) findViewById(R.id.imageView2);
        imgView.setImageResource(R.drawable.avatarfrau300x300);
    }

    private void openActivityStart(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Start ActivityStart... ");
        Intent intent = new Intent(this, StartSettingsActivity.class);
        startActivity(intent);
    }

    private void openActivitySetup(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Start ActivitySetup... ");
        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
    }
    private void openActivityExit(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Exit App... ");
        MainActivity.this.finish();

    }
    private void openActivityVocabularySetup(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Start ActivityVocabularySetup... ");
        Intent intent = new Intent(this, VocabularySetupActivity.class);
        startActivity(intent);

    }

    private void readConfig(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Read Config ...");
        // Read default config
        SharedPreferences config = getSharedPreferences("defaultConfig",0);
        Config.lastUser = config.getString("lastUser","default");


        SharedPreferences sharedPreferences2 = getSharedPreferences("neueCFG", 0);
        Gson gson2 = new Gson();
        String json2 = sharedPreferences2.getString("neuec", null);
        Type type2 = new TypeToken<ArrayList<UserConfig>>() {}.getType();
        UserConfig.list = gson2.fromJson(json2, type2);
        if (UserConfig.list == null)
            UserConfig.list = new LinkedList<>();

        for(int i = 0; i< UserConfig.list.size(); i++){
            Config.addedUsers.add(UserConfig.list.get(i).getUserName());
            if(Config.lastUser.equals(UserConfig.list.get(i).getUserName())){
                Config.choosenCategory = UserConfig.list.get(i).getChoosenCategory();
                Config.choosenLvl = UserConfig.list.get(i).getChoosenLvl();
                Config.saveSettings = UserConfig.list.get(i).getSaveSettings();
                Config.spinnerVocabularySetupForeignLanguage = UserConfig.list.get(i).getSpinnerVocabularySetupForeignLanguage();
                Config.spinnerVocabularySetupNativeLanguage = UserConfig.list.get(i).getSpinnerVocabularySetupNativeLanguage();
            }
        }
        appActiv= 1;
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Read Config ... DONE");
    }

    private void setLanguages(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Set Languages ... ");
        Config.languages.add("Deutsch");
        Config.languages.add("Englisch");
        Config.languages.add("Französisch");
        Config.languages.add("Dänisch");
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Set Languages ... DONE");
    }

    /*
    private void loadData(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Load Vocabularys ... ");
        SharedPreferences sharedPreferences = getSharedPreferences("testanlage", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("liste", null);
        Type type = new TypeToken<ArrayList<Vokabel>>() {}.getType();
        Vokabel.allVocabularies = gson.fromJson(json, type);
        if (Vokabel.allVocabularies == null)
            Vokabel.allVocabularies = new LinkedList<>();
        appActiv = 1;
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Load Vocabularys ... DONE");
    }

     */

    private void loadData(){
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Load Vocabularys ... ");
        SharedPreferences sharedPreferences = getSharedPreferences("testanlage", 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Config.lastUser, null);
        Type type = new TypeToken<ArrayList<VokabelNeu>>() {}.getType();
        VokabelNeu.allVocabularies = gson.fromJson(json, type);
        if (VokabelNeu.allVocabularies == null)
            VokabelNeu.allVocabularies = new ArrayList<>();
        appActiv = 1;
        if(Config.LOGGING)Log.d(Config.LOGTAG,"Load Vocabularys ... DONE");
    }










}
package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Config {

    public static int counterQuestions;
    public static String choosenCategory;
    public static int choosenLvl;
    public static String spinnerVocabularySetupNativeLanguage;
    public static int sPVucabularySetupNativeLanguage;
    public static String spinnerVocabularySetupForeignLanguage;
    public static int sPVucabularySetupForeeignLanguage;
    public static boolean saveSettings;
    public static String lastUser;
    public static String userName;
    public static final String LOGTAG = "VocbularyTrainer";
    public static final boolean LOGGING = true;
    public static ArrayList<String> addedUsers = new ArrayList();
    public static ArrayList<String> languages = new ArrayList<String>();




    //public static Context context;
/*
Config(Context context){
    this.context = context;
}

    public static void setConfig(JSONObject userData) throws JSONException {
        Config.counterQuestions = Integer.parseInt(userData.getString("counterQuestions"));
        Config.choosenCategory = Integer.parseInt(userData.getString("choosenCategory"));
        Config.choosenLvl = Integer.parseInt(userData.getString("choosenLvl"));
        Config.saveSettings = Boolean.parseBoolean(userData.getString("saveSettings"));
    }

    public static void readConfig(){
        try {
            JSONObject jsonObject = new JSONObject(readFromJASON());
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            JSONObject userData = jsonArray.getJSONObject(0);
            Config.setConfig(userData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String readFromJASON() {
        String json = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("user.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeConfig(){
    try{
        AssetManager assetManager = context.getAssets();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("counterQuestions",Config.counterQuestions);
        jsonObject.put("choosenCategory",Config.choosenCategory);
        jsonObject.put("choosenLvl",Config.choosenLvl);
        jsonObject.put("saveSettings",Config.saveSettings);
    } catch (Exception e){
        e.printStackTrace();
    }





    }




*/





}









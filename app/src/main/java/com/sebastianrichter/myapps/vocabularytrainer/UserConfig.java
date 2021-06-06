package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserConfig{

    // ------------------------------------------------------------------------
    //
    private int counterQuestions;
    private String choosenCategory;
    private int choosenLvl;
    private String spinnerVocabularySetupNativeLanguage;
    private String spinnerVocabularySetupForeignLanguage;
    private boolean saveSettings;
    private String userName;
    public static String lastUser;
    public static List<UserConfig> list = new LinkedList<UserConfig>();

    // ------------------------------------------------------------------------
    //
    UserConfig(int cQ, String cC, int cL, String sVSNL, String sVSFL, boolean sS, String uN) {
        setCounterQuestions(cQ);
        setChoosenCategory(cC);
        setChoosenLvl(cL);
        setSpinnerVocabularySetupNativeLanguage(sVSNL);
        setSpinnerVocabularySetupForeignLanguage(sVSFL);
        setSaveSettings(sS);
        setUserName(uN);
    }

    // ------------------------------------------------------------------------
    //
    public int getCounterQuestions() {
        return counterQuestions;
    }

    public void setCounterQuestions(int counterQuestions) {
        this.counterQuestions = counterQuestions;
    }

    public String getChoosenCategory() {
        return choosenCategory;
    }

    public void setChoosenCategory(String choosenCategory) {
        this.choosenCategory = choosenCategory;
    }

    public int getChoosenLvl() {
        return choosenLvl;
    }

    public void setChoosenLvl(int choosenLvl) {
        this.choosenLvl = choosenLvl;
    }

    public String getSpinnerVocabularySetupNativeLanguage() {
        return spinnerVocabularySetupNativeLanguage;
    }

    public void setSpinnerVocabularySetupNativeLanguage(String spinnerVocabularySetupNativeLanguage) {
        this.spinnerVocabularySetupNativeLanguage = spinnerVocabularySetupNativeLanguage;
    }

    public String getSpinnerVocabularySetupForeignLanguage() {
        return spinnerVocabularySetupForeignLanguage;
    }

    public void setSpinnerVocabularySetupForeignLanguage(String spinnerVocabularySetupForeignLanguage) {
        this.spinnerVocabularySetupForeignLanguage = spinnerVocabularySetupForeignLanguage;
    }

    public boolean getSaveSettings() {
        return saveSettings;
    }

    public void setSaveSettings(boolean saveSettings) {
        this.saveSettings = saveSettings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



}

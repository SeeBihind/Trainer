package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vokabel {

        private String  vocabularyLanguage1,
                        vocabularylanguage2,
                        sampleSentenceLanguage1,
                        sampleSentenceLanguage2,
                        category, foreignLanguage, nativeLanguage;


        public static List<Vokabel> allVocabularies = new LinkedList<Vokabel>();

        public static int vocabularyIndex;

        Vokabel(String vocabularyLanguage1,
                String vocabularylanguage2,
                String sampleSentenceLanguage1,
                String sampleSentenceLanguage2,
                String foreignLanguage,
                String nativeLanguage,
                String category) {

            setVocabularyLanguage1(vocabularyLanguage1);
            setVocabularylanguage2(vocabularylanguage2);
            setSampleSentenceLanguage1(sampleSentenceLanguage1);
            setSampleSentenceLanguage2(sampleSentenceLanguage2);
            setForeignLanguage(foreignLanguage);
            setNativeLanguage(nativeLanguage);
            setCategory(category);

        }
        // ------------------------------------------------------------

        public String getVocabularyLanguage1() {
        return vocabularyLanguage1;
    }
        private void setVocabularyLanguage1(String vokabel1) {
            this.vocabularyLanguage1 = vokabel1;
        }

        public String getVocabularylanguage2() {
            return vocabularylanguage2;
        }
        private void setVocabularylanguage2(String vocabularylanguage2) {
            this.vocabularylanguage2 = vocabularylanguage2;
        }

        public static int getVocabularyIndex() {
            return vocabularyIndex;
        }
        private static void setVocabularyIndex(int vocabularyIndex) {
            Vokabel.vocabularyIndex = ++vocabularyIndex;
        }

        public String getSampleSentenceLanguage1() {
            return sampleSentenceLanguage1;
        }
        private void setSampleSentenceLanguage1(String sampleSentenceLanguage1) {
            this.sampleSentenceLanguage1 = sampleSentenceLanguage1;
        }

        public String getSampleSentenceLanguage2() {
        return sampleSentenceLanguage2;
    }
        private void setSampleSentenceLanguage2(String sampleSentenceLanguage2) {
            this.sampleSentenceLanguage2 = sampleSentenceLanguage2;
        }

        public String getCategory() {
        return category;
    }
        private void setCategory(String category) {
            this.category = category;
        }

        public String getForeignLanguage() {
        return foreignLanguage;
    }
        private void setForeignLanguage(String foreignLanguage) {
            this.foreignLanguage = foreignLanguage;
        }

        public String getNativeLanguage() {
        return nativeLanguage;
    }
        private void setNativeLanguage(String nativeLanguage) {
            this.nativeLanguage = nativeLanguage;
    }

        public static void createSamples(){
            allVocabularies.add(new Vokabel("das Haus","La maison","Das ist ein sch??nes Haus.","C'est une belle maison.","Franz??sisch","Deutsch","Haus & Garten"));
            allVocabularies.add(new Vokabel("Der Berg","The mountain","Das ist ein sehr hoher Berg","That is a very high mountain.","Englisch","Deutsch","Natur und Landschaft"));
            allVocabularies.add(new Vokabel("Die Gelegenheit","Lejlighed-en","Du hast nun die Gelegenheit etwas zu tun.","Du har nu leijligheden at g??re noget.","D??nisch","Deutsch","Abstrakte Nomen"));
            allVocabularies.add(new Vokabel("wollen","vouloir","Ich will diesen Film sehen.","Je veux voir ce film.","Franz??sisch","Deutsch","Hilfsverben"));
        }

}

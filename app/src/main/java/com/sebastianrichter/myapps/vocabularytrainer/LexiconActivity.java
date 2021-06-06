package com.sebastianrichter.myapps.vocabularytrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class LexiconActivity extends AppCompatActivity {

    private LexiconAdapter lexiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lexicon);

        Button btn_BackToVocabulary = (Button) findViewById(R.id.btn_BackToVocabulary);
        btn_BackToVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityVocabulary();
            }
        });

        ListView lv_Vocabulary = (ListView) findViewById(R.id.lV_Vocabulary);



        lexiAdapter = new LexiconAdapter(this,R.layout.adapter_view_layout, VokabelNeu.allVocabularies);
        lv_Vocabulary.setAdapter(lexiAdapter);
        lexiAdapter.notifyDataSetInvalidated();

        /*
        ListView lv_Vocabulary = (ListView) findViewById(R.id.lV_Vocabulary);
        ArrayAdapter adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vokabel.allVocabularies);
        lv_Vocabulary.setAdapter(adapter3);
*/

    }

    private void openActivityVocabulary(){
        Intent intent = new Intent(this, VocabularySetupActivity.class);
        startActivity(intent);
    }


}

class LexiconAdapter extends ArrayAdapter<VokabelNeu>{
    private Context mContext;
    int mResource;

    public LexiconAdapter(Context context, int resource, ArrayList<VokabelNeu> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String vocabularyNativeLanguage = getItem(position).getVocabularyLanguage1();
        String vocabularyForeignLanguage = getItem(position).getVocabularylanguage2();
        String sampleNative = getItem(position).getSampleSentenceLanguage1();
        String sampleForeign = getItem(position).getSampleSentenceLanguage2();
        String category = getItem(position).getCategory();
        String languageForeign = getItem(position).getForeignLanguage();


        //VokabelNeu vok = new VokabelNeu("","","","","","","");
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tx1 = (TextView) convertView.findViewById(R.id.tV_VocabularyNativeLanguage);
        TextView tx2 = (TextView) convertView.findViewById(R.id.tV_VocabularyForeignLanguage);
        TextView tx3 = (TextView) convertView.findViewById(R.id.tv_SampleNative);
        TextView tx4 = (TextView) convertView.findViewById(R.id.tV_SampleForeign);
        TextView tx5 = (TextView) convertView.findViewById(R.id.tV_Category);
        TextView tx6 = (TextView) convertView.findViewById(R.id.tV_LanguageForeign);
        tx1.setText(vocabularyNativeLanguage);
        tx2.setText(vocabularyForeignLanguage);
        tx3.setText(sampleNative);
        tx4.setText(sampleForeign);
        tx5.setText(category);
        tx6.setText(languageForeign);


        return convertView;

    }
}
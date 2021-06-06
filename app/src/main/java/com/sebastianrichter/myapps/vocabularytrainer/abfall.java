package com.sebastianrichter.myapps.vocabularytrainer;

import android.content.SharedPreferences;

public class abfall {

    /*

    private void readConfig(){
        try {
            JSONObject jsonObject = new JSONObject(readFromJASON());
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            JSONObject userData = jsonArray.getJSONObject(0);
            Config.readConfig(userData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readFromJASON() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("user.json");
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
*/

     /*
    private void loadVocabularies(){
        SharedPreferences vocabulary = getSharedPreferences("vocabularies",0);

        int i = 0;
        boolean t = false;
        while(!t){
            if(!vocabulary.getString(String.valueOf(i),"").equals("")){
                String[] v = vocabulary.getString(String.valueOf(i),"").split(";");
                //System.out.println(v[0]+v[1]+v[2]+v[3]+v[4]+v[5]);
                Vokabel.allVocabularies.add(new Vokabel(v[0],v[1],v[2],v[3],v[4],v[5]));
                i++;
            }else{
                t= true;
            };
        }
    }

    */


/*
    private void writeVocabularies(){
        SharedPreferences mySPR = getSharedPreferences("vocabularies",0);
        SharedPreferences.Editor editor = mySPR.edit();
        for(int i = 0; i < Vokabel.allVocabularies.size(); i++){
            StringBuffer sb = new StringBuffer();
            sb.append(Vokabel.allVocabularies.get(i).getVocabularyLanguage1()).append(';');
            sb.append(Vokabel.allVocabularies.get(i).getVocabularylanguage2()).append(';');
            sb.append(Vokabel.allVocabularies.get(i).getSampleSentenceLanguage1()).append(';');
            sb.append(Vokabel.allVocabularies.get(i).getSampleSentenceLanguage1()).append(';');
            sb.append(Vokabel.allVocabularies.get(i).getChoosenLanguage()).append(';');
            sb.append(Vokabel.allVocabularies.get(i).getCategory()).append(';');
            editor.putString(String.valueOf(Vokabel.vocabularyIndex), sb.toString());
            Vokabel.vocabularyIndex++;
        }
        editor.commit();
    }


    public class VCWriter {

    public static void createSample() {
        for(int i = 0; i< 10; i++)
            VCReader.alleVokabeln.add(new Vokabel("Vokabel 1-" + i, "Vokabel 2-" + i, "Hello, whats up-" + i, "Hallo, was geht ab-"+i, "Deutsch-"+i,"Politik-"+i));

    }

    public static void write() {
        System.out.println("void write ///////////////////////////////");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(VCReader.path));
            for (int i = 0; i < VCReader.alleVokabeln.size(); i++) {
                out.write(VCReader.alleVokabeln.get(i).getVocabularyLanguage1() + ";");
                out.write(VCReader.alleVokabeln.get(i).getVocabularylanguage2() + ";");
                out.write(VCReader.alleVokabeln.get(i).getSampleSentenceLanguage1() + ";");
                out.write(VCReader.alleVokabeln.get(i).getSampleSentenceLanguage2() + ";");
                out.write(VCReader.alleVokabeln.get(i).getChoosenLanguage() + ";");
                out.write(VCReader.alleVokabeln.get(i).getCategory() + ";");
                out.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void writeTest(int i) {
        for(int j = 0; j < VCReader.alleVokabeln.size(); j++ ) {
            System.out.println(VCReader.alleVokabeln.get(i));
        }
    }
}


public class VCReader {

    public static List<Vokabel> alleVokabeln = new LinkedList<Vokabel>();
    public static String path = "." + File.separator + "Beispiel.dat";

    static void read() {
        System.out.println("void read ///////////////////////////////");

        File datei = new File(path);
        BufferedReader in = null;
        if (!datei.exists()) {
            try {
                datei.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            String adresszeile;
            try {
                in = new BufferedReader(new FileReader(datei));
                while ((adresszeile = in.readLine()) != null) {
                    String[] aV = adresszeile.split(";");
                    alleVokabeln.add(new Vokabel(aV[0], aV[1], aV[2], aV[3], aV[4], aV[5]));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


 */
}

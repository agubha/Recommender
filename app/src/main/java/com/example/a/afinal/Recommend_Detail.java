package com.example.a.afinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class Recommend_Detail extends AppCompatActivity {

    EditText book_detail;
    Button find;
    String TextSplit;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_detail);

        book_detail = findViewById(R.id.Book_details);
        find = findViewById(R.id.find_books);
        display = findViewById(R.id.display);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextSplit = book_detail.getText().toString();
                String[] Splitted = TextSplit.split("\\s+");
                int i;
                for (i = 0; i < Splitted.length; i++) {
                    display.setText(Splitted[i]);
                }


                Async_Stemming stem = new Async_Stemming();
                stem.execute(Splitted);


            }
        });

    }


    class Async_Stemming extends AsyncTask<String[], Void, ArrayList> {
        @Override
        protected ArrayList doInBackground(String[]... strings) {
            ArrayList<String> stemmed_list = null;
            for (int i = 0; i < strings.length; i++) {
                String[] word = strings[i];
                PorterStemmer stemmer = new PorterStemmer();
                String stemmed = stemmer.stemWord(word[i]);
                Log.i(stemmed, "Stemmed Word");
                stemmed_list = new ArrayList<>();
                stemmed_list.add(stemmed);


//                LinearLayout linearLayout = new LinearLayout(Recommend_Detail.this);
//                setContentView(linearLayout);
//                linearLayout.setOrientation(LinearLayout.VERTICAL);
//                TextView textView = new TextView(Recommend_Detail.this);
//                textView.setText(stemmed_list.get(i));
//                linearLayout.addView(textView);


            }
            return (stemmed_list);

        }

        @Override
        protected void onPostExecute(ArrayList stemmed_list) {
            ArrayList<String> stemmed_list2 = new ArrayList<>();

            stemmed_list2 = (ArrayList<String>) stemmed_list.clone();
            stemmed_value(stemmed_list2);
        }
    }


    protected String[] stemmed_value(String[] stemmed_value) {

    }
}

package com.example.gerard.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class PoemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_view);

        int poemId = getIntent().getIntExtra("POEM_ID",0);

        PoemViewModel poemViewModel = ViewModelProviders.of(this).get(PoemViewModel.class);

        poemViewModel.getPoem(poemId).observe(this, new Observer<Poem>() {
            @Override
            public void onChanged(@Nullable Poem poem) {
                if(poem != null) {
                    ((TextView) findViewById(R.id.poem_title)).setText(poem.title);
                    ((TextView) findViewById(R.id.poem_author)).setText(poem.author);
                    ((RatingBar) findViewById(R.id.poem_rating)).setRating(poem.rating);
                    ((TextView) findViewById(R.id.poem_content)).setText(poem.content);
                }
            }
        });
    }
}

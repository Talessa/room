package com.example.gerard.room;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class NewPoemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poem);

        final PoemViewModel poemViewModel = ViewModelProviders.of(this).get(PoemViewModel.class);

        findViewById(R.id.addPoem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Poem poem = new Poem();
                poem.author = ((EditText) findViewById(R.id.poem_author)).getText().toString();
                poem.title = ((EditText) findViewById(R.id.poem_title)).getText().toString();
                poem.content = ((EditText) findViewById(R.id.poem_content)).getText().toString();
                //poem.date = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString();
                poem.date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

                poemViewModel.insertPoem(poem);

                finish();
            }
        });
    }
}

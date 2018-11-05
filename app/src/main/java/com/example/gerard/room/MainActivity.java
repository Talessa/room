package com.example.gerard.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewPoemActivity.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.poemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PoemsRecyclerAdapter poemsRecyclerAdapter = new PoemsRecyclerAdapter();
        recyclerView.setAdapter(poemsRecyclerAdapter);

        PoemViewModel poemViewModel = ViewModelProviders.of(this).get(PoemViewModel.class);
        poemViewModel.getAllPoems().observe(this, new Observer<List<Poem>>() {
            @Override
            public void onChanged(@Nullable List<Poem> poems) {
                poemsRecyclerAdapter.setList(poems);
                poemsRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }
}

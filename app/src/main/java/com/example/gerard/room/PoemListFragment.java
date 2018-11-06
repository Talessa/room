package com.example.gerard.room;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class PoemListFragment extends Fragment {

    public PoemListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poem_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.poemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final PoemsRecyclerAdapter poemsRecyclerAdapter = new PoemsRecyclerAdapter();
        recyclerView.setAdapter(poemsRecyclerAdapter);

        PoemViewModel poemViewModel = ViewModelProviders.of(this).get(PoemViewModel.class);
        poemViewModel.getAllPoemsOrderedBy(setOrder()).observe(this, new Observer<List<Poem>>() {
            @Override
            public void onChanged(@Nullable List<Poem> poems) {
                poemsRecyclerAdapter.setList(poems);
                poemsRecyclerAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    abstract String setOrder();
}

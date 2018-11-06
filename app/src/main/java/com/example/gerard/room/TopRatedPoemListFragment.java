package com.example.gerard.room;

public class TopRatedPoemListFragment extends PoemListFragment {
    @Override
    String setOrder() {
        return "rating";
    }
}

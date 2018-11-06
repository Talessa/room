package com.example.gerard.room;

public class NewestPoemListFragment extends PoemListFragment {
    @Override
    String setOrder() {
        return "date";
    }
}

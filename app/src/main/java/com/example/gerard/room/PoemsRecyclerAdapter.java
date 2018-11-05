package com.example.gerard.room;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class PoemsRecyclerAdapter extends RecyclerView.Adapter<PoemsRecyclerAdapter.PoemViewHolder>{

    List<Poem> list;

    @Override
    public PoemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemPoem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poem, parent, false);
        return new PoemViewHolder(itemPoem);
    }

    @Override
    public void onBindViewHolder(PoemViewHolder holder, int position) {
        final Poem poem = list.get(position);

        holder.poemAuthor.setText(poem.author);
        holder.poemTitle.setText(poem.title);
        holder.ratingBar.setRating(poem.rating);
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if(b){
                    poem.rating = v;
                    PoemViewModel poemViewModel = ViewModelProviders.of((AppCompatActivity) ratingBar.getContext()).get(PoemViewModel.class);
                    poemViewModel.setRating(poem);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PoemViewActivity.class);
                intent.putExtra("POEM_ID", poem.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public void setList(List<Poem> poems){
        this.list = poems;
    }

    class PoemViewHolder extends RecyclerView.ViewHolder {
        private TextView poemTitle;
        private TextView poemAuthor;
        private RatingBar ratingBar;


        PoemViewHolder(View itemPoem) {
            super(itemPoem);
            poemTitle = itemPoem.findViewById(R.id.poem_title);
            poemAuthor = itemPoem.findViewById(R.id.poem_author);
            ratingBar = itemPoem.findViewById(R.id.ratingBar);
        }
    }
}

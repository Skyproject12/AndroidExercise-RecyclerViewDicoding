package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewHeroAdapter extends RecyclerView.Adapter<CardViewHeroAdapter.CardViewHolder> {
    private ArrayList<Hero> listHero;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback= onItemClickCallback;
    }

    public CardViewHeroAdapter(ArrayList<Hero> listHero) {
        this.listHero = listHero;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_hero, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        Hero hero= listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.imagePhoto);
        holder.textName.setText(hero.getName());
        holder.textFrom.setText(hero.getFrom());
        holder.buttonFavorit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Favorite " +
                        listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.buttonShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Share " +
                        listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make onclick next send position if click recycler view
                onItemClickCallback.onItemCliked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textFrom;
        ImageView imagePhoto;
        Button buttonFavorit;
        Button buttonShare;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhoto= itemView.findViewById(R.id.image_item_photo);
            textName= itemView.findViewById(R.id.text_item_name);
            textFrom= itemView.findViewById(R.id.text_item_from);
            buttonFavorit= itemView.findViewById(R.id.button_favorit);
            buttonShare= itemView.findViewById(R.id.button_share);
        }
    }
    // make interface
    public interface  OnItemClickCallback{
        void onItemCliked(Hero data);
    }
}

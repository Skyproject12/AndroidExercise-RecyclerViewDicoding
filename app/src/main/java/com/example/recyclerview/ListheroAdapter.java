package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListheroAdapter extends RecyclerView.Adapter<ListheroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;

    public ListheroAdapter(ArrayList<Hero> listHero) {
        this.listHero = listHero;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hero, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Hero hero= listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imagePhoto);
        holder.textName.setText(hero.getName());
        holder.textFrom.setText(hero.getFrom());
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textFrom;
        ImageView imagePhoto;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            textName= itemView.findViewById(R.id.text_item_name);
            textFrom= itemView.findViewById(R.id.text_item_from);
            imagePhoto= itemView.findViewById(R.id.image_item_photo);
        }
    }
}

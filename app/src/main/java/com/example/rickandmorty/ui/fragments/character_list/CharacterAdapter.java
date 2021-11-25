package com.example.rickandmorty.ui.fragments.character_list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.databinding.ItemCharacterBinding;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private List<Character> list = new ArrayList<>();
    private OnItemClickListener listener;

    public void setCharacters(List<Character> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Character getItem(int pos){
        return list.get(pos);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
            });
        }

        public void onBind(Character character) {
            binding.tvItemCharacterName.setText(character.getName());
            Glide.with(binding.getRoot())
                    .load(character.getImage())
                    .centerCrop()
                    .into(binding.ivItemCharacter);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
}

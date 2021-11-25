package com.example.rickandmorty.ui.fragments.character_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.data.remote.RickAndMortyApi;
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class CharacterDetailFragment extends BaseFragment<FragmentCharacterDetailBinding> {

    private CharacterDetailFragmentArgs args;
    @Inject
    RickAndMortyApi api;

    public CharacterDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = CharacterDetailFragmentArgs.fromBundle(getArguments());
        api.getCharacterById(args.getCharacterId()).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.isSuccessful() && response.body() != null){
                    binding.text.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {

            }
        });
    }

    @Override
    protected FragmentCharacterDetailBinding bind() {
        return FragmentCharacterDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupObservers() {

    }
}
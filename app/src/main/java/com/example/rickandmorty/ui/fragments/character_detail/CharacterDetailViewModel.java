package com.example.rickandmorty.ui.fragments.character_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.common.Resource;
import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.data.models.RickAndMortyResponse;
import com.example.rickandmorty.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterDetailViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<RickAndMortyResponse<Character>>> characterLiveData;
    @Inject
    public CharacterDetailViewModel(MainRepository repository){
    this.repository = repository;
    }

    public void fetchCharacters(){
        characterLiveData = repository.getCharacters();
    }

}


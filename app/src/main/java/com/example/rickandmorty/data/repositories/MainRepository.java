package com.example.rickandmorty.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.App;
import com.example.rickandmorty.common.Resource;
import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.data.models.RickAndMortyResponse;
import com.example.rickandmorty.data.remote.RickAndMortyApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private RickAndMortyApi api;

    @Inject
    public MainRepository(RickAndMortyApi  api){
        this.api = api;
    }

    public MutableLiveData<Resource<RickAndMortyResponse<Character>>> getCharacters() {
        MutableLiveData<Resource<RickAndMortyResponse<Character>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getCharacters().enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }
}
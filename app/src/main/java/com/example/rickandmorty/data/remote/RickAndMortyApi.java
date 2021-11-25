package com.example.rickandmorty.data.remote;

import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.data.models.RickAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {

    @GET("character")
    Call<RickAndMortyResponse<Character>> getCharacters();

    @GET("character/{id}")
    Call<Character> getCharacterById(
            @Path("id") int id
    );
}

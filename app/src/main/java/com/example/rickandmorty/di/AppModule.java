package com.example.rickandmorty.di;

import com.example.rickandmorty.data.remote.RetroFitClient;
import com.example.rickandmorty.data.remote.RickAndMortyApi;
import com.example.rickandmorty.data.repositories.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class) //указывает скоп
public abstract class AppModule {

    @Provides
    public static RickAndMortyApi provideApi(){
        return new RetroFitClient().provideApi();
    }

    @Provides
    public static MainRepository provideRepository(RickAndMortyApi api){
        return new MainRepository(api);
    }

}

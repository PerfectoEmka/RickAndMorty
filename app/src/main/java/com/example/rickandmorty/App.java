package com.example.rickandmorty;

import android.app.Application;

import com.example.rickandmorty.data.remote.RetroFitClient;
import com.example.rickandmorty.data.remote.RickAndMortyApi;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }
}

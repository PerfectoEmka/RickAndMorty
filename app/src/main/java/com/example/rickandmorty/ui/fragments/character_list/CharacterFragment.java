package com.example.rickandmorty.ui.fragments.character_list;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.rickandmorty.R;
import com.example.rickandmorty.base.BaseFragment;
import com.example.rickandmorty.common.Resource;
import com.example.rickandmorty.data.models.Character;
import com.example.rickandmorty.data.models.RickAndMortyResponse;
import com.example.rickandmorty.databinding.FragmentCharacterBinding;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.HiltAndroidApp;

@AndroidEntryPoint
public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> implements CharacterAdapter.OnItemClickListener{

    private CharacterViewModel viewModel;
    private CharacterAdapter adapter;

    public CharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CharacterAdapter();
    }

    @Override
    protected FragmentCharacterBinding bind() {
        return FragmentCharacterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        adapter.setListener(this);
        viewModel =  new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        viewModel.fetchCharacters();
        binding.recycler.setAdapter(adapter);
    }

    @Override
    protected void setupObservers() {
        viewModel.characterLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<RickAndMortyResponse<Character>>>() {
            @Override
            public void onChanged(Resource<RickAndMortyResponse<Character>> response) {
                switch (response.status) {
                    case SUCCESS: {
                        adapter.setCharacters(response.data.getResults());
                        binding.progress.setVisibility(View.GONE);
                        break;
                    }
                    case ERROR: {
                        binding.progress.setVisibility(View.GONE);
                        break;
                    }
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(int pos) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate((NavDirections) CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment2(adapter.getItem(pos).getId()));
    }
}
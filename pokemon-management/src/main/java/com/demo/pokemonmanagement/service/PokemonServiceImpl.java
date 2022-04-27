package com.demo.pokemonmanagement.service;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements IPokemonService{

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> findAll(){
        return pokemonRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Pokemon findById(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    @Override
    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon update(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id)
                .map(existingPokemon -> {
                    existingPokemon.setName(pokemon.getName());
                    existingPokemon.setSpecies(pokemon.getSpecies());
                    return pokemonRepository.save(existingPokemon);
                })
                .orElse(null);
    }

    public void delete(Long id){
        pokemonRepository.deleteById(id);
    }
}

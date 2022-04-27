package com.demo.pokemonmanagement.service;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.dto.SearchNameReq;
import com.demo.pokemonmanagement.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    public List<Pokemon> findAll(){
        return pokemonRepository.findAllByOrderByIdAsc();
    }

    public Pokemon findById(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

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

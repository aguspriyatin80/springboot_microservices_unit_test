package com.demo.pokemonmanagement.service;

import com.demo.pokemonmanagement.domain.Pokemon;
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
        return pokemonRepository.findAll();
    }

    public Pokemon findById(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon create(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Pokemon saveOrUpdate(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(Long id, String name, String species) {
        Pokemon pokemonFromDb = pokemonRepository.findById(id).get();
        pokemonFromDb.setName(name);
        pokemonFromDb.setSpecies(species);
        Pokemon updatedPokemon = pokemonRepository.save(pokemonFromDb);
        return updatedPokemon;
    }

    public void delete(Long id){
        pokemonRepository.deleteById(id);
    }
}

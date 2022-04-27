package com.demo.pokemonmanagement.service;

import com.demo.pokemonmanagement.domain.Pokemon;

import java.util.List;

public interface IPokemonService {
    List<Pokemon> findAll();
    Pokemon findById(Long id);
    Pokemon create(Pokemon pokemon);
    Pokemon update(Long id, Pokemon pokemon);
    void delete(Long id);
}

package com.demo.pokemonmanagement.repository;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.dto.PokemonDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
    List<Pokemon> findAllByOrderByIdAsc();
    Optional<Pokemon> findByName(String name);
}

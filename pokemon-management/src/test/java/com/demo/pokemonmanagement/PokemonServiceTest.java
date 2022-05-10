package com.demo.pokemonmanagement;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.repository.PokemonRepository;
import com.demo.pokemonmanagement.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonServiceTest{
    @InjectMocks
    PokemonService pokemonService;

    @Mock
    PokemonRepository pokemonRepository;

    Pokemon pokemon;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pokemon satu");
        pokemon.setSpecies("Species satu");

    }

    @Test
    public void testSaveOrUpdate(){
        pokemon = new Pokemon(1L,"Pokemon new", "Species new");
        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        Pokemon actual = pokemonService.saveOrUpdate(pokemon);
        when(pokemonService.saveOrUpdate(pokemon)).thenReturn(pokemon);

        assertNotNull(actual);

    }

    @Test
    public void testCreate(){
        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pokemon 1");
        pokemon.setSpecies("Species 1");

        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        assertEquals(pokemonService.create(pokemon),pokemon);
    }

    @Test
    public void testUpdate(){
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pokemon 2");
        pokemon.setSpecies("Species name");

        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemon));

        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        pokemonService.update(1L,pokemon);

        assertNotEquals("Pokemon 1",  pokemon.getName());

    }

    @Test
    public void testFindAll() {
        List<Pokemon> datas = Arrays.asList(
                new Pokemon(1L,"Pokemon1", "Spesies"),
                new Pokemon(2L,"Pokemon2", "Spesies"),
                new Pokemon(3L,"Pokemon3", "Spesies")
        );
        pokemonRepository.saveAll(datas);
        when(pokemonRepository.findAll()).thenReturn(datas);
        List<Pokemon> actualList = pokemonService.findAll();
        assertThat(actualList.size(), equalTo(datas.size()));
    }

    @Test
    public void testFindOne() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("Pokemon satu");
        pokemon.setSpecies("Species satu");

        pokemonRepository.save(pokemon);

        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemon));
        Pokemon storedPokemon = pokemonService.findById(1L);

        assertThat(storedPokemon.getId(), equalTo(pokemon.getId()));

    }

    @Test
    public void testDelete() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pokemon deleted");
        pokemon.setSpecies("Species deleted");
        pokemon.setId(10L);

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));

        pokemonService.delete(pokemon.getId());
        verify(pokemonRepository).deleteById(pokemon.getId());
    }

}
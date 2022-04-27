package com.demo.pokemonmanagement;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.dto.PokemonDTO;
import com.demo.pokemonmanagement.repository.PokemonRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PokemonRepositoryTest {
    @Autowired
    PokemonRepository pokemonRepository;

//    @BeforeEach
//    public void setUp(){
//        List<Pokemon> pokemonList = Arrays.asList(
//                new Pokemon(null,"Pokemon 1", "Species 1"),
//                new Pokemon(null,"Pokemon 2", "Species 2")
//        );
//        pokemonRepository.saveAll(pokemonList);
//    }
//    @AfterEach
//    public void tearDown(){
//        pokemonRepository.deleteAll();
//    }
    @Test
    @Order(1)
    public void testCreate(){
        Pokemon p = new Pokemon();
        p.setName("Pokemon 1");
        p.setSpecies("Species 1");
        pokemonRepository.save(p);
        assertNotNull(pokemonRepository.findById(1L).get());
        assertEquals("Pokemon 1", pokemonRepository.findById(1L).get().getName());
    }

    @Test
    @Order(2)
    public void testUpdate () {
        Pokemon p = pokemonRepository.findById(1L).get();
        p.setName("Pokemon 1 updated");
        pokemonRepository.save(p);
        assertNotEquals("Pokemon 1", pokemonRepository.findById(1L).get().getName());
    }

    @Test
    @Order(3)
    public void testReadAll () {
        List list = pokemonRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void testRead () {
        Pokemon pokemon = pokemonRepository.findById(1L).get();
        assertEquals("Pokemon 1 updated", pokemon.getName());
    }

    @Test
    @Order(5)
    public void testDelete () {
        pokemonRepository.deleteById(1L);
        assertThat(pokemonRepository.existsById(1L)).isFalse();
    }
}

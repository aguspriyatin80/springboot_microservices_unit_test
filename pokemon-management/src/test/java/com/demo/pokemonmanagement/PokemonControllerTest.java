package com.demo.pokemonmanagement;

import com.demo.pokemonmanagement.controller.PokemonController;
import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers= PokemonController.class)
public class PokemonControllerTest {
    @MockBean
    PokemonService pokemonService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveOrUpdate() throws Exception{
        Pokemon pokemon = new Pokemon(null, "Pokemon 1", "Pokemon species");
        when(pokemonService.saveOrUpdate(pokemon)).thenReturn(pokemon);
        mockMvc.perform(post("/pokemon")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pokemon)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllPokemon() throws Exception {
        when(pokemonService.findAll()).thenReturn(Arrays.asList(
            new Pokemon(1L,"Pokemon 1", "species"),

            new Pokemon(3L,"Pokemon 3", "species")
        ));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pokemon")
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testFindAllPokemon2() throws Exception {
        when(pokemonService.findAll()).thenReturn(Arrays.asList(
            new Pokemon(1L,"Pokemon 1", "species"),

            new Pokemon(3L,"Pokemon 3", "species")
        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    public void testFindById() throws Exception{
        final Long id = 1L;
        final Pokemon pokemon = new Pokemon(null, "Pokemon 1", "species");
        when(pokemonService.findById(id)).thenReturn(pokemon);

        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/{id}",id)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        final Long id = 1L;
        Mockito.doNothing().when(pokemonService).delete(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/pokemon/{id}",id))
                .andExpect(status().isOk());
    }
}

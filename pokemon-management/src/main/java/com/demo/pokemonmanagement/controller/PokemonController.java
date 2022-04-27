package com.demo.pokemonmanagement.controller;

import com.demo.pokemonmanagement.domain.Pokemon;
import com.demo.pokemonmanagement.dto.SearchNameReq;
import com.demo.pokemonmanagement.service.IPokemonService;
import com.demo.pokemonmanagement.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PokemonController {
    @Autowired
    PokemonService pokemonService;
//    IPokemonService pokemonService;

    @PostMapping("/pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon create(@RequestBody Pokemon pokemon){
        return pokemonService.create(pokemon);
    }

    @GetMapping("/pokemon")
    public List<Pokemon> findAll(){
        return pokemonService.findAll();
    }

    @GetMapping("/pokemon/{id}")
    public Pokemon findById(@PathVariable Long id){
        return pokemonService.findById(id);
    }

    @PutMapping("/pokemon/{id}")
    public Pokemon update(@PathVariable Long id, @RequestBody Pokemon pokemon){
        return pokemonService.update(id,pokemon);
    }

    @DeleteMapping("/pokemon/{id}")
    public void delete(@PathVariable Long id){
        pokemonService.delete(id);
    }


}

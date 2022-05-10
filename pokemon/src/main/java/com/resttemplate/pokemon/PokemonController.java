package com.resttemplate.pokemon;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class PokemonController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/template/pokemon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveOrUpdate(@RequestBody Pokemon pokemon)
    {
        System.out.println("Inside saveOrUpdate method of SpringBootRestTemplateDemo Application");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Pokemon> entity = new HttpEntity<Pokemon>(pokemon, headers);

        ResponseEntity<String> responseEntity =  restTemplate
                .exchange("http://localhost:8080/pokemon", HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/template/pokemon/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePokemon(@PathVariable("id") int id, @RequestBody Pokemon pokemon)
    {
        System.out.println("Inside updatePokemon method of SpringBootRestTemplateDemo Application");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Pokemon> entity = new HttpEntity<Pokemon>(pokemon, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/pokemon/" + id, HttpMethod.PUT,
                entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/template/pokemon", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPokemon()
    {
        System.out.println("Inside getAllPokemon method of SpringBootRestTemplateDemo Application");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://localhost:8080/pokemon", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/template/pokemon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOnePokemon(@PathVariable("id") int id)
    {
        System.out.println("Inside getOnePokemon method of SpringBootRestTemplateDemo Application");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("http://localhost:8080/pokemon/"+id, HttpMethod.GET, entity, String.class);

        return responseEntity.getBody();
    }

    @RequestMapping(value = "/template/pokemon/{id}", method = RequestMethod.DELETE)
    public String deletePokemon(@PathVariable("id") int id)
    {
        System.out.println("Inside deletePokemon method of SpringBootRestTemplateDemo Application");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Pokemon> entity = new HttpEntity<Pokemon>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/pokemon/" + id, HttpMethod.DELETE,
                entity, String.class);
        return responseEntity.getBody();
    }
}

package com.example.aula3.controllers;

import java.util.List;

import com.example.aula3.dto.CategoriaDTO;
import com.example.aula3.entity.Categoria;
import com.example.aula3.repository.CategoriaRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {
    
    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    // SELECIONAR POR ID
    @GetMapping("{id}")
    public Categoria getById(@PathVariable int id){
        return categoriaRepository.findById(id).orElseThrow(() 
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    // SELECIONAR POR QUALQUER CAMPO
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria save(@RequestBody CategoriaDTO categoria) {
        
    }


    //INSERIR
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //DELETAR
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        categoriaRepository.findById(id).map(categoria ->{
            categoriaRepository.delete(categoria);
            return categoria;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
    
    //UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id,
     @RequestBody Categoria categoria){
            categoriaRepository.findById(id).map(categoriaExistente ->{
            categoria.setId(categoriaExistente.getId());
            categoriaRepository.save(categoria);
            return categoriaExistente;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
    
    //SELECIONAR TODOS
    @GetMapping
    public List<Categoria> find(Categoria filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(StringMatcher.CONTAINING);
        return categoriaRepository.findAll();
    }

    @RequestMapping(value = {"/teste/{nome}", "/teste2/{nome}"}, 
    method = RequestMethod.GET, 
    consumes = {"application/json" , "application/xml"}, 
    produces = {"application/json" , "application/xml"})
    public String testeCategoria(@PathVariable("nome") String nomeCategoria){
        return String.format("Categoria %s", nomeCategoria);
    }
}

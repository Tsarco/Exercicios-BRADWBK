package com.example.livro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.livro.model.Livro;
import com.example.livro.repository.LivroRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<Iterable<Livro>> listarLivros() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        livroRepository.save(livro);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }
    
    
    
}

package com.example.livro.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.livro.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {
    
}

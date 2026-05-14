package com.example.livro.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.livro.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
} 

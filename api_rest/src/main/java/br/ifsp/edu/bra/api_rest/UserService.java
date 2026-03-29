package br.ifsp.edu.bra.api_rest;

public interface UserService {

    void add(User novoUser);

    User find(String login);

    boolean remove(String login);
    
} 

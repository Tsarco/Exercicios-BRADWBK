package br.ifsp.edu.bra.api_rest;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service // <- bean 
public class UserServiceImpl implements UserService{
    private ArrayList<User> listaUsuarios = new ArrayList<>();

    @Override
    public void add(User novoUser){
        listaUsuarios.add(novoUser);
    };

    @Override
    public User find(String login){
        for(User user : listaUsuarios) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    };


    @Override
    public boolean remove(String login){
        User usuarioParaRemover = find(login);

        if (usuarioParaRemover != null){
            listaUsuarios.remove(usuarioParaRemover);
            return true;
        }
    
        return false; //se for nulo, n deleta
    };
}
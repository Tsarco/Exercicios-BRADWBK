package br.ifsp.edu.bra.api_rest;

import org.springframework.web.bind.annotation.RequestMapping;
// Import automático: shift + alt + o
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.add(user); //adiciona usuario no serviço q criamos apos criar o user

        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        //user.setLogin("user_recebido"); (comentei pra testar usar login q o usuario por)
        // 201 created
        return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/user/{login}")
    public ResponseEntity<User> findUser(@PathVariable("login") String login) {
        User user = userService.find(login);
        
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("user/{login}")
    public ResponseEntity<String> deleteUser(@PathVariable("login") String login) {

        boolean foiRemovido = userService.remove(login);
        
        if (foiRemovido){
            return ResponseEntity.ok("O usuário foi removido com sucesso.");
        }else{
            return ResponseEntity.ok("Usuario não encontrado");
        }
    }

    
}

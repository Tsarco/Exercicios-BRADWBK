package lista02.exerc02.controller;

import lista02.exerc02.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
    
    // Lista em memória para simular um banco de dados
    private List<Postagem> bancoDeDados = new ArrayList<>();
    private Long proximoId = 1L;

    // 1. GET /postagens
    @GetMapping
    public ResponseEntity<List<Postagem>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(bancoDeDados);
    }

    // 2. POST /postagens 
    @PostMapping
    public ResponseEntity<Postagem> adicionar(@RequestBody Postagem novaPostagem) {
        novaPostagem.setId(proximoId++);
        
        if (novaPostagem.getDataCriacao() == null) {
            novaPostagem.setDataCriacao(LocalDate.now());
        }
        
        bancoDeDados.add(novaPostagem);
        
        //retorna 201 se criado
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPostagem);
    }

    // 3. GET /postagens/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable Long id) {
        Optional<Postagem> postagemEncontrada = bancoDeDados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (postagemEncontrada.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(postagemEncontrada.get());
        } else {
            // Retorna 404 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 4. PUT /postagens/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem postagemAtualizada) {
        for (int i = 0; i < bancoDeDados.size(); i++) {
            Postagem postagemAtual = bancoDeDados.get(i);
            
            if (postagemAtual.getId().equals(id)) {
                // Atualiza os dados
                postagemAtual.setTitulo(postagemAtualizada.getTitulo());
                postagemAtual.setConteudo(postagemAtualizada.getConteudo());
                postagemAtual.setDataCriacao(postagemAtualizada.getDataCriacao());
                
                return ResponseEntity.status(HttpStatus.OK).body(postagemAtual);
            }
        }
        
        // Retorna 404 se n achar
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 5. DELETE /postagens/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
        for (int i = 0; i < bancoDeDados.size(); i++) {
            Postagem postagem = bancoDeDados.get(i);
            
            if (postagem.getId().equals(id)) {
                bancoDeDados.remove(i);
            
                postagem.setTitulo("Postagem removida");
                postagem.setConteudo("Conteúdo removido");
                
                return ResponseEntity.status(HttpStatus.OK).body(postagem);
            }
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

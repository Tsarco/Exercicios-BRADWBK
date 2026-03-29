package lista02.exerc02.model;

import java.time.LocalDate;

public class Postagem {
    
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDate dataCriacao;

    // Construtor vazio exigido pelo Spring/Jackson
    public Postagem() {
    }

    public Postagem(Long id, String titulo, String conteudo, LocalDate dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
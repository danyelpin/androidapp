package com.example.appandroid;

public class Nota {
    public Nota(String conteudo, String titulo) {
        this.conteudo = conteudo;
        this.titulo = titulo;
    }

    public Nota(Integer id, String conteudo, String titulo) {
        this.id = id;
        this.conteudo = conteudo;
        this.titulo = titulo;
    }

    Integer id;
    String titulo;

    String conteudo;

}

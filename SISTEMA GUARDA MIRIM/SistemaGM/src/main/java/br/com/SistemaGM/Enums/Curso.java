package br.com.SistemaGM.Enums;

public enum Curso {
    COMPUTACAO("Ciencia da Computacao"),
    ENGENHARIA("Engenharia Civil");

    private String curso;

    // Construtor
    private Curso(String curso) {
        this.curso = curso;
    }
}

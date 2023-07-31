package br.com.SistemaGM.Enums;

public enum Status {

    BOLSISTA(0),
    VOLUNTARIO(1);

    private int status;

    // Construtor
    private Status(int stts) {
        this.status = stts;
    }
}

package br.com.SistemaGM.Enums;

public enum Tipo {
    MONITOR(0),
    COORDENADOR(1);

    private int tipo;

    private Tipo(int t) {
        this.tipo = t;
    }
}

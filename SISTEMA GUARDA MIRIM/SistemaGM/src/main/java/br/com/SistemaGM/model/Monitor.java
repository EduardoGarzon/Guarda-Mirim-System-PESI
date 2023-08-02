package br.com.SistemaGM.model;

import br.com.SistemaGM.Enums.Curso;
import br.com.SistemaGM.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // Indica classe como eventide ao spring
@DiscriminatorValue("Monitor")
public class Monitor extends Usuario {

    @Column(name = "RegistroAcademico")
    @NotBlank(message = "[AVISO]: O RA nao pode estar vazio!")
    @Size(min = 4, max = 7, message = "[AVISO]: O RA deve conter pelo menos 4 digitos e no maximo 7 digitos!")
    private String RA;
    @Column(name = "Curso")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "[AVISO]: O campo Curso nao pode ser nulo!")
    private Curso curso; // Enum para cursos
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "[AVISO]: O campo Status nao pode ser nulo!")
    private Status status; // Enum para status

    @Column(name = "Monitoramento")
    private String TurmaMonitorada;

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTurmaMonitorada() {
        return TurmaMonitorada;
    }

    public void setTurmaMonitorada(String turmaMonitorada) {
        TurmaMonitorada = turmaMonitorada;
    }
}

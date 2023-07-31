package br.com.SistemaGM.model;

import br.com.SistemaGM.Enums.Tipo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Configuração para SINGLE_TABLE
public abstract class Usuario {

    @Column(name = "NomeUsuario")
    @Size(min = 5, max = 35, message="[AVISO]: O nome deve conter no minimo 5 caracteres e no maximo 35 caracteres!")
    @NotBlank(message = "[AVISO]: O Nome nao pode estar vazio!")
    private String nome;
    @Column(name = "CPF")
    @NotBlank(message = "[AVISO]: O CPF nao pode estar vazio!")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 digitos!")
    private String CPF;
    @Column(name = "Email")
    @NotBlank(message = "[AVISO]: O Email nao pode estar vazio!")
    @Size(max = 40, message = "O email deve ter no maximo 20 caracteres!")
    @Email
    private String email;
    @Column(name = "Contato")
    @NotBlank(message = "[AVISO]: O Contato nao pode estar vazio!")
    @Size(max = 14, message = "O contato deve ter no maximo 14 digitos!")
    private String contato;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
















    @Id                                             // Indica id como identificador da classe
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento +1 para cada id no BD
    private Integer id;

    @Column(name = "Senha")
    private String senha;

    @Column(name = "tipo_usuario") // Adicione esta linha
    @Enumerated(EnumType.STRING)
    private Tipo tipo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

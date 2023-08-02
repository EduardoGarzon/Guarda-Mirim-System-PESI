package br.com.SistemaGM.dao;

import br.com.SistemaGM.model.Coordenador;
import br.com.SistemaGM.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordenadorDao extends JpaRepository<Coordenador, Integer>{
    public List<Coordenador> findByNomeContainingIgnoreCase(String nome);
    public Coordenador findByCPF(String cpf);
}

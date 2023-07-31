package br.com.SistemaGM.dao;

import br.com.SistemaGM.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitorDao extends JpaRepository<Monitor, Integer>{
    public List<Monitor> findByNomeContainingIgnoreCase(String nome);
    public Monitor findByCPF(String cpf);
}

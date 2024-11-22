package com.fiap.sparklight_api.repository;

import com.fiap.sparklight_api.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    Optional<Historico> findByMesAndAno(Integer mes, Integer ano);
    Optional<Historico> findByUsuario_UsuarioId(Long usuarioId);
}


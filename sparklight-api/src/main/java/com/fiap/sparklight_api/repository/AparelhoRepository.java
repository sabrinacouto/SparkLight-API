package com.fiap.sparklight_api.repository;

import com.fiap.sparklight_api.model.Aparelho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
    Page<Aparelho> findAll(Pageable pageable);
    Page<Aparelho> findByUsuario_UsuarioId(Long usuarioId, Pageable pageable);
}

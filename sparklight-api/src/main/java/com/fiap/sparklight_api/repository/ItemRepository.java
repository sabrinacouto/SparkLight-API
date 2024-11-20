package com.fiap.sparklight_api.repository;

import com.fiap.sparklight_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByHistorico_HistoricoId(Long historicoId);
}


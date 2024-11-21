package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.ItemDTO;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Item;
import com.fiap.sparklight_api.repository.AparelhoRepository;
import com.fiap.sparklight_api.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemMapper {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;


    public ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setItemId(item.getItemId());
        dto.setQuantidade(item.getQuantidade());

        Aparelho aparelho = item.getAparelho();
        if (aparelho != null) {

            BigDecimal tarifaKWh = new BigDecimal("0.50");


            BigDecimal consumoMensal = aparelho.calcularConsumoMensal();


            BigDecimal custoMensal = consumoMensal.multiply(tarifaKWh);


            dto.setConsumoMes(consumoMensal.multiply(BigDecimal.valueOf(item.getQuantidade())));
            dto.setCustoMensal(custoMensal.multiply(BigDecimal.valueOf(item.getQuantidade())));
        }


        if (item.getAparelho() != null) {
            dto.setAparelhoId(item.getAparelho().getAparelhoId());
        }
        if (item.getHistorico() != null) {
            dto.setHistoricoId(item.getHistorico().getHistoricoId());
        }

        return dto;
    }


    public Item toEntity(ItemDTO dto, Aparelho aparelho, Historico historico) {
        Item item = new Item();
        item.setItemId(dto.getItemId());
        item.setQuantidade(dto.getQuantidade());

        item.setAparelho(aparelho);
        item.setHistorico(historico);

        return item;
    }
}


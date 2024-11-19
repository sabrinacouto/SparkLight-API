package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.ItemDTO;
import com.fiap.sparklight_api.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setItemId(item.getItemId());
        dto.setQuantidade(item.getQuantidade());
        dto.setConsumomes(item.getConsumomes());
        dto.setCustomensal(item.getCustomensal());
        dto.setAparelhoId(item.getAparelho().getAparelhoId());
        dto.setHistoricoId(item.getHistorico().getHistoricoId());
        return dto;
    }

    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setItemId(dto.getItemId());
        item.setQuantidade(dto.getQuantidade());
        item.setConsumomes(dto.getConsumomes());
        item.setCustomensal(dto.getCustomensal());
        return item;
    }
}


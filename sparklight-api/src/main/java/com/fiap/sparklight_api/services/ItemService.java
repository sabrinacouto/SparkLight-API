package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.ItemDTO;
import com.fiap.sparklight_api.exceptions.ItemNotFoundException;
import com.fiap.sparklight_api.mapper.ItemMapper;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Item;
import com.fiap.sparklight_api.repository.AparelhoRepository;
import com.fiap.sparklight_api.repository.HistoricoRepository;
import com.fiap.sparklight_api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.math.BigDecimal;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ItemMapper itemMapper;


    public Page<ItemDTO> getAllItems(Pageable pageable) {
        Page<Item> itens = itemRepository.findAll(pageable);
        return itens.map(itemMapper::toDTO);
    }


    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return itemMapper.toDTO(item);
    }


    public ItemDTO createItem(@Valid ItemDTO dto) {
        Aparelho aparelho = aparelhoRepository.findById(dto.getAparelhoId())
                .orElseThrow(() -> new ItemNotFoundException("Aparelho não encontrado."));

        Historico historico = historicoRepository.findById(dto.getHistoricoId())
                .orElseThrow(() -> new ItemNotFoundException("Histórico não encontrado."));


        Item item = itemMapper.toEntity(dto, aparelho, historico);


        BigDecimal consumoMensal = aparelho.calcularConsumoMensal().multiply(BigDecimal.valueOf(dto.getQuantidade()));


        BigDecimal valorKWh = aparelho.getPotencia();
        BigDecimal custoMensal = aparelho.calcularCustoMensal(valorKWh)
                .multiply(BigDecimal.valueOf(dto.getQuantidade()));


        item.setConsumoMes(consumoMensal);
        item.setCustoMensal(custoMensal);


        Item savedItem = itemRepository.save(item);

        return itemMapper.toDTO(savedItem);
    }



    public ItemDTO updateItem(Long id, @Valid ItemDTO dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));


        item.setQuantidade(dto.getQuantidade());


        Aparelho aparelho = aparelhoRepository.findById(dto.getAparelhoId())
                .orElseThrow(() -> new ItemNotFoundException("Aparelho não encontrado."));
        item.setAparelho(aparelho);

        Historico historico = historicoRepository.findById(dto.getHistoricoId())
                .orElseThrow(() -> new ItemNotFoundException("Histórico não encontrado."));
        item.setHistorico(historico);


        BigDecimal consumoMensal = aparelho.calcularConsumoMensal().multiply(BigDecimal.valueOf(dto.getQuantidade()));


        BigDecimal valorKWh = aparelho.getPotencia();
        BigDecimal custoMensal = aparelho.calcularCustoMensal(valorKWh)
                .multiply(BigDecimal.valueOf(dto.getQuantidade()));


        item.setConsumoMes(consumoMensal);
        item.setCustoMensal(custoMensal);


        Item updatedItem = itemRepository.save(item);

        return itemMapper.toDTO(updatedItem);
    }
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        itemRepository.delete(item);
    }



}


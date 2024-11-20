package com.fiap.sparklight_api.controller;


import com.fiap.sparklight_api.dto.ItemDTO;
import com.fiap.sparklight_api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping
    public Page<ItemDTO> getAllItems(Pageable pageable) {
        return itemService.getAllItems(pageable);
    }


    @GetMapping("/{id}")
    public EntityModel<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO itemDTO = itemService.getItemById(id);


        EntityModel<ItemDTO> itemModel = EntityModel.of(itemDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).getItemById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).updateItem(id, itemDTO)).withRel("update");


        itemModel.add(selfLink, updateLink);

        return itemModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestBody @Valid ItemDTO dto) {
        return itemService.createItem(dto);
    }

    @PutMapping("/{id}")
    public EntityModel<ItemDTO> updateItem(@PathVariable Long id, @RequestBody @Valid ItemDTO dto) {
        ItemDTO updatedItem = itemService.updateItem(id, dto);

        EntityModel<ItemDTO> itemModel = EntityModel.of(updatedItem);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).getItemById(id)).withSelfRel();
        itemModel.add(selfLink);

        return itemModel;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}


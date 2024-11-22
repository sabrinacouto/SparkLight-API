package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.ItemDTO;
import com.fiap.sparklight_api.services.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO savedItem = itemService.createItem(itemDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getItemById(savedItem.getItemId())).withSelfRel();
        var allItemsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getAllItems(Pageable.unpaged())).withRel("itens");

        savedItem.add(selfLink, allItemsLink);

        return ResponseEntity.created(selfLink.toUri()).body(savedItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO itemDTO = itemService.getItemById(id);

        if (itemDTO == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getItemById(id)).withSelfRel();
        var allItemsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getAllItems(Pageable.unpaged())).withRel("itens");

        itemDTO.add(selfLink, allItemsLink);

        return ResponseEntity.ok(itemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Long id,
                                              @Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO updatedItem = itemService.updateItem(id, itemDTO);

        if (updatedItem == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getItemById(updatedItem.getItemId())).withSelfRel();
        var allItemsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getAllItems(Pageable.unpaged())).withRel("itens");

        updatedItem.add(selfLink, allItemsLink);

        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<EntityModel<Page<ItemDTO>>> getAllItems(Pageable pageable) {
        Page<ItemDTO> itemsDTO = itemService.getAllItems(pageable);

        EntityModel<Page<ItemDTO>> entityModel = EntityModel.of(itemsDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getAllItems(pageable)).withSelfRel();
        entityModel.add(selfLink);

        if (itemsDTO.hasPrevious()) {
            Link previousPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(ItemController.class)
                                    .getAllItems(PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize())))
                    .withRel("previous");
            entityModel.add(previousPageLink);
        }

        if (itemsDTO.hasNext()) {
            Link nextPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(ItemController.class)
                                    .getAllItems(PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize())))
                    .withRel("next");
            entityModel.add(nextPageLink);
        }

        var allItemsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class)
                .getAllItems(Pageable.unpaged())).withRel("itens");
        entityModel.add(allItemsLink);

        return ResponseEntity.ok(entityModel);
    }
}



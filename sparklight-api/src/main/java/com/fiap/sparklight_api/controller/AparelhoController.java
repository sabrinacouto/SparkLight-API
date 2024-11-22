package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.AparelhoDTO;
import com.fiap.sparklight_api.services.AparelhoService;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aparelhos")
@RequiredArgsConstructor
public class AparelhoController {

    private final AparelhoService aparelhoService;

    @PostMapping
    public ResponseEntity<AparelhoDTO> createAparelho(@Valid @RequestBody AparelhoDTO aparelhoDTO) {
        AparelhoDTO savedAparelho = aparelhoService.createAparelho(aparelhoDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAparelhoById(savedAparelho.getAparelhoId())).withSelfRel();
        var allAparelhosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAllAparelhos(Pageable.unpaged())).withRel("aparelhos");

        savedAparelho.add(selfLink, allAparelhosLink);

        return ResponseEntity.created(selfLink.toUri()).body(savedAparelho);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AparelhoDTO> getAparelhoById(@PathVariable Long id) {
        AparelhoDTO aparelhoDTO = aparelhoService.getAparelhoById(id);

        if (aparelhoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAparelhoById(id)).withSelfRel();
        var allAparelhosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAllAparelhos(Pageable.unpaged())).withRel("aparelhos");

        aparelhoDTO.add(selfLink, allAparelhosLink);

        return ResponseEntity.ok(aparelhoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AparelhoDTO> updateAparelho(@PathVariable Long id,
                                                      @Valid @RequestBody AparelhoDTO aparelhoDTO) {
        AparelhoDTO updatedAparelho = aparelhoService.updateAparelho(id, aparelhoDTO);

        if (updatedAparelho == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAparelhoById(updatedAparelho.getAparelhoId())).withSelfRel();
        var allAparelhosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAllAparelhos(Pageable.unpaged())).withRel("aparelhos");

        updatedAparelho.add(selfLink, allAparelhosLink);

        return ResponseEntity.ok(updatedAparelho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAparelho(@PathVariable Long id) {
        aparelhoService.deleteAparelho(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<EntityModel<Page<AparelhoDTO>>> getAllAparelhos(Pageable pageable) {
        Page<AparelhoDTO> aparelhosDTO = aparelhoService.getAllAparelhos(pageable);

        EntityModel<Page<AparelhoDTO>> entityModel = EntityModel.of(aparelhosDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAllAparelhos(pageable)).withSelfRel();
        entityModel.add(selfLink);

        if (aparelhosDTO.hasPrevious()) {
            Link previousPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(AparelhoController.class)
                                    .getAllAparelhos(PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize())))
                    .withRel("previous");
            entityModel.add(previousPageLink);
        }

        if (aparelhosDTO.hasNext()) {
            Link nextPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(AparelhoController.class)
                                    .getAllAparelhos(PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize())))
                    .withRel("next");
            entityModel.add(nextPageLink);
        }

        var allAparelhosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class)
                .getAllAparelhos(Pageable.unpaged())).withRel("aparelhos");
        entityModel.add(allAparelhosLink);

        return ResponseEntity.ok(entityModel);
    }
}

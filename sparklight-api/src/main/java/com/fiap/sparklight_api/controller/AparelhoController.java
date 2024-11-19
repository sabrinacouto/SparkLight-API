package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.AparelhoDTO;
import com.fiap.sparklight_api.services.AparelhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aparelhos")
public class AparelhoController {

    @Autowired
    private AparelhoService aparelhoService;


    @GetMapping
    public Page<AparelhoDTO> getAllAparelhos(Pageable pageable) {
        return aparelhoService.getAllAparelhos(pageable);
    }


    @GetMapping("/{id}")
    public EntityModel<AparelhoDTO> getAparelhoById(@PathVariable Long id) {
        AparelhoDTO aparelhoDTO = aparelhoService.getAparelhoById(id);


        EntityModel<AparelhoDTO> aparelhoModel = EntityModel.of(aparelhoDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class).getAparelhoById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class).updateAparelho(id, aparelhoDTO)).withRel("update");


        aparelhoModel.add(selfLink, updateLink);

        return aparelhoModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AparelhoDTO createAparelho(@RequestBody @Valid AparelhoDTO dto) {
        return aparelhoService.createAparelho(dto);
    }

    @PutMapping("/{id}")
    public EntityModel<AparelhoDTO> updateAparelho(@PathVariable Long id, @RequestBody @Valid AparelhoDTO dto) {
        AparelhoDTO updatedAparelho = aparelhoService.updateAparelho(id, dto);

        // Adicionando links HATEOAS
        EntityModel<AparelhoDTO> aparelhoModel = EntityModel.of(updatedAparelho);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AparelhoController.class).getAparelhoById(id)).withSelfRel();
        aparelhoModel.add(selfLink);

        return aparelhoModel;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAparelho(@PathVariable Long id) {
        aparelhoService.deleteAparelho(id);
    }
}

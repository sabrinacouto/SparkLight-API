package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.services.HistoricoService;
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
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public Page<HistoricoDTO> getAllHistoricos(Pageable pageable) {
        return historicoService.getAllHistoricos(pageable);
    }

    @GetMapping("/{id}")
    public EntityModel<HistoricoDTO> getHistoricoById(@PathVariable Long id) {
        HistoricoDTO historicoDTO = historicoService.getHistoricoById(id);

        EntityModel<HistoricoDTO> historicoModel = EntityModel.of(historicoDTO);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class).getHistoricoById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class).updateHistorico(id, historicoDTO)).withRel("update");

        historicoModel.add(selfLink, updateLink);

        return historicoModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoDTO createHistorico(@RequestBody @Valid HistoricoDTO dto) {
        return historicoService.createHistorico(dto);
    }

    @PutMapping("/{id}")
    public EntityModel<HistoricoDTO> updateHistorico(@PathVariable Long id, @RequestBody @Valid HistoricoDTO dto) {
        HistoricoDTO updatedHistorico = historicoService.updateHistorico(id, dto);

        EntityModel<HistoricoDTO> historicoModel = EntityModel.of(updatedHistorico);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class).getHistoricoById(id)).withSelfRel();
        historicoModel.add(selfLink);

        return historicoModel;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHistorico(@PathVariable Long id) {
        historicoService.deleteHistorico(id);
    }
}


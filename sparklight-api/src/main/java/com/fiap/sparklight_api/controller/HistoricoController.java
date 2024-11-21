package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.services.HistoricoService;
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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<HistoricoDTO> createHistorico(@Valid @RequestBody HistoricoDTO historicoDTO) {
        HistoricoDTO savedHistorico = historicoService.createHistorico(historicoDTO);
        System.out.println("HistoricoDTO retornado: " + savedHistorico);
        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getHistoricoById(savedHistorico.getHistoricoId())).withSelfRel();
        var allHistoricosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getAllHistoricos(Pageable.unpaged())).withRel("historicos");

        savedHistorico.add(selfLink, allHistoricosLink);

        return ResponseEntity.created(selfLink.toUri()).body(savedHistorico);

    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoDTO> getHistoricoById(@PathVariable Long id) {
        HistoricoDTO historicoDTO = historicoService.getHistoricoById(id);

        if (historicoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getHistoricoById(id)).withSelfRel();
        var allHistoricosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getAllHistoricos(Pageable.unpaged())).withRel("historicos");

        historicoDTO.add(selfLink, allHistoricosLink);

        return ResponseEntity.ok(historicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricoDTO> updateHistorico(@PathVariable Long id,
                                                        @Valid @RequestBody HistoricoDTO historicoDTO) {
        HistoricoDTO updatedHistorico = historicoService.updateHistorico(id, historicoDTO);

        if (updatedHistorico == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getHistoricoById(updatedHistorico.getHistoricoId())).withSelfRel();
        var allHistoricosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getAllHistoricos(Pageable.unpaged())).withRel("historicos");

        updatedHistorico.add(selfLink, allHistoricosLink);

        return ResponseEntity.ok(updatedHistorico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorico(@PathVariable Long id) {
        historicoService.deleteHistorico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<EntityModel<Page<HistoricoDTO>>> getAllHistoricos(Pageable pageable) {
        Page<HistoricoDTO> historicosDTO = historicoService.getAllHistoricos(pageable);

        EntityModel<Page<HistoricoDTO>> entityModel = EntityModel.of(historicosDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getAllHistoricos(pageable)).withSelfRel();
        entityModel.add(selfLink);

        if (historicosDTO.hasPrevious()) {
            Link previousPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(HistoricoController.class)
                                    .getAllHistoricos(PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize())))
                    .withRel("previous");
            entityModel.add(previousPageLink);
        }

        if (historicosDTO.hasNext()) {
            Link nextPageLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(HistoricoController.class)
                                    .getAllHistoricos(PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize())))
                    .withRel("next");
            entityModel.add(nextPageLink);
        }

        var allHistoricosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .getAllHistoricos(Pageable.unpaged())).withRel("historicos");
        entityModel.add(allHistoricosLink);

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/{usuarioId}/calculo")
    public ResponseEntity<EntityModel<Map<String, BigDecimal>>> calcularConsumoECusto(
            @PathVariable Long usuarioId, Pageable pageable) {


        HistoricoDTO resultado = historicoService.calcularConsumoECusto(usuarioId, pageable);


        Map<String, BigDecimal> response = new HashMap<>();
        response.put("consumoMes", resultado.getConsumoMes());
        response.put("custoMes", resultado.getCustoMes());


        EntityModel<Map<String, BigDecimal>> historicoModel = EntityModel.of(response);


        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HistoricoController.class)
                .calcularConsumoECusto(usuarioId, pageable)).withSelfRel();
        historicoModel.add(selfLink);

        return ResponseEntity.ok(historicoModel);
    }

}



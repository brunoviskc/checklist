package com.ogambientes.checklist.controller;


import com.ogambientes.checklist.model.ProjetoModel;
import com.ogambientes.checklist.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoModel> criarProjeto(@Valid @RequestBody ProjetoModel projetoModel) {
        ProjetoModel projetoSalvo = projetoService.salvarProjeto(projetoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoModel>> listarTodos(){
        List<ProjetoModel> projetos =  projetoService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoModel> buscarPorId(@PathVariable Long id){
        Optional<ProjetoModel> projeto = projetoService.buscarPorId(id);

        return projeto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id){

        if (projetoService.buscarPorId(id).isPresent()) {
            projetoService.deletarProjeto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

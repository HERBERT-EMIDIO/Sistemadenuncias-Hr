package com.hr.sistemadenuncias.controller;

import com.hr.sistemadenuncias.model.Denuncia;
import com.hr.sistemadenuncias.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; // Adicione esta linha

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
// Endpoint para atualizar uma denúncia por ID
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping
    public ResponseEntity<Denuncia> criarDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia novaDenuncia = denunciaService.salvarDenuncia(denuncia);
        return new ResponseEntity<>(novaDenuncia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Denuncia>> listarDenuncias() {
        List<Denuncia> denuncias = denunciaService.findAll();
        return ResponseEntity.ok(denuncias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarDenunciaPorId(@PathVariable Long id) {
        Optional<Denuncia> denuncia = denunciaService.findById(id);

        if (denuncia.isPresent()) {
            return ResponseEntity.ok(denuncia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //... (Outros métodos GET e POST)

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia denunciaAtualizada) {
        Optional<Denuncia> denuncia = denunciaService.updateDenuncia(id, denunciaAtualizada);

        if (denuncia.isPresent()) {
            return ResponseEntity.ok(denuncia.get());
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    //... (Outros métodos GET, POST e PUT) obs; Ctrl + Alt + L

    // Endpoint para deletar uma denúncia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDenuncia(@PathVariable Long id) {
        boolean isDeleted = denunciaService.deleteDenuncia(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
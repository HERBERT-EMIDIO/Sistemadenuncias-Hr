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

}
package com.hr.sistemadenuncias.service;

import com.hr.sistemadenuncias.model.Denuncia;
import com.hr.sistemadenuncias.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    @Autowired
    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public Denuncia salvarDenuncia(Denuncia denuncia) {
        // Lógica de negócio pode ser adicionada aqui, como validações ou regras
        // Por exemplo, definir a data de criação
        denuncia.setDataHoraCriacao(LocalDateTime.now());

        // Se a denúncia for anônima, garantimos que os campos de doador fiquem nulos
        if (denuncia.isAnonima()) {
            denuncia.setNomeDoador(null);
            denuncia.setContatoDoador(null);
        }

        // Chamamos o método save do repositório para persistir a denúncia
        return denunciaRepository.save(denuncia);
    }

    // Adicione este método para buscar todas as denúncias
    public List<Denuncia> findAll() {
        return denunciaRepository.findAll();
    }
}
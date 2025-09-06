package com.hr.sistemadenuncias.service;

import com.hr.sistemadenuncias.model.Denuncia;
import com.hr.sistemadenuncias.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    @Autowired
    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public Denuncia salvarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> findAll() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> findById(Long id) {
        return denunciaRepository.findById(id);
    }

    public Optional<Denuncia> updateDenuncia(Long id, Denuncia denunciaAtualizada) {
        if (denunciaRepository.existsById(id)) {
            denunciaAtualizada.setId(id);
            return Optional.of(denunciaRepository.save(denunciaAtualizada));
        }
        return Optional.empty();
    }

    public boolean deleteDenuncia(Long id) {
        if (denunciaRepository.existsById(id)) {
            denunciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
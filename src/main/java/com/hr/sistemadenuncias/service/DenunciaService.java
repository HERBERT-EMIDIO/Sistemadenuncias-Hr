package com.hr.sistemadenuncias.service;

import com.hr.sistemadenuncias.model.Denuncia;
import com.hr.sistemadenuncias.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
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
        denuncia.setDataHoraCriacao(LocalDateTime.now());

        if (denuncia.isAnonima()) {
            denuncia.setNomeDoador(null);
            denuncia.setContatoDoador(null);
        }

        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> findAll() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> findById(Long id) {
        return denunciaRepository.findById(id);
    }

    // Método para atualizar uma denúncia por ID
    public Optional<Denuncia> updateDenuncia(Long id, Denuncia denunciaAtualizada) {
        Optional<Denuncia> denunciaExistente = denunciaRepository.findById(id);

        if (denunciaExistente.isPresent()) {
            Denuncia denuncia = denunciaExistente.get();
            denuncia.setTitulo(denunciaAtualizada.getTitulo());
            denuncia.setDescricao(denunciaAtualizada.getDescricao());
            denuncia.setAnonima(denunciaAtualizada.isAnonima());
            denuncia.setNomeDoador(denunciaAtualizada.getNomeDoador());
            denuncia.setContatoDoador(denunciaAtualizada.getContatoDoador());
            denuncia.setTipo(denunciaAtualizada.getTipo());
            denuncia.setClassificacao(denunciaAtualizada.getClassificacao());

            return Optional.of(denunciaRepository.save(denuncia));
        }
        return Optional.empty();
    }

    // Método para deletar uma denúncia por ID
    public boolean deleteDenuncia(Long id) {
        if (denunciaRepository.existsById(id)) {
            denunciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
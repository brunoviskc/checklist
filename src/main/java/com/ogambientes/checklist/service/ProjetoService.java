package com.ogambientes.checklist.service;

import com.ogambientes.checklist.model.AmbienteModel;
import com.ogambientes.checklist.model.ProjetoModel;
import com.ogambientes.checklist.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Transactional
    public ProjetoModel salvarProjeto(ProjetoModel projeto){

        if (projeto.getDataCriacao() == null){
            projeto.setDataCriacao(LocalDateTime.now());
        }

        if (projeto.getAmbientes() != null && !projeto.getAmbientes().isEmpty()){
            for (AmbienteModel ambiente : projeto.getAmbientes()){
                ambiente.setProjeto(projeto);
            }
        }

        return projetoRepository.save(projeto);
    }

    @Transactional
    public ProjetoModel atualizarProjeto(Long id, ProjetoModel projetoAtualizado) {
        ProjetoModel projetoExistente = projetoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        projetoExistente.setNomeCliente(projetoAtualizado.getNomeCliente());
        projetoExistente.setNomeVendedor(projetoAtualizado.getNomeVendedor());
        projetoExistente.setNomeArquiteto(projetoAtualizado.getNomeArquiteto());

        if (projetoAtualizado.getDataCriacao() != null) {
            projetoExistente.setDataCriacao(projetoAtualizado.getDataCriacao());
        }

        projetoExistente.getAmbientes().clear();

        if (projetoAtualizado.getAmbientes() != null && !projetoAtualizado.getAmbientes().isEmpty()) {
            for (AmbienteModel ambiente : projetoAtualizado.getAmbientes()) {
                ambiente.setProjeto(projetoExistente);
                projetoExistente.getAmbientes().add(ambiente);
            }
        }

        return projetoRepository.save(projetoExistente);
    }

    public List<ProjetoModel> listarTodos(){
        return projetoRepository.findAll();
    }

    public Optional<ProjetoModel> buscarPorId(Long id){
        return projetoRepository.findById(id);

    }

    @Transactional
    public void deletarProjeto(Long id){
        projetoRepository.deleteById(id);
    }
}

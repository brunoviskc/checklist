package com.ogambientes.checklist.repository;

import com.ogambientes.checklist.model.AmbienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbienteRepository extends JpaRepository<AmbienteModel, Long> {

    List<AmbienteModel> findByProjetoId(Long projetoId);
}

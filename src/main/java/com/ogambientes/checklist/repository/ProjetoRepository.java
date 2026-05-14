package com.ogambientes.checklist.repository;

import com.ogambientes.checklist.model.ProjetoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjetoRepository extends JpaRepository<ProjetoModel, Long> {

}

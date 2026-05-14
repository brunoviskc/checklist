package com.ogambientes.checklist.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_projetos")
public class ProjetoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "O nome é obrigatório!")
    @Column(nullable = false, length = 150)
    private String nomeCliente;

    @Column(length = 100)
    private String nomeVendedor;

    @Column(length = 100)
    private String nomeArquiteto;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AmbienteModel> ambientes = new ArrayList<>();
}

package com.ogambientes.checklist.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ogambientes.checklist.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_ambientes")
public class AmbienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Nome ambiente obrigatório. ex: Superiores, Inferiores , Cristaleira")
    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    @JsonBackReference
    private ProjetoModel projeto;

    @NotNull (message = "Você deve selecionar o tipo de ambiente.")
    @Enumerated(EnumType.STRING)
    private TipoAmbiente tipoAmbiente;

    @NotNull (message = "Você deve selecionar o acabamento interno da caixaria.")
    @Enumerated(EnumType.STRING)
    private AcabamentoInterno acabamentoInterno;

    @NotNull (message = "Você deve selecionar o acabamento externo.")
    @Enumerated(EnumType.STRING)
    private AcabamentoExterno acabamentoExterno;

    @Enumerated(EnumType.STRING)
    private AcabamentoPerfil acabamentoPerfil;

    @Enumerated(EnumType.STRING)
    private AcabamentoTelinha acabamentoTelinha;

    @Enumerated(EnumType.STRING)
    private TipoPorta tipoPorta;

    @Enumerated(EnumType.STRING)
    private TipoPortaPassagem tipoPortaPassagem;

    @Enumerated(EnumType.STRING)
    private TipoPuxador tipoPuxador;

    @Enumerated(EnumType.STRING)
    private TipoVidro tipoVidro;

    @Enumerated(EnumType.STRING)
    private TipoCorredica tipoCorredica;

    @Enumerated(EnumType.STRING)
    private TipoDobradica tipoDobradica;

    @Enumerated(EnumType.STRING)
    private TipoAventos tipoAventos;

    @Enumerated(EnumType.STRING)
    private TipoAcessorio tipoAcessorio;

    @Enumerated(EnumType.STRING)
    private TipoCabideiro tipoCabideiro;

    @Enumerated(EnumType.STRING)
    private TipoLed tipoLed;

    @Enumerated(EnumType.STRING)
    private TipoPainel tipoPainel;

    @Enumerated(EnumType.STRING)
    private TipoRodape tipoRodape;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

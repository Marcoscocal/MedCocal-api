package br.com.medcocal.medcocal_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servicos")
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nomeServico;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer duracaoMinutos;
}
package br.com.medcocal.medcocal_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Medico extends Usuario {

    @Column(nullable = false, unique = true, length = 20)
    private String crm;

    @Column(nullable = false, length = 100)
    private String especialidade;
}
package br.com.medcocal.medcocal_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Paciente extends Usuario {

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;
}
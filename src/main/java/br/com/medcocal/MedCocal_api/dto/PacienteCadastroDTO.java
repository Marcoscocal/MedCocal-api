package br.com.medcocal.medcocal_api.dto;

import java.time.LocalDate;

public record PacienteCadastroDTO(
    String nome,
    String email,
    String senha,
    String telefone,
    String cpf,
    LocalDate dataNascimento
) {}
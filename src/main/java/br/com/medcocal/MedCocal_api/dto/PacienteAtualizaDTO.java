package br.com.medcocal.medcocal_api.dto;

public record PacienteAtualizaDTO(
    String nome,
    String cpf,
    String telefone
) {}
package br.com.medcocal.medcocal_api.dto;

import br.com.medcocal.medcocal_api.model.Paciente;
import java.time.LocalDate;

public record PacienteRespostaDTO(
    Long id,
    String nome,
    String email,
    String telefone,
    String cpf,
    LocalDate dataNascimento
) {
    public PacienteRespostaDTO(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getCpf(),
            paciente.getDataNascimento()
        );
    }
}
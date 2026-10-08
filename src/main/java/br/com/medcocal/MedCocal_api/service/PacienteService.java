package br.com.medcocal.medcocal_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.medcocal.medcocal_api.dto.PacienteAtualizaDTO;
import br.com.medcocal.medcocal_api.dto.PacienteCadastroDTO;
import br.com.medcocal.medcocal_api.dto.PacienteRespostaDTO;
import br.com.medcocal.medcocal_api.exception.ResourceNotFoundException;
import br.com.medcocal.medcocal_api.model.Paciente;
import br.com.medcocal.medcocal_api.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public PacienteRespostaDTO cadastrar(PacienteCadastroDTO dto) {
        if (pacienteRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }

        if (pacienteRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema.");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setEmail(dto.email());
        paciente.setCpf(dto.cpf());
        paciente.setTelefone(dto.telefone());
        paciente.setSenha(dto.senha());
        paciente.setDataNascimento(dto.dataNascimento());

        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return new PacienteRespostaDTO(pacienteSalvo);
    }
    
    @Transactional
    public PacienteRespostaDTO atualizar(Long id, PacienteAtualizaDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));

        if (pacienteRepository.existsByCpfAndIdNot(dto.cpf(), id)) {
            throw new IllegalArgumentException("O CPF informado já está em uso por outro paciente.");
        }

        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setTelefone(dto.telefone());

        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return new PacienteRespostaDTO(pacienteAtualizado);
    }
    
    @Transactional
    public void remover(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com o ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PacienteRespostaDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteRespostaDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PacienteRespostaDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com o ID: " + id));
        return new PacienteRespostaDTO(paciente);
    }
}
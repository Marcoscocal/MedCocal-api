package br.com.medcocal.medcocal_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.medcocal.medcocal_api.dto.PacienteAtualizaDTO;
import br.com.medcocal.medcocal_api.dto.PacienteCadastroDTO;
import br.com.medcocal.medcocal_api.dto.PacienteRespostaDTO;
import br.com.medcocal.medcocal_api.service.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteRespostaDTO> cadastrar(@Valid @RequestBody PacienteCadastroDTO dto) {
        PacienteRespostaDTO novoPaciente = pacienteService.cadastrar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPaciente.id())
                .toUri();

        return ResponseEntity.created(uri).body(novoPaciente);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PacienteRespostaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteAtualizaDTO dto) {
        
        PacienteRespostaDTO pacienteAtualizado = pacienteService.atualizar(id, dto);
        return ResponseEntity.ok(pacienteAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pacienteService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PacienteRespostaDTO>> listarTodos() {
        List<PacienteRespostaDTO> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteRespostaDTO> buscarPorId(@PathVariable Long id) {
        PacienteRespostaDTO paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }
}
package br.com.medcocal.medcocal_api.repository;

import br.com.medcocal.medcocal_api.model.Agendamento;
import br.com.medcocal.medcocal_api.model.Medico;
import br.com.medcocal.medcocal_api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByMedico(Medico medico);

    List<Agendamento> findByPaciente(Paciente paciente);

    List<Agendamento> findByDataHora(LocalDateTime dataHora);
}
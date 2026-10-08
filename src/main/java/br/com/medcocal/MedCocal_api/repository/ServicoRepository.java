package br.com.medcocal.medcocal_api.repository;

import br.com.medcocal.medcocal_api.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    boolean existsByNomeServico(String nomeServico);

    Optional<Servico> findByNomeServico(String nomeServico);
}
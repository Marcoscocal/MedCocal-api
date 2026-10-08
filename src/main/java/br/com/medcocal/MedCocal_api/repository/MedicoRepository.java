package br.com.medcocal.medcocal_api.repository;

import br.com.medcocal.medcocal_api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    boolean existsByCrm(String crm);

    boolean existsByEmail(String email);

    Optional<Medico> findByEmail(String email);
}
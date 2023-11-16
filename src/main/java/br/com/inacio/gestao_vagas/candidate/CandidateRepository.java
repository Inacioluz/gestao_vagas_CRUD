package br.com.inacio.gestao_vagas.candidate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends  JpaRepository<CandidateEntity, UUID>{


}

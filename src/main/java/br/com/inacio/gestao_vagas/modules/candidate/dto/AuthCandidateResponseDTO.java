package br.com.inacio.gestao_vagas.modules.candidate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthCandidateResponseDTO {
    
    private String access_token;
    
}

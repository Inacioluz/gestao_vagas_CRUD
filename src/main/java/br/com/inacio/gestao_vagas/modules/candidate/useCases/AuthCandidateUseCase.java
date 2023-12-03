package br.com.inacio.gestao_vagas.modules.candidate.useCases;

import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.inacio.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.inacio.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.inacio.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import ch.qos.logback.core.util.Duration;

@Service
public class AuthCandidateUseCase {

    
  @Value("${security.token.secret.candidate}")
  private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Object execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {

        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password not found");
        });

        var passwordMatches = this.passwordEncoder
        .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
        .withIssuer("javagas")
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("candidate"))
        .withExpiresAt(Instant.now().plus((TemporalAmount) Duration.buildByMinutes(10)))
        .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .access_token(token)
        .build();

        return authCandidateResponse;

    }
}

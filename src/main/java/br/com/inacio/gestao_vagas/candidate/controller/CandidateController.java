package br.com.inacio.gestao_vagas.candidate.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidateEntity) {
        System.out.println("Candidato");
        System.out.println(candidateEntity.getEmail());
        System.out.println(candidateEntity.getUsername());
        System.out.println(candidateEntity.getPassword());
        System.out.println(candidateEntity.getDescription());
           
    }
}

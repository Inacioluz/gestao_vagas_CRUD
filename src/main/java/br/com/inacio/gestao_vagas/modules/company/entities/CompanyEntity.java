package br.com.inacio.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity(name = "company")
@Data
public class CompanyEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    private String username;
    
    @Email(message = "O campo (email) precisa conter um email válido")
    private String email;
    
    @Length(min = 10, max = 100, message = "A senha deve ter entre 10 e 100 cracteres")
    private String password;

    private String website;
    private String nome;
    private String decription;

    private LocalDateTime createdAt;
}

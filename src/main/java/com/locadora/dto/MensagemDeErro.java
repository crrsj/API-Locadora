package com.locadora.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemDeErro {
 private HttpStatus status;
 private String mensagem;
}

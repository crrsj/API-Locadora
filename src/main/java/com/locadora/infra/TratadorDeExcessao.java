package com.locadora.infra;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.locadora.dto.MensagemDeErro;



@ControllerAdvice
public class TratadorDeExcessao {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<MensagemDeErro>tratarErroDeVeiculoLocado(){
		var erro = new MensagemDeErro(HttpStatus.BAD_REQUEST,"Veículo locado,se estiver disponível,favor cancelar a locação e cadastrar novamente");
	    return ResponseEntity.badRequest().body(erro);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>erroAoBuscarPorId(){
		var erro = new MensagemDeErro(HttpStatus.BAD_REQUEST,"Veículo não encontrado !");
	    return ResponseEntity.badRequest().body(erro);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>tratador400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(TratandoErros::new).toList());		
	}
	public record TratandoErros(String campo,String mensagem) {
		public TratandoErros(FieldError erro) {
			this(erro.getField(),erro.getDefaultMessage());
		}

	}
}

package com.locadora.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.locadora.dto.ClienteDto;
import com.locadora.service.LocadoraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("locadora")
@RequiredArgsConstructor
public class LocadoraController {

	private final LocadoraService service;
	
	@PostMapping
	@Operation(summary = "Rota responsável por efetivar uma locação de veículo")
	 @ApiResponse(responseCode = "201",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	  })      
	public ResponseEntity<ClienteDto>criarCadastro(@RequestBody @Valid ClienteDto cliente){
		var criarCadastro = service.criar(cliente);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
		.buildAndExpand(criarCadastro.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(criarCadastro));
		
	}
	
	@GetMapping
	@Operation(summary = "Rota responsável pela busca de todos as locações")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })         
	public ResponseEntity<List<ClienteDto>>listarTodos(){
		var listar = service.buscarTodos().stream().map(ClienteDto::new).toList();
		return ResponseEntity.ok(listar);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Rota responsável pela busca de uma locação pelo id")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })         
	public ResponseEntity<ClienteDto>buscarId(@PathVariable Long id){
		var busca = service.buscarPorId(id);
		return ResponseEntity.ok().body(new ClienteDto(busca));
	}
	
	@PutMapping
	@Operation(summary = "Rota responsável pela atualização de uma locação")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })         
	public ResponseEntity<ClienteDto>atualizar(@RequestBody @Valid ClienteDto atualizar){
		var atualize = service.atualizar(atualizar);
		return ResponseEntity.ok().body(new ClienteDto(atualize));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Rota responsável por deletar uma locação pelo id")
	 @ApiResponse(responseCode = "204",description = " sem conteúdo",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	  })     
	public ResponseEntity<Void>delete(@PathVariable Long id){
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("buscaPlaca")
	@Operation(summary = "Rota responsável por buscar uma locação pela placa")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })         
	public ResponseEntity<ClienteDto>buscarPorPlaca(@RequestParam String placa){
		var buscaPlaca = service.buscarPorPlaca(placa);
		return ResponseEntity.ok().body(new ClienteDto(buscaPlaca));
	}
}

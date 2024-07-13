package com.locadora.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.locadora.dto.ClienteDto;
import com.locadora.enums.Status;
import com.locadora.model.Cliente;
import com.locadora.repository.LocadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocadoraService {

	private final LocadoraRepository repository;
	
	public Cliente criar(ClienteDto cliente)   {
		var locar = new Cliente(cliente);	
		locar.setTotal(locar.getDias() * locar.getDiaria());
		locar.setStatus(Status.LOCADO);
		return repository.save(locar);
	}
	
	public List<Cliente>buscarTodos(){			  
		return repository.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente>buscar = repository.findById(id);
		return buscar.get();
	}
	
	public Cliente atualizarPorId(ClienteDto atualizar,Long id) {
		var atualizando = new Cliente(atualizar);
		buscarPorId(id);
		atualizando.setId(id);		
		return repository.save(atualizando);
		
	}
	
	public void excluir(Long id) {
		buscarPorId(id);
		repository.findById(id);
		
	}
	
	public Cliente buscarPorPlaca(String placa) {
		return repository.findByPlaca(placa.trim().toUpperCase());
	}
}

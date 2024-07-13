package com.locadora.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import com.locadora.dto.ClienteDto;
import com.locadora.enums.Marca;
import com.locadora.enums.Modelo;
import com.locadora.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private Marca marca;
	private Modelo modelo;	
	@Column(name = "placa",nullable = false,unique = true)
	private String placa;
	private String dataRetirada= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private String dataEntrega = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private int dias;
	private double diaria;
	private double total;
	private Status status;

	public Cliente(ClienteDto cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.marca = cliente.getMarca();
		this.modelo = cliente.getModelo();
		this.dataRetirada = cliente.getDataRetirada();
		this.dataEntrega = cliente.getDataEntrega();
		this.dias = cliente.getDias();
		this.diaria = cliente.getDiaria();
		this.status = cliente.getStatus();
		this.placa = cliente.getPlaca();
		this.total = cliente.getTotal();
	}

	
}

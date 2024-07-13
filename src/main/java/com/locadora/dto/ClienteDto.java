package com.locadora.dto;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



import com.locadora.enums.Marca;
import com.locadora.enums.Modelo;
import com.locadora.enums.Status;
import com.locadora.model.Cliente;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDto {

	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Marca marca;
	@Enumerated(EnumType.STRING)
	private Modelo modelo;	
	@Enumerated(EnumType.STRING)
	private String placa;
	private String dataRetirada= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private String dataEntrega = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private int dias;
	private double diaria;
	private double total;
	@Enumerated(EnumType.STRING)
	private Status status;

	public ClienteDto(Cliente criarCadastro) {
		this.id = criarCadastro.getId();
		this.nome = criarCadastro.getNome();
		this.cpf = criarCadastro.getCpf();
		this.telefone = criarCadastro.getTelefone();
		this.marca = criarCadastro.getMarca();
		this.modelo = criarCadastro.getModelo();
		this.placa = criarCadastro.getPlaca();
		this.dataRetirada = criarCadastro.getDataRetirada();
		this.dataEntrega = criarCadastro.getDataEntrega();
		this.dias = criarCadastro.getDias();
		this.diaria = criarCadastro.getDiaria();
		this.total = criarCadastro.getTotal();
		this.status = criarCadastro.getStatus(); 
	}
}

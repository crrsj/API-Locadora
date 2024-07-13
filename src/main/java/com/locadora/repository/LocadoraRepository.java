package com.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locadora.model.Cliente;

public interface LocadoraRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "select c from Cliente c where upper(trim(c.placa)) like %?1% ") 
	Cliente findByPlaca(String placa);

}

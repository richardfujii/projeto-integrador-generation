package com.educati.EducaTI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public ResponseEntity<?> cadastroUsuario(Usuario novoUsuario) {
	
		for (int i = 0; i  < novoUsuario.getCPF().length(); i++) {
			char titulo = novoUsuario.getCPF().charAt(i);
			if ( Character.isLetter(titulo)) {
				
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas números são válidos");
						
			}
		}
		
		if (usuarioRepository.findAllByNomeContainingIgnoreCase(novoUsuario.getNome()).isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(novoUsuario));
			
		}
		
		else {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário já existente");
		}
		
		
	}
	
	
			
	
	
	
	
	
	

}
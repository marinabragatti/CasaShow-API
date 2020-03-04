package com.gft.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.model.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<DetalhesErro> handleNoSuchElementException(NoSuchElementException e, HttpServletRequest request){
	
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Item não encontrado.");
		erro.setMensagemDesenvolvedor("http://erros.casashow.com/404");
		erro.setTimestamp(new SimpleDateFormat( "dd/MM/yyyy HH:mm" ).format(new Date(System.currentTimeMillis())));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
	
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.casashow.com/400");
		erro.setTimestamp(new SimpleDateFormat( "dd/MM/yyyy HH:mm" ).format(new Date(System.currentTimeMillis())));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<DetalhesErro> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
	
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Caracter Inválido.");
		erro.setMensagemDesenvolvedor("http://erros.casashow.com/400");
		erro.setTimestamp(new SimpleDateFormat( "dd/MM/yyyy HH:mm" ).format(new Date(System.currentTimeMillis())));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<DetalhesErro> handleEmptyResultDataAccessException(EmptyResultDataAccessException e, HttpServletRequest request){
	
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Item inexistente.");
		erro.setMensagemDesenvolvedor("http://erros.casashow.com/404");
		erro.setTimestamp(new SimpleDateFormat( "dd/MM/yyyy HH:mm" ).format(new Date(System.currentTimeMillis())));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}

package com.gft.api;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.model.Evento;
import com.gft.service.EventosService;

@RestController
@RequestMapping("/api/eventos")
public class EventoResource {

	@Autowired
	private EventosService eventosService;
	
	@GetMapping
	public ResponseEntity<List<Evento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Evento evento){
		evento = eventosService.salvar(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(evento.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable ("codigo") Long codigo) throws Exception{
		Evento evento = eventosService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(evento);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@RequestBody Evento evento, @PathVariable ("codigo") Long codigo) throws Exception{
		evento.setCodigo(codigo);
		eventosService.atualizar(evento);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws Exception{
		eventosService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
}

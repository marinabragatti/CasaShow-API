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

import com.gft.model.Casa;
import com.gft.service.CasasService;

@RestController
@RequestMapping("/api/casas")
public class CasaResource {

	@Autowired
	private CasasService casasService;
	
	@GetMapping
	public ResponseEntity<List<Casa>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Casa casa){
		casa = casasService.salvar(casa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(casa.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable ("codigo") Long codigo) throws Exception{
		Casa casa = casasService.buscar(codigo);
		CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(casa);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@RequestBody Casa casa, @PathVariable ("codigo") Long codigo) throws Exception{
		casa.setCodigo(codigo);
		casasService.atualizar(casa);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable ("codigo") Long codigo) throws Exception{
		casasService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/asc")
	public ResponseEntity<List<Casa>> listarCrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarCrescente());
	}
	
	@GetMapping("/desc")
	public ResponseEntity<List<Casa>> listarDecrescente(){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarDecrescente());
	}
	
	@GetMapping("/nome/{nomeCasa}")
	public ResponseEntity<Casa> pesquisa(@PathVariable ("nomeCasa") String nomeCasa){
		return ResponseEntity.status(HttpStatus.OK).body(casasService.pesquisar(nomeCasa));
	}
}

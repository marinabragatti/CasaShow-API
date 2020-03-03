package com.gft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.model.Evento;
import com.gft.repository.EventosInter;

@Service
public class EventosService {

	@Autowired
	private EventosInter eventosInter;
	
	public List<Evento> listar(){
		return eventosInter.findAll();
	}
	
	public Evento buscar(Long id) throws Exception {
		Evento evento = eventosInter.findById(id).get();
		if(evento == null) {
			throw new Exception("Evento não encontrado.");
		}
		return evento;
	}
	
	public Evento salvar(Evento evento) {
		evento.setCodigo(null);
		return eventosInter.save(evento);
	}
	
	public void deletar(Long codigo) throws Exception {
		try {
			eventosInter.deleteById(codigo);
		} catch (EmptyResultDataAccessException e){
			throw new Exception("Evento não encontrado.");
		}
	}
	
	public void atualizar(Evento evento) throws Exception {
		buscar(evento.getCodigo());
		eventosInter.save(evento);
	}
}

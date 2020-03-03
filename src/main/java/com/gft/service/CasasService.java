package com.gft.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.model.Casa;
import com.gft.repository.CasasShowsInter;

@Service
public class CasasService {

	private CasasShowsInter casaShowInter;
	
	public List<Casa> listar(){
		return casaShowInter.findAll();
	}
	
	public Casa salvar(Casa casa) {
		casa.setCodigo(null);
		return casaShowInter.save(casa);
	}
	
	public Casa buscar(Long codigo) throws Exception {
		Casa casa = casaShowInter.findById(codigo).get();
		if(casa == null) {
			throw new Exception("Casa não encontrado.");
		}
		return casa;
	}
	
	public void atualizar(Casa casa) throws Exception {
		buscar(casa.getCodigo());
		casaShowInter.save(casa);
	}
	
	public void deletar(Long codigo) throws Exception {
		try {
			casaShowInter.deleteById(codigo);
		} catch (EmptyResultDataAccessException e){
			throw new Exception("Casa não encontrada.");
		}
	}

//	public List<Casa> listarCrescente(){
//		casaShowInter.findAllById()
//		
//	}
}

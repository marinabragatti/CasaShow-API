package com.gft.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Casa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Nome da Casa de Show inválido")
	private String nomeCasa;
	
	@NotEmpty(message = "Endereço da Casa de Show inválido")
	private String endereco;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "casaShow")
	private List<Evento> eventos = new ArrayList<Evento>();
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeCasa() {
		return nomeCasa;
	}
	public void setNomeCasa(String nomeCasa) {
		this.nomeCasa = nomeCasa;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((eventos == null) ? 0 : eventos.hashCode());
		result = prime * result + ((nomeCasa == null) ? 0 : nomeCasa.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Casa other = (Casa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (eventos == null) {
			if (other.eventos != null)
				return false;
		} else if (!eventos.equals(other.eventos))
			return false;
		if (nomeCasa == null) {
			if (other.nomeCasa != null)
				return false;
		} else if (!nomeCasa.equals(other.nomeCasa))
			return false;
		return true;
	}
	

}

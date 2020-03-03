package com.gft.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Nome do Evento inválido")
	private String nomeEvento;
	
	@NotNull(message = "Valores inválidos")
	@NumberFormat(pattern = "#,##0")
	@Min(value = 0L, message = "A capacidade deve ser positiva")
	private int capacidade;
	
	@NotNull(message = "Data inválida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataEvento;
	
	@NotNull(message = "Valor do ingresso inválido")
	@NumberFormat(pattern = "#,##0.00")
	@DecimalMin(value = "0.01", message = "O valor não pode ser menor que R$0,01")
	private BigDecimal valorIngresso;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Casa casaShow;
	
	@Enumerated(EnumType.STRING)
	private GeneroMusical genero;
	
		
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}
	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}
	public Casa getCasaShow() {
		return casaShow;
	}
	public void setCasaShow(Casa casaShow) {
		this.casaShow = casaShow;
	}
	public GeneroMusical getGenero() {
		return genero;
	}
	public void setGenero(GeneroMusical genero) {
		this.genero = genero;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacidade;
		result = prime * result + ((casaShow == null) ? 0 : casaShow.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataEvento == null) ? 0 : dataEvento.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((nomeEvento == null) ? 0 : nomeEvento.hashCode());
		result = prime * result + ((valorIngresso == null) ? 0 : valorIngresso.hashCode());
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
		Evento other = (Evento) obj;
		if (capacidade != other.capacidade)
			return false;
		if (casaShow == null) {
			if (other.casaShow != null)
				return false;
		} else if (!casaShow.equals(other.casaShow))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataEvento == null) {
			if (other.dataEvento != null)
				return false;
		} else if (!dataEvento.equals(other.dataEvento))
			return false;
		if (genero != other.genero)
			return false;
		if (nomeEvento == null) {
			if (other.nomeEvento != null)
				return false;
		} else if (!nomeEvento.equals(other.nomeEvento))
			return false;
		if (valorIngresso == null) {
			if (other.valorIngresso != null)
				return false;
		} else if (!valorIngresso.equals(other.valorIngresso))
			return false;
		return true;
	}
	
	
	
}

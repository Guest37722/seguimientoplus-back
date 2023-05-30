package com.ohtic.seguimientoplus.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Seguimiento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private int pacienteId;
    private int altura;
    private int peso;
    private int masaGrasa;
    private int masaMagra;
    private Date fechaRegistro;
    
    @PrePersist
    public void prePersist() {
    	this.fechaRegistro = new Date();
    }

    
    
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getPacienteId() {
		return pacienteId;
	}



	public void setPacienteId(int pacienteId) {
		this.pacienteId = pacienteId;
	}



	public int getAltura() {
		return altura;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public int getPeso() {
		return peso;
	}



	public void setPeso(int peso) {
		this.peso = peso;
	}



	public int getMasaGrasa() {
		return masaGrasa;
	}



	public void setMasaGrasa(int masaGrasa) {
		this.masaGrasa = masaGrasa;
	}



	public int getMasaMagra() {
		return masaMagra;
	}



	public void setMasaMagra(int masaMagra) {
		this.masaMagra = masaMagra;
	}



	public Date getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;

}

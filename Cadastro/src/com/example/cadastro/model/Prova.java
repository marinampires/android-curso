package com.example.cadastro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prova implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String data;
	private String materia;
	private List<String> topicos = new ArrayList<String>();

	public Prova(String data, String materia) {
		super();
		this.data = data;
		this.materia = materia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public List<String> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<String> topicos) {
		this.topicos = topicos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Prova [data=" + data + ", materia=" + materia + "]";
	}
	
}

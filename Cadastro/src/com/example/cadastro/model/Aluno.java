package com.example.cadastro.model;

import java.io.Serializable;
import java.util.List;

import android.content.Context;

import com.example.cadastro.dao.AlunoDAO;

public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String endereco;
	private String telefone;
	private String site;
	private String caminhoFoto;
	private Double nota;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	public void gravar(Context context){
		AlunoDAO dao = new AlunoDAO(context);
		if(this.id == null)
			dao.inserir(this);
		else
			dao.alterar(this);
		dao.close();
	}
	
	public static List<Aluno> getAlunos(Context context){
		AlunoDAO dao = new AlunoDAO(context);
		List<Aluno> alunos = dao.list();
		dao.close();
		
		return alunos;
	}

	public static Aluno getAluno(Context context, Long id){
		AlunoDAO dao = new AlunoDAO(context);
		Aluno aluno = dao.findBy(id);
		dao.close();
		
		return aluno;
	}
	
	public static boolean isAluno(Context context, String telefone){
		AlunoDAO dao = new AlunoDAO(context);
		boolean existe = dao.isAluno(telefone);
		dao.close();
		
		return existe;
	}
	
	public void delete(Context context){
		AlunoDAO dao = new AlunoDAO(context);
		dao.delete(this.id);
		dao.close();
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + " - Nome: " + this.nome;
	}
	
	
}

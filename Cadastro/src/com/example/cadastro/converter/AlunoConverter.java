package com.example.cadastro.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import com.example.cadastro.model.Aluno;

public class AlunoConverter {
	public static String toJSON(List<Aluno> alunos){
		JSONStringer jsonStringer = new JSONStringer();
		try {
			jsonStringer.object().key("list").array().object().key("aluno").array();
			
			for (Aluno aluno : alunos) {
				jsonStringer.object()
				.key("id").value(aluno.getId())
				.key("nome").value(aluno.getNome())
				.key("telefone").value(aluno.getSite())
				.key("site").value(aluno.getSite())
				.key("endereco").value(aluno.getEndereco())
				.key("nota").value(aluno.getNota())
				.endObject();
			}
			
			return jsonStringer.endArray().endObject().endArray().endObject().toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}

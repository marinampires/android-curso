package com.example.cadastro.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.cadastro.model.Aluno;
import com.example.cadastro.util.ContextActionBar;
import com.example.cadastro.view.FormularioActivity;
import com.example.cadastro.view.ListaAlunosActivity;

public class ItemListaListener implements OnItemLongClickListener, OnItemClickListener{
	private ListaAlunosActivity activity;
	
	public ItemListaListener(){};
	
	public ItemListaListener(ListaAlunosActivity activity){
		this.activity = activity;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
		Aluno alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
		ContextActionBar actionBar = new ContextActionBar(alunoSelecionado, activity);
		activity.startSupportActionMode(actionBar);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
		Aluno alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
		Intent edicao = new Intent(activity, FormularioActivity.class);
		edicao.putExtra("alunoSelecionado", alunoSelecionado);
		activity.startActivity(edicao);
	}

}

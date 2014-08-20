package com.example.cadastro.view;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cadastro.R;
import com.example.cadastro.adapter.ListaAlunoAdapter;
import com.example.cadastro.listener.ItemListaListener;
import com.example.cadastro.model.Aluno;
import com.example.cadastro.task.EnviaAlunosTask;

public class ListaAlunosActivity extends ActionBarActivity {
	private ListView listaAlunos;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		listaAlunos = (ListView)findViewById(R.id.lista_alunos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.carregaLista();
	}

	public void carregaLista(){
		List<Aluno> alunos = Aluno.getAlunos(this);
		
		ListaAlunoAdapter adapter = new ListaAlunoAdapter(alunos, this);
		this.listaAlunos.setAdapter(adapter);
		ItemListaListener itemListaListener = new ItemListaListener(this);
		this.listaAlunos.setOnItemLongClickListener(itemListaListener);
		this.listaAlunos.setOnItemClickListener(itemListaListener);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_novo:
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			return false;
		case R.id.menu_mapa:
			Toast.makeText(this, "Menu mapa clicado", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_enviar_alunos:
			new EnviaAlunosTask(this).execute();
			return false;
		case R.id.menu_receber_provas:
			Intent provas = new Intent(this, ProvasActivity.class);
			startActivity(provas);
			return false;
		case R.id.menu_preferencias:
			Toast.makeText(this, "Menu preferencias clicado", Toast.LENGTH_SHORT).show();
			break;		
		}
		return super.onOptionsItemSelected(item);
	}

}

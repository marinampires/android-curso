package com.example.cadastro.view;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ExpandableListView;

import com.example.cadastro.R;
import com.example.cadastro.fragment.ListaProvasCompletaAdapter;
import com.example.cadastro.model.Prova;

public class ProvasCompletasActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_list);
		
		ExpandableListView listViewProvas = (ExpandableListView) findViewById(R.id.lista_expansivel_provas);
		Prova prova = new Prova("20/08/2014", "Programação");
		prova.setTopicos(Arrays.asList("Algoritmo", "Iterações", "Condicional","Algoritmo", "Iterações", "Condicional"));
		
		Prova prova2 = new Prova("20/08/2014", "Matemática");
		prova2.setTopicos(Arrays.asList("Álgebra Linear", "Integral", "Diferencial", "Álgebra Linear", "Integral", "Diferencial"));
		
		List<Prova> provas = Arrays.asList(prova, prova2);
		
		listViewProvas.setAdapter(new ListaProvasCompletaAdapter(provas, this));
	}

}

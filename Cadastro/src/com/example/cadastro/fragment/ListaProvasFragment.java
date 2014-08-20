package com.example.cadastro.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.cadastro.R;
import com.example.cadastro.adapter.ListaProvaAdapter;
import com.example.cadastro.model.Prova;
import com.example.cadastro.view.ProvasActivity;

public class ListaProvasFragment extends Fragment {
	private ListView listViewProvas;
	private boolean isTablet;
	
	public ListaProvasFragment(boolean isTablet) {
		super();
		this.isTablet = isTablet;
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.provas_lista, container, false);
		this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas);
		
		Prova prova = new Prova("20/08/2014", "Programação");
		prova.setTopicos(Arrays.asList("Algoritmo", "Iterações", "Condicional"));
		
		Prova prova2 = new Prova("20/08/2014", "Matemática");
		prova2.setTopicos(Arrays.asList("Álgebra Linear", "Integral", "Diferencial"));
		
		List<Prova> provas = Arrays.asList(prova, prova2);
		this.listViewProvas.setAdapter(new ListaProvaAdapter(provas, this));
		
		this.listViewProvas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				Prova provaSelecionada = (Prova) adapter.getItemAtPosition(posicao);
				ProvasActivity calendarioProvas = (ProvasActivity)getActivity();
				calendarioProvas.setProva(provaSelecionada);
			}
			
		});
		
		if(isTablet){
			//Inicializando com a 1a prova
			ProvasActivity calendarioProvas = (ProvasActivity)getActivity();
			calendarioProvas.setProva(prova);
		}
		return layoutProvas;
	}

	
}

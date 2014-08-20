package com.example.cadastro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cadastro.R;
import com.example.cadastro.model.Prova;

public class DetalhesProvaFragment extends Fragment {
	private Prova prova;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutDetalhes = inflater.inflate(R.layout.provas_detalhe, container, false);
		if(getArguments() != null){
			this.prova = (Prova) getArguments().getSerializable("prova");
		}

		if(this.prova != null){
			TextView materia = (TextView) layoutDetalhes.findViewById(R.id.detalhe_prova_materia);
			materia.setText(this.prova.getMateria());
			
			TextView data = (TextView) layoutDetalhes.findViewById(R.id.detalhe_prova_data);
			data.setText(this.prova.getData());
			
			ListView topicos = (ListView) layoutDetalhes.findViewById(R.id.detalhe_prova_topicos);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, this.prova.getTopicos());
			topicos.setAdapter(adapter);
		}
		
		return layoutDetalhes;
	}
	
}

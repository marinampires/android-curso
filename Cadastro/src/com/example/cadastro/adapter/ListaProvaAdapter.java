package com.example.cadastro.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cadastro.R;
import com.example.cadastro.fragment.ListaProvasFragment;
import com.example.cadastro.model.Prova;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class ListaProvaAdapter extends BaseAdapter{
	private List<Prova> provas;
	private ListaProvasFragment activity;
	
	public ListaProvaAdapter(List<Prova> provas, ListaProvasFragment activity) {
		super();
		this.provas = provas;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return this.provas.size();
	}

	@Override
	public Object getItem(int posicao) {
		return this.provas.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return 1;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {
		View view = activity.getActivity().getLayoutInflater().inflate(R.layout.item_prova, null);
		
		Prova prova = (Prova) getItem(posicao);
		
		TextView materia = (TextView) view.findViewById(R.id.prova_materia);
		materia.setText(prova.getMateria());
		
		TextView data = (TextView) view.findViewById(R.id.prova_data);
		if(data != null){
			data.setText(prova.getData());
		}
		
		return view;
	}

}

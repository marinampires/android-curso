package com.example.cadastro.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.cadastro.R;
import com.example.cadastro.model.Prova;

@SuppressLint("InflateParams")
public class ListaProvasCompletaAdapter extends BaseExpandableListAdapter{
	private List<Prova> provas;
	private Activity activity;
	
	public ListaProvasCompletaAdapter(List<Prova> provas, Activity activity) {
		super();
		this.provas = provas;
		this.activity = activity;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.provas.get(groupPosition).getTopicos().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return this.provas.get(groupPosition).getTopicos().get(childPosition).hashCode();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = activity.getLayoutInflater();
		
		View topicoLayout = (View) inflater.inflate(R.layout.item_topico_prova, null);
		
		TextView topicoText = (TextView) topicoLayout.findViewById(R.id.topico);
		String topico = provas.get(groupPosition).getTopicos().get(childPosition);
		topicoText.setText(topico);
		
		return topicoLayout;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.provas.get(groupPosition).getTopicos().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.provas.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.provas.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return this.provas.get(groupPosition).hashCode();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View itemProva = activity.getLayoutInflater().inflate(R.layout.item_prova, null);
		
		Prova prova = (Prova) this.provas.get(groupPosition);
		
		TextView materia = (TextView) itemProva.findViewById(R.id.prova_materia);
		materia.setText(prova.getMateria());
		
		return itemProva;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}

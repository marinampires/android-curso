package com.example.cadastro.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cadastro.R;
import com.example.cadastro.fragment.DetalhesProvaFragment;
import com.example.cadastro.fragment.ListaProvasFragment;
import com.example.cadastro.model.Prova;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle estado) {
		super.onCreate(estado);
		setContentView(R.layout.provas);

		ListaProvasFragment lista = new ListaProvasFragment(); 
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.provas_lista, lista);

		transaction.commit();
		if(isTablet() && lista.getProvas() != null && !lista.getProvas().isEmpty())
			setProva(lista.getProvas().get(0));
		
	}

	public void setProva(Prova prova) {
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", prova);

		DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
		detalhesProva.setArguments(argumentos);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		if (isTablet()) {
			transaction.replace(R.id.provas_lista, new ListaProvasFragment());
			transaction.replace(R.id.provas_view, detalhesProva);
		} else {
			transaction.replace(R.id.provas_lista, detalhesProva);
			transaction.addToBackStack(null);
		}

		transaction.commit();

	}

	public boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}
}
